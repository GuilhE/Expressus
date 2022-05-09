import SwiftUI

struct CoffeeStream: View {
    
    @Binding var pouring: Bool
    
    @State private var poured: Bool = false
    @State private var height: CGFloat = 0
    @State private var finalHeight: CGFloat = 0
    @State private var topLeft: CGPoint = CGPoint(x: 0, y: 0)
    
    private let step: CGFloat = 10
    
    var body: some View {
        GeometryReader { geometry in
            ThemeScope(theme: Themes.CoffeeStream()) { theme in
                CoffeeStreamShape(topLeft: self.$topLeft, height: self.$finalHeight).fill(theme.primary)
            }
            .onChange(of: pouring) { isPouring in
                let speed = geometry.size.height / 250
                Task.init() {
                    if (isPouring) {
                        if (!poured) {
                            while (finalHeight < geometry.size.height) {
                                finalHeight += step
                                try await Task.sleep(nanoseconds: 1_000_000 * UInt64(speed))
                            }
                            poured = true
                        }
                    } else {
                        if (poured) {
                            while (topLeft.y < geometry.size.height) {
                                topLeft = CGPoint(x: 0, y: topLeft.y + step)
                                finalHeight -= step
                                try await Task.sleep(nanoseconds: 1_000_000 * UInt64(speed))
                            }
                        }
                        finalHeight = 0
                        topLeft = CGPoint(x: 0, y: 0)
                        poured = false
                    }
                }
            }
        }
    }
}

private struct CoffeeStreamShape: Shape {
    
    @Binding var topLeft: CGPoint
    @Binding var height: CGFloat
    
    func path(in rect: CGRect) -> Path {
        var path = Path()
        path.addRoundedRect(
            in: CGRect(origin: topLeft, size: CGSize(width: rect.width, height: self.height)),
            cornerSize: CGSize(width: 20, height: 20)
        )
        return path
    }
}

struct CoffeeStream_Previews: PreviewProvider {
    static var previews: some View {
        OtherView(pouring: false).previewInterfaceOrientation(.portrait)
    }
    
    struct OtherView : View {
        @State var pouring : Bool
        
        var body: some View {
            VStack {
                CoffeeStream(pouring: self.$pouring).padding(.horizontal, 170)
                Button("Animate", action: { pouring.toggle() }).padding()
            }
        }
    }
}
