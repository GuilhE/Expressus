import SwiftUI

struct Slot: View {
    
    private var width: CGFloat
    private var height: CGFloat
    private var strokeWidth: CGFloat
    private var topOffset: CGFloat
    private var bottomOffset: CGFloat
    private var convexTop: Bool
    private var flatTop: Bool
    private var convexBottom: Bool
    private var flatBottom: Bool
    private var top: Color
    private var end: Color
    private var bottom: Color
    private var start: Color
    private var gradientColors: [Color]?
    
    init(width: CGFloat,
         height: CGFloat,
         strokeWidth: CGFloat,
         topOffset: CGFloat = 0,
         bottomOffset: CGFloat = 0,
         convexTop: Bool = true,
         flatTop: Bool = false,
         convexBottom: Bool = true,
         flatBottom: Bool = false,
         start: Color,
         end: Color,
         top: Color,
         bottom: Color,
         gradientColors: [Color]? = nil
    ) {
        self.width = width
        self.height = height
        self.strokeWidth = strokeWidth
        self.topOffset = topOffset
        self.bottomOffset = bottomOffset
        self.convexTop = convexTop
        self.flatTop = flatTop
        self.convexBottom = convexBottom
        self.flatBottom = flatBottom
        self.top = top
        self.end = end
        self.bottom = bottom
        self.start = start
        self.gradientColors = gradientColors
    }
    
    var body: some View {
        ZStack {
            if let clrs = gradientColors {
                SlotBackgroundShape(
                    topOffset: topOffset,
                    bottomOffset: bottomOffset,
                    convexTop: convexTop,
                    convexBottom: convexBottom
                ).fill(LinearGradient(gradient: Gradient(colors: clrs), startPoint: .leading, endPoint: .trailing))
            }
            
            SlotTopShape(
                width: strokeWidth/2,
                offset: topOffset,
                convex: convexTop,
                flat: flatTop
            ).fill(top)
            
            SlotEndShape(
                width: strokeWidth/2,
                topOffset: topOffset,
                bottomOffset: bottomOffset,
                convexTop: convexTop,
                convexBottom: convexBottom
            ).fill(end)
            
            SlotBottomShape(
                width: strokeWidth/2,
                offset: bottomOffset,
                convex: convexBottom,
                flat: flatBottom
            ).fill(bottom)
            
            SlotStartShape(
                width: strokeWidth/2,
                topOffset: topOffset,
                bottomOffset: bottomOffset,
                convexTop: convexTop,
                convexBottom: convexBottom
            ).fill(start)
            
        }.frame(width: width, height: height, alignment: .center)
    }
}

private struct SlotBackgroundShape: Shape {
    
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
        path.addQuadCurve(to: CGPoint(x: rect.width, y: convexTop ? topOffset : 0), control: CGPoint(x: rect.midX, y: convexTop ? -topOffset : topOffset))
        path.addLine(to: CGPoint(x: rect.width, y: rect.height - (convexBottom ? bottomOffset : 0)))
        path.addQuadCurve(
            to: CGPoint(x: 0, y: rect.height - (convexBottom ? bottomOffset : 0)),
            control: CGPoint(x: rect.midX, y: rect.height + (convexBottom ? bottomOffset : -bottomOffset))
        )
        return path
    }
}

private struct SlotTopShape: Shape {
    
    private var width: CGFloat
    private var offset: CGFloat
    private var convex: Bool
    private var flat: Bool
    
    init(width: CGFloat, offset: CGFloat, convex: Bool, flat: Bool) {
        self.width = width
        self.offset = offset
        self.convex = convex
        self.flat = flat
    }
    
    func path(in rect: CGRect) -> Path {
        var path = Path()
        path.move(to: CGPoint(x: 0, y: convex ? offset : 0))
        path.addQuadCurve(
            to: CGPoint(x: rect.width, y: convex ? offset : 0),
            control: CGPoint(x: rect.midX, y: convex ? -offset : offset)
        )
        path.addLine(to: CGPoint(x: rect.width - width, y: width + (convex ? offset : 0)))
        if (flat) {
            path.addLine(to: CGPoint(x: width, y: width + (convex ? offset : 0)))
        } else {
            path.addQuadCurve(
                to: CGPoint(x: width, y: width + (convex ? offset : 0)),
                control: CGPoint(x: rect.midX, y: width - (convex ? offset : -offset))
            )
        }
        return path
    }
}

private struct SlotBottomShape: Shape {
    
    private var width: CGFloat
    private var offset: CGFloat
    private var convex: Bool
    private var flat: Bool
    
    init(width: CGFloat, offset: CGFloat, convex: Bool, flat: Bool) {
        self.width = width
        self.offset = offset
        self.convex = convex
        self.flat = flat
    }
    
