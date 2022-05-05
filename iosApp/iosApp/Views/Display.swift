import SwiftUI

struct Display: View {
    
    @Binding var text: String
    
    var body: some View {
        ZStack {
            Text(self.text)
                .font(Font.custom(FontRecources.Led, size: 24))
                .textCase(.uppercase)
                .foregroundColor(Themes.Display.onBackground)
                .frame(maxWidth: .infinity, maxHeight: .infinity)
        }
        .background(
            LinearGradient(
                gradient: Gradient(
                    colors: [Themes.Display.onBackground, Themes.Display.background, Themes.Display.background]),
                startPoint: .top,
                endPoint: .bottom
            )
        )
        .frame(maxWidth: .infinity, maxHeight: 50, alignment: .center)
        .cornerRadius(6)        
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
