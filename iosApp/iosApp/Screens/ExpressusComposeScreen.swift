import UIKit
import SwiftUI
import Shared
import SharedUi

struct ExpressusUIViewController: UIViewControllerRepresentable {
    
    @Binding var grinding: Bool
    @Binding var pouring: Bool
    @Binding var makingCoffee: Bool
    @Binding var status: String
    let action: () -> Void
    
    func makeUIViewController(context: Context) -> UIViewController {
        return SharedViewControllers().Expressus(isGrinding: grinding, isPouring: pouring, isMakingCoffee: makingCoffee, status: status, makeCoffee: action)
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
        ExpressusUIViewController(grinding: $isGrinding, pouring: $isPouring, makingCoffee: $isMakingCoffee, status: $status, action: { viewModel.makeCoffee() })
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
