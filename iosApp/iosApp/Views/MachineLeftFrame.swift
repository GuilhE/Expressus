import SwiftUI

struct MachineLeftFrame<Content> : View where Content : View {
    
    private let gradientColors: [Color] = [Themes.MachineFrame.surface, Themes.MachineFrame.surface]
    private var content: () -> Content
    
    init(@ViewBuilder content: @escaping () -> Content) {
        self.content = content
    }
    
    var body: some View {
        Panel(gradientColors, bottomOffset: 0) { content() }
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