    func path(in rect: CGRect) -> Path {
        var path = Path()
        path.move(to: CGPoint(x: width, y: rect.height - width - (convex ? offset : 0)))
        if(flat) {
            path.addLine(to: CGPoint(x: rect.width - width, y: rect.height - width - (convex ? offset : 0)))
        } else {
            path.addQuadCurve(
                to: CGPoint(x: rect.width - width, y: rect.height - width - (convex ? offset : 0)),
                control: CGPoint(x: rect.midX, y: rect.height - width - (convex ? -offset : offset))
            )
        }
        path.addLine(to: CGPoint(x: rect.width, y: rect.height - (convex ? offset : 0)))
        path.addQuadCurve(
            to: CGPoint(x: 0, y: rect.height - (convex ? offset : 0)),
            control: CGPoint(x: rect.midX, y: rect.height - (convex ? -offset : offset))
        )
        return path
    }
}

private struct SlotStartShape: Shape {
    
    private var width: CGFloat
    private var topOffset: CGFloat
    private var bottomOffset: CGFloat
    private var convexTop: Bool
    private var convexBottom: Bool
    
    init(width: CGFloat, topOffset: CGFloat, bottomOffset: CGFloat, convexTop: Bool, convexBottom: Bool) {
        self.width = width
        self.topOffset = topOffset
        self.bottomOffset = bottomOffset
        self.convexTop = convexTop
        self.convexBottom = convexBottom
    }
    
    func path(in rect: CGRect) -> Path {
        var path = Path()
        path.move(to: CGPoint(x: 0, y: convexTop ? topOffset : 0))
        path.addLine(to: CGPoint(x: width, y: width + (convexTop ? topOffset : 0)))
        path.addLine(to: CGPoint(x: width, y: rect.height - width - (convexBottom ? bottomOffset : 0)))
        path.addLine(to: CGPoint(x: 0, y: rect.height - (convexBottom ? bottomOffset : 0)))
        return path
    }
}

private struct SlotEndShape: Shape {
    
    private var width: CGFloat
    private var topOffset: CGFloat
    private var bottomOffset: CGFloat
    private var convexTop: Bool
    private var convexBottom: Bool
    
    init(width: CGFloat, topOffset: CGFloat, bottomOffset: CGFloat, convexTop: Bool, convexBottom: Bool) {
        self.width = width
        self.topOffset = topOffset
        self.bottomOffset = bottomOffset
        self.convexTop = convexTop
        self.convexBottom = convexBottom
    }
    
    func path(in rect: CGRect) -> Path {
        var path = Path()
        path.move(to: CGPoint(x: rect.width, y: convexTop ? topOffset : 0))
        path.addLine(to: CGPoint(x: rect.width, y: rect.height - (convexBottom ? bottomOffset : 0)))
        path.addLine(to: CGPoint(x: rect.width - width, y: rect.height - width - (convexBottom ? bottomOffset : 0)))
        path.addLine(to: CGPoint(x: rect.width - width, y: width + (convexTop ? topOffset : 0)))
        return path
    }
}

struct Slot_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            Slot(width: 150,
                 height: 150,
                 strokeWidth: 50,
                 topOffset: 10,
                 bottomOffset: 10,
                 flatTop: true,
                 flatBottom: true,
                 start: Color.black,
                 end: Color.black,
                 top: Color.red,
                 bottom: Color.red,
                 gradientColors: [Color.green, Color.yellow]
            )
            
            Slot(width: 150,
                 height: 150,
                 strokeWidth: 50,
                 topOffset: 10,
                 bottomOffset: 10,
                 convexTop: false,
                 flatTop: true,
                 convexBottom: false,
                 flatBottom: true,
                 start: Color.black,
                 end: Color.black,
                 top: Color.red,
                 bottom: Color.red,
                 gradientColors: [Color.green, Color.yellow]
            )
            
            Slot(width: 150,
                 height: 150,
                 strokeWidth: 50,
                 topOffset: 10,
                 bottomOffset: 10,
                 convexTop: true,
                 flatTop: false,
                 convexBottom: true,
                 flatBottom: false,
                 start: Color.black,
                 end: Color.black,
                 top: Color.red,
                 bottom: Color.red,
                 gradientColors: [Color.green, Color.yellow]
            )
            
            Slot(width: 150,
                 height: 150,
                 strokeWidth: 50,
                 topOffset: 10,
                 bottomOffset: 10,
                 convexTop: true,
                 flatTop: false,
                 convexBottom: true,
                 flatBottom: false,
                 start: Color.black,
                 end: Color.black,
                 top: Color.red,
                 bottom: Color.red,
                 gradientColors: [Color.green, Color.yellow]
            )
        }
    }
}
