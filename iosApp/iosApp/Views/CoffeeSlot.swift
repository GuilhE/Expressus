import SwiftUI

struct CoffeeSlot: View {
    
    var body: some View {
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
}

struct CoffeeSlot_Previews: PreviewProvider {
    static var previews: some View {
        CoffeeSlot()
            .frame(maxWidth: .infinity, alignment: .center)
            .aspectRatio(1, contentMode: .fit)
            .padding()
    }
}
