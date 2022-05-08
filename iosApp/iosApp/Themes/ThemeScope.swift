import SwiftUI

struct ThemeScope<Content : View, T : MaterialTheme> : View {
    
    private let theme: T
    private let content: (T) -> Content
    
    init(theme: T, @ViewBuilder content: @escaping (_ theme: T) -> Content) {
        self.theme = theme
        self.content = content
    }
        
    var body: some View {
        content(theme)
    }
}

private struct ThemeScope_Previews: PreviewProvider {
    struct PreviewTheme : ThemeWithBackground {
        let background: Color = Color.black
        let onBackground: Color = Color.white
    }
    
    static var previews: some View {
        ThemeScope(theme: PreviewTheme()) { theme in
            Text("Hello, World!")
                .background(theme.background)
                .foregroundColor(theme.onBackground)
        }
    }
}
