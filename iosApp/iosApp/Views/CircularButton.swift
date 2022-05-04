import SwiftUI

struct CircularButton: View {
    
    private var size: CGFloat
    private var action: () -> Void
    
    init(size: CGFloat, action: @escaping () -> Void) {
        self.size = size
        self.action = action
    }
    
    var body: some View {
        Button(action: action) {
            Circle()
                .fill(
                    RadialGradient(
                        gradient: Gradient(
                            colors: [ColorsPallete.whiteDirty, ColorsPallete.grayDark]),
                        center: UnitPoint(x: 0, y: 0),
                        startRadius: 0,
                        endRadius: 30
                    )
                )
                .frame(width: size, height: size)
        }
    }
}

struct CircularButton_Previews: PreviewProvider {
    static var previews: some View {
        ZStack {
            Color.black
            CircularButton(size: 50, action: {})
        }
    }
}
