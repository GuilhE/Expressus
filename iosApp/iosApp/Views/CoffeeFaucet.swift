import SwiftUI

struct CoffeeFaucet: View {
    var body: some View {
        GeometryReader { geometry in
            ThemeScope(theme: Themes.CoffeeSlot()) { theme in
                let gradient = LinearGradient(gradient: Gradient(colors: [theme.primary, theme.primaryVariant]), startPoint: .top, endPoint: .bottom)
                ZStack {
                    Panel(
                        bottomOffset: geometry.size.width / 25,
                        gradient: gradient,
                        content: {}
                    ).frame(
                        width: geometry.size.width / 3,
                        height: geometry.size.width / 4,
                        alignment: .bottom
                    ).padding(.top, geometry.size.width / 3.5)
                    
                    Panel(
                        bottomOffset: geometry.size.width / 25,
                        gradient: gradient,
                        content: {}
                    ).frame(
                        width: geometry.size.width,
                        height: geometry.size.width / 3,
                        alignment: .top
                    )
                }
            }
        }
    }
}

struct CoffeeFaucet_Previews: PreviewProvider {
    static var previews: some View {
        CoffeeFaucet().padding()
    }
}
