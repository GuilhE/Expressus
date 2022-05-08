import SwiftUI

struct CircularButton: View {
    
    let size: CGFloat
    let action: () -> Void
       
    var body: some View {
        ThemeScope(theme: Themes.CircularButton()) { theme in
            Button(action: action) {
                Circle()
                    .fill(
                        RadialGradient(
                            gradient: Gradient(colors: [theme.background, theme.onBackground]),
                            center: UnitPoint(x: 0, y: 0),
                            startRadius: 0,
                            endRadius: (size*30)/50
                        )
                    )
                    .frame(width: size, height: size)
            }
        }
    }
}

struct CircularButton_Previews: PreviewProvider {
    static var previews: some View {
        ZStack {
            Color.black
            CircularButton(size: 100, action: {})
        }
    }
}
