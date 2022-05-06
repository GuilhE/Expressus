import SwiftUI

struct CoffeeStream: View {
    var body: some View {
        ThemeScope(theme: Themes.CoffeeStream()) { theme in
            CoffeeStream()
        }
    }
}

struct CoffeeStream_Previews: PreviewProvider {
    static var previews: some View {
        CoffeeStream()
    }
}
