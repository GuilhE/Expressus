import SwiftUI

struct Panel<Content> : View where Content : View {
    
    private var gradientColors: [Color]
    private var topOffset: CGFloat
    private var bottomOffset: CGFloat
    private var convexTop: Bool
    private var convexBottom: Bool
    private var content: () -> Content
    
    init(_ gradientColors: [Color], topOffset: CGFloat = 10, bottomOffset: CGFloat = 10, convexTop: Bool = true, convexBottom: Bool = true, @ViewBuilder content: @escaping () -> Content) {
        self.gradientColors = gradientColors
        self.topOffset = topOffset
        self.bottomOffset = bottomOffset
        self.convexTop = convexTop
        self.convexBottom = convexBottom
        self.content = content
    }
    
    var body: some View {
        return ZStack {
            PanelShape(
                topOffset: topOffset,
                bottomOffset: bottomOffset,
                convexTop: convexTop,
                convexBottom: convexBottom
            )
            .fill(LinearGradient(gradient: Gradient(colors: gradientColors), startPoint: .leading, endPoint: .trailing))
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
            control: CGPoint(x: rect.width/2, y: convexTop ? -topOffset : topOffset)
        )
        path.addLine(to: CGPoint(x: rect.width, y: rect.height - (convexBottom ? bottomOffset : 0)))
        path.addQuadCurve(
            to: CGPoint(x: 0, y: rect.height - (convexBottom ? bottomOffset : 0)),
            control: CGPoint(x: rect.width/2, y: rect.height + (convexBottom ? bottomOffset : -bottomOffset))
        )
        return path
    }
}

struct Panel_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            Panel([Color.red, Color.yellow]) {}
            Panel([Color.red, Color.yellow], convexTop: false, convexBottom: false) {}
        }
        .padding(10)
    }
}
