import SwiftUI

struct MachineLeftFrame<Content> : View where Content : View {
        
    private var content: () -> Content
    
    init(@ViewBuilder content: @escaping () -> Content) {
        self.content = content
    }
    
    var body: some View {
        ThemeScope(theme: Themes.MachineFrame()) { theme in
            Panel([theme.primary, theme.surface, theme.surface, theme.surface, theme.primary], bottomOffset: 0) { content() }
        }
    }
}

struct MachineLeftFrame_Previews: PreviewProvider {
    static var previews: some View {
        MachineLeftFrame {
            Text("Hello World")
                .foregroundColor(Color.white)
        }
    }
}
