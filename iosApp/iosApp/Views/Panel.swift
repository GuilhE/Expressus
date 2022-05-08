import SwiftUI

struct Panel<Content> : View where Content : View {
        
    private var topOffset: CGFloat
    private var bottomOffset: CGFloat
    private var convexTop: Bool
    private var convexBottom: Bool
    private var gradient: LinearGradient?
    private var content: () -> Content
    
    init(topOffset: CGFloat = 0, bottomOffset: CGFloat = 0, convexTop: Bool = true, convexBottom: Bool = true, gradient: LinearGradient? = nil, @ViewBuilder content: @escaping () -> Content) {
        self.topOffset = topOffset
        self.bottomOffset = bottomOffset
        self.convexTop = convexTop
        self.convexBottom = convexBottom
        self.gradient = gradient
        self.content = content
    }
    
    var body: some View {
        ZStack {
           let v = PanelShape(
                topOffset: topOffset,
                bottomOffset: bottomOffset,
                convexTop: convexTop,
                convexBottom: convexBottom
            )
            if let style = gradient {
                v.fill(style)
            }
            content()
        }
    }
}

private struct PanelShape: Shape {
    
    private var topOffset: CGFloat
    private var bottomOffset: CGFloat
    private var convexTop: Bool
    private var convexBottom: Bool
    
    init(topOffset: CGFloat, bottomOffset: CGFloat, convexTop: Bool, convexBottom: Bool) {
        self.topOffset = topOffset
        self.bottomOffset = bottomOffset
        self.convexTop = convexTop
        self.convexBottom = convexBottom
    }
    
    func path(in rect: CGRect) -> Path {
        var path = Path()
        path.move(to: CGPoint(x: 0, y: convexTop ? topOffset : 0))
        path.addQuadCurve(
            to: CGPoint(x: rect.width, y: convexTop ? topOffset : 0),
            control: CGPoint(x: rect.midX, y: convexTop ? -topOffset : topOffset)
        )
        path.addLine(to: CGPoint(x: rect.width, y: rect.height - (convexBottom ? bottomOffset : 0)))
        path.addQuadCurve(
            to: CGPoint(x: 0, y: rect.height - (convexBottom ? bottomOffset : 0)),
            control: CGPoint(x: rect.midX, y: rect.height + (convexBottom ? bottomOffset : -bottomOffset))
        )
        return path
    }
}

struct Panel_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            let gradient = LinearGradient(gradient: Gradient(colors: [Color.red, Color.yellow]), startPoint: .leading, endPoint: .trailing)
            Panel(topOffset: 10, bottomOffset: 10, gradient: gradient) {}
            Panel(topOffset: 10, bottomOffset: 10, convexTop: false, convexBottom: false, gradient: gradient) {}
        }
        .padding(10)
    }
}
