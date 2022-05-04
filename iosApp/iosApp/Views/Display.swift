import SwiftUI

struct Display: View {
    
    @Binding var text: String
    
    var body: some View {
        ZStack {
            Text(self.text)
                .font(Font.custom(FontRecources.Led, size: 24))
                .textCase(.uppercase)
                .foregroundColor(ColorsPallete.greenLight)
                .frame(maxWidth: .infinity, maxHeight: .infinity)
        }
        .background(
            LinearGradient(
                gradient: Gradient(
                    colors: [ColorsPallete.greenLight, ColorsPallete.greenDark, ColorsPallete.greenDark]),
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
