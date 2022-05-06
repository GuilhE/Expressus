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
