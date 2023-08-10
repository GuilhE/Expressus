import UIKit
import SwiftUI
import Shared
import SharedUi

struct ExpressusUIViewController: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        return SharedViewControllers().Expressus(isGrinding: true, isPouring: false, label: "Grinding")
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ExpressusComposeScreen: View {
    
    @StateObject private var viewModel = ViewModels().expressusStateViewModel().asObservableObject()
    @State private var isMakingCoffee: Bool = false
    @State private var isPouring: Bool = false
    @State private var isGrinding: Bool = false
    @State private var status: String = ""
    
    private let soundPlayer = SoundPlayer()
    private let vibratorManager = VibratorManager()
    
    var body: some View {
        ExpressusUIViewController()
            .onReceive(viewModel.$state) { new in
                isMakingCoffee = new.isMakingCoffee()
                isGrinding = new.isGrinding
                isPouring = new.isPouring
                status = new.label()
                if(new.isPouring) {
                    soundPlayer.playPouring()
                    vibratorManager.stop()
                } else if(new.isGrinding) {
                    soundPlayer.playGriding()
                    vibratorManager.vibrate()
                }
            }
            .ignoresSafeArea()
    }
}
