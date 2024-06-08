import SwiftUI

struct Display: View {
    
    @Binding var text: String
    
    var body: some View {
        ThemeScope(theme: Themes.Display()) { theme in
            ZStack {
                Text(self.text)
                    .font(Font.custom(Fonts.Led, size: 24))
                    .textCase(.uppercase)
                    .foregroundColor(theme.onBackground)
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
            }
            .background(
                LinearGradient(
                    gradient: Gradient(colors: [theme.onBackground, theme.background, theme.background]),
                    startPoint: .top,
                    endPoint: .bottom
                )
            )            
            .cornerRadius(6)
        }
    }
}

struct Display_Previews: PreviewProvider {
    static var previews: some View {
        OtherView(text: "Stand by")
    }
    struct OtherView : View {
        @State var text : String = ""
        var body: some View {
            Display(text: self.$text).padding(10)
        }
    }
}
