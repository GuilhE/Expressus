import SwiftUI
import AVFoundation

struct FontRecources {
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

private struct ColorsPallete {
    static let whiteDirty = Color(0xFFFAF0)
    static let grayDark = Color(0x444444)
    static let greenDark = Color(0x00323C)
    static let greenLight = Color(0x58C7C7)
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

struct Themes {
    
    struct MachineFrame {
        static let primary = Color.black
        static let primaryVariant = Color(0x000000, alpha: 0.7)
        static let surface = Color(0x000000, alpha: 0.9)
        static let background = Color.black
        static let onBackground = ColorsPallete.whiteDirty
    }
    
    struct CircularButton {
        static let background: Color = ColorsPallete.whiteDirty
        static let onBackground: Color = ColorsPallete.grayDark
    }
    
    struct Display {        
        static let background: Color = ColorsPallete.greenDark
        static let onBackground: Color = ColorsPallete.greenLight
    }
}

private struct AudioResources {
    static let grinding = "grinding"
    static let pouring = "pouring"
}

struct SoundPlayer {
    
    private var grinding: AVAudioPlayer? = nil
    private var pouring: AVAudioPlayer? = nil
    
    init() {
        guard let grindingURL = Bundle.main.url(forResource: AudioResources.grinding, withExtension: "mp3") else {
            fatalError("Unable to find \(AudioResources.grinding) in bundle")
        }
        do {
            grinding = try AVAudioPlayer(contentsOf: grindingURL)
        } catch {
            print(error.localizedDescription)
        }
        
        guard let pouringURL = Bundle.main.url(forResource: AudioResources.pouring, withExtension: "mp3") else {
            fatalError("Unable to find \(AudioResources.pouring) in bundle")
        }
        do {
            pouring = try AVAudioPlayer(contentsOf: pouringURL)
        } catch {
            print(error.localizedDescription)
        }
    }
    
    func playGriding() {
        grinding.map { player in
            if(!player.isPlaying) {
                player.play()
            }
        }
    }
    
    func playPouring() {
        pouring.map { player in
            if(!player.isPlaying) {
                player.play()
            }
        }
    }
}
