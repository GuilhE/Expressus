import SwiftUI

struct CoffeeSlot: View {
    
    @Binding var grinding : Bool
    @Binding var pouring : Bool
    @State private var cupVrtPadding: CGFloat = 0
    @State private var cupHrzPadding: CGFloat = 0
    @State private var grindTask: Task<Void, Never>? = nil
    
    var body: some View {
        GeometryReader { geometry in
            let cupSize = geometry.size.width / 3
            ThemeScope(theme: Themes.CoffeeSlot()) { theme in
                ZStack {
                    Slot(
                        strokeWidth: 30,
                        topOffset: 10,
                        bottomOffset: 10,
                        convexTop: false,
                        flatTop: false,
                        convexBottom: true,
                        flatBottom: true,
                        top: theme.primary,
                        bottom: theme.primaryVariant,
                        start: theme.primary,
                        end: theme.primary,
                        gradient: LinearGradient(
                            gradient: Gradient(colors: [theme.primary, theme.secondary]),
                            startPoint: .top,
                            endPoint: .bottom
                        )
                    )
                    
                    CoffeeStream(pouring: self.$pouring)
                        .frame(width: geometry.size.width / 20)
                        .padding(.vertical, 20)
                    CoffeeFaucet()
                        .padding(.horizontal, geometry.size.width / 2.7)
                    
                    ZStack(alignment: .bottom) {
                        let shadowWidth = cupSize + 5
                        let shadowHeight = shadowWidth / 20
                        Ellipse()
                            .frame(width: shadowWidth, height: shadowHeight)
                            .foregroundColor(Color.black.opacity(0.2))
                            .padding(EdgeInsets(top: 0, leading: 0, bottom: 7 + cupVrtPadding, trailing: cupHrzPadding))
                        Cup(height: geometry.size.width / 3)
                            .padding(EdgeInsets(top: 0, leading: 0, bottom: 10 + cupVrtPadding, trailing: cupHrzPadding))
                    }
                    .frame(maxWidth: cupSize, maxHeight: .infinity, alignment: .bottom)
                    .onChange(of: grinding) { isGrinding in
                        if(isGrinding) {
                            grindTask = Task { await grind() }
                        } else {
                            grindTask?.cancel()
                        }
                    }
                }
                
                //Overlay
                Slot(
                    strokeWidth: 30,
                    topOffset: 10,
                    bottomOffset: 10,
                    convexTop: false,
                    flatTop: false,
                    convexBottom: true,
                    flatBottom: true,
                    top: theme.primary.opacity(0),
                    bottom: theme.primaryVariant.opacity(0),
                    start: theme.primary,
                    end: theme.primary,
                    gradient: LinearGradient(
                        gradient: Gradient(stops: [
                            Gradient.Stop(color: .black.opacity(0.85), location: 0.0),
                            Gradient.Stop(color: .black.opacity(0), location: 0.4),
                            Gradient.Stop(color: .black.opacity(0), location: 0.5),
                            Gradient.Stop(color: .black.opacity(0.85), location: 1.0)
                        ]),
                        startPoint: .leading,
                        endPoint: .trailing
                    )
                )
            }
        }
    }
    
    private func grind() async {
        do {
            cupVrtPadding = 0
            cupHrzPadding = 0
            while (true) {
                cupVrtPadding = CGFloat.random(in: -1...1)
                cupHrzPadding = CGFloat.random(in: 0...1)
                try await Task.sleep(nanoseconds: 50_000_000)
            }
        } catch {
            //ignore
        }
    }
}

struct CoffeeSlot_Previews: PreviewProvider {
    static var previews: some View {
        OtherView(grinding: false,pouring: false).previewInterfaceOrientation(.portrait)
    }
    
    struct OtherView : View {
        @State var grinding : Bool = false
        @State var pouring : Bool = false
        
        var body: some View {
            VStack {
                CoffeeSlot(grinding: self.$grinding, pouring: self.$pouring)
                    .frame(maxWidth: .infinity, alignment: .center)
                    .aspectRatio(1, contentMode: .fit)
                    .padding()
                Button("Grind", action: { grinding.toggle() }).padding()
                Button("Pour", action: { pouring.toggle() }).padding()
            }
        }
    }
}
