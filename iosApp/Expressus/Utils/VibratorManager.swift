import CoreHaptics

struct VibratorManager {
    
    private let engine: CHHapticEngine?
    private let player: CHHapticPatternPlayer?
    private var events = [CHHapticEvent]()
    
    init() {
        guard CHHapticEngine.capabilitiesForHardware().supportsHaptics else {
            engine = nil
            player = nil
            return
        }
        
        do {
            engine = try CHHapticEngine()
            do {
                try engine?.start()
                events.append(
                    CHHapticEvent(
                        eventType: .hapticContinuous,
                        parameters: [CHHapticEventParameter(parameterID: .hapticIntensity, value: 0.5)],
                        relativeTime: 0,
                        duration: 5
                    )
                )                
                player = try engine?.makePlayer(with: CHHapticPattern(events: events, parameters: []))
            } catch {
                player = nil
                print("Unable to start HapticEngine: \(error.localizedDescription)")
            }
        } catch {
            engine = nil
            player = nil
            print("HapticEngine unavailable: \(error.localizedDescription)")
        }
    }
    
    func vibrate() {
        do {
            try player?.start(atTime: 0)
        } catch {
            print("Failed to vibrate: \(error.localizedDescription)")
        }
    }
    
    func stop() {
        do {
            try player?.cancel()
        } catch {
            print("Failed to cancel vibration: \(error.localizedDescription)")
        }
    }
}
