import SwiftUI

struct Cup: View {
    
    let height: CGFloat
    
    var body: some View {
        ThemeScope(theme: Themes.Cup()) { theme in
            ZStack(alignment: .top) {
                let w = height
                let h = height
                
                let borderHeight = h / 7
                let bodyOffset = borderHeight / 3
                let bezierOffset = h / 80
                let bezierOffset2 = h / 200
                
                let topLeft = CGPoint(x: bodyOffset, y: borderHeight)
                let topRight = CGPoint(x: w - bodyOffset, y: borderHeight)
                let bottomRight = CGPoint(x: w - borderHeight * 3 / 2, y: h)
                let bottomLeft = CGPoint(x: borderHeight * 3 / 2, y: h)
                
                RoundedRectangle(cornerRadius: 10)
                    .fill(theme.primary)
                    .frame(width: w, height: borderHeight)
                
                CupBodyShape(
                    topLeft: topLeft,
                    topRight: topRight,
                    bottomRight: bottomRight,
                    bottomLeft: bottomLeft,
                    bezierOffset: bezierOffset,
                    bezierOffset2: bezierOffset2
                ).fill(theme.primary)
                
                CupStripeShape(
                    topLeft: topLeft,
                    bottomRight: bottomRight,
                    bottomLeft: bottomLeft,
                    bezierOffset: bezierOffset,
                    bezierOffset2: bezierOffset2
                ).fill(theme.onPrimary)
            }
            .frame(width: height, height: height)
        }
    }
}

private struct CupBodyShape : Shape {
    
    let topLeft: CGPoint
    let topRight: CGPoint
    let bottomRight: CGPoint
    let bottomLeft: CGPoint
    let bezierOffset: CGFloat
    let bezierOffset2: CGFloat
       
    func path(in rect: CGRect) -> Path {
        var path = Path()
        path.move(to: topLeft)
        path.addLine(to: topRight)
        path.addLine(to: CGPoint(x: bottomRight.x + bezierOffset2, y: bottomRight.y - bezierOffset))
        path.addQuadCurve(
            to: CGPoint(x: bottomRight.x - bezierOffset, y: bottomRight.y),
            control: bottomRight
        )
        path.addLine(to: CGPoint(x: bottomRight.x - bezierOffset, y: bottomRight.y))
        path.addLine(to: CGPoint(x: bottomLeft.x + bezierOffset, y: bottomLeft.y))
        path.addQuadCurve(
            to: CGPoint(x: bottomLeft.x - bezierOffset2, y: bottomLeft.y - bezierOffset),
            control: bottomLeft
        )
        path.addLine(to: CGPoint(x: bottomLeft.x - bezierOffset2, y: bottomRight.y - bezierOffset))
        return path
    }
}

private struct CupStripeShape : Shape {
    
    let topLeft: CGPoint
    let bottomRight: CGPoint
    let bottomLeft: CGPoint
    let bezierOffset: CGFloat
    let bezierOffset2: CGFloat
    
    func path(in rect: CGRect) -> Path {
        var path = Path()
        path.move(to: CGPoint(x: (bottomLeft.x + topLeft.x) / 2, y: (bottomLeft.y + topLeft.y) / 2))
        path.addLine(to: CGPoint(x: bottomRight.x - bezierOffset, y: bottomRight.y))
        path.addLine(to: CGPoint(x: bottomLeft.x + bezierOffset, y: bottomLeft.y))
        path.addQuadCurve(
            to: CGPoint(x: bottomLeft.x - bezierOffset2, y: bottomRight.y - bezierOffset),
            control: bottomLeft
        )
        path.addLine(to: CGPoint(x: bottomLeft.x - bezierOffset2, y: bottomRight.y - bezierOffset))
        return path
    }
}

struct Cup_Previews: PreviewProvider {
    static var previews: some View {
        ZStack {
            Color.gray
            Cup(height: CGFloat(150))
        }
    }
}
