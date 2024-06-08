import AVFoundation

private struct AudioResources {
    static let grinding = "grinding"
    static let pouring = "pouring"
}

struct SoundPlayer {
    
    private let grinding: AVAudioPlayer?
    private let pouring: AVAudioPlayer?
    
    init() {
        guard let grindingURL = Bundle.main.url(forResource: AudioResources.grinding, withExtension: "mp3") else {
            fatalError("Unable to find \(AudioResources.grinding) in bundle")
        }
        do {
            grinding = try AVAudioPlayer(contentsOf: grindingURL)
        } catch {
            grinding = nil
            print(error.localizedDescription)
        }
        
        guard let pouringURL = Bundle.main.url(forResource: AudioResources.pouring, withExtension: "mp3") else {
            fatalError("Unable to find \(AudioResources.pouring) in bundle")
        }
        do {
            pouring = try AVAudioPlayer(contentsOf: pouringURL)
        } catch {
            pouring = nil
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
