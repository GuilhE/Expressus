import SwiftUI

private struct ColorsPallete {
    static let whiteDirty = Color(0xFFFAF0)
    static let gray = Color(0x888888)
    static let grayDark = Color(0x444444)
    static let greenDark = Color(0x00323C)
    static let greenLight = Color(0x58C7C7)
    static let brown = Color(0x643D2A)
    static let brownDark = Color(0x39180F)
    static let redCandy = Color(0xF34737)
}

extension Color {
    init(_ hex: UInt, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xFF) / 255,
            green: Double((hex >> 8) & 0xFF) / 255,
            blue: Double(hex & 0xFF) / 255,
            opacity: alpha
        )
    }
}

protocol MaterialTheme { }

protocol ThemeWithPrimary : MaterialTheme {
    var primary: Color { get }
    var onPrimary: Color { get }
}

protocol ThemeWithPrimaryVariant : MaterialTheme {
    var primaryVariant: Color { get }
    var onPrimaryVariant: Color { get }
}

protocol ThemeWithSecondary : MaterialTheme  {
    var secondary: Color { get }
    var onSecondary: Color { get }
}

protocol ThemeWithSecondaryVariant : MaterialTheme  {
    var secondaryVariant: Color { get }
    var onSecondaryVariant: Color { get }
}

protocol ThemeWithBackground : MaterialTheme  {
    var background: Color { get }
    var onBackground: Color { get }
}

protocol ThemeWithSurface : MaterialTheme  {
    var surface: Color { get }
    var onSurface: Color { get }
}

struct Themes {
    
    struct MachineFrame : ThemeWithPrimary, ThemeWithPrimaryVariant, ThemeWithSurface, ThemeWithBackground {
        private static let onDefault = Color.white
        
        let primary = Color.black
        let primaryVariant = Color.black.opacity(0.7)
        let surface = Color.black.opacity(0.9)
        let background = Color.black
        let onBackground = ColorsPallete.whiteDirty
        
        let onPrimary = onDefault
        let onPrimaryVariant = onDefault
        let onSurface = onDefault
    }
    
    struct CircularButton : ThemeWithBackground {
        let background: Color = ColorsPallete.whiteDirty
        let onBackground: Color = ColorsPallete.grayDark
    }
    
    struct Display : ThemeWithBackground {
        let background: Color = ColorsPallete.greenDark
        let onBackground: Color = ColorsPallete.greenLight
    }
    
    struct CoffeeSlot : ThemeWithPrimary, ThemeWithPrimaryVariant, ThemeWithSecondary, ThemeWithSecondaryVariant {
        private static let onDefault = Color.white
        
        let primary = Color.black
        let primaryVariant = ColorsPallete.grayDark
        let secondary = ColorsPallete.grayDark.opacity(0.9)
        let secondaryVariant = ColorsPallete.gray
        
        let onPrimary = onDefault
        let onPrimaryVariant = onDefault
        let onSecondary = onDefault
        let onSecondaryVariant = onDefault
    }
    
    struct CoffeeStream : ThemeWithPrimary, ThemeWithSecondary, ThemeWithSecondaryVariant, ThemeWithBackground {
        private static let onDefault = Color.white
        
        let primary = ColorsPallete.brown
        let secondary = ColorsPallete.brownDark
        let secondaryVariant = ColorsPallete.brownDark.opacity(0.7)
        let background: Color = Color.black
        
        let primaryVariant = onDefault
        let onBackground: Color = onDefault
        let onPrimary = onDefault
        let onSecondary = onDefault
        let onSecondaryVariant = onDefault
    }
    
    struct Cup : ThemeWithPrimary, ThemeWithSecondary {
        private static let onDefault = Color.white
        
        let primary = ColorsPallete.whiteDirty
        let onPrimary = ColorsPallete.redCandy
        let secondary = ColorsPallete.grayDark
        
        let onSecondary = onDefault
    }
}
