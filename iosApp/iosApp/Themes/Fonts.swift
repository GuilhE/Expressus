import SwiftUI

struct Fonts {
    static let Led = "LedPanelStationOn" //turns out that using printFontNames() insinde .onAppear() reveals the real name...
    
    static func printFontNames() {
        for family: String in UIFont.familyNames {
            print(family)
            for names: String in UIFont.fontNames(forFamilyName: family) {
                print("== \(names)")
            }
        }
    }
}
