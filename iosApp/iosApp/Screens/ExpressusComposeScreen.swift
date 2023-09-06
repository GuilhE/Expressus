import SwiftUI
import Shared
import SharedComposables

struct ExpressusComposeScreen: View {
    
    @StateObject private var viewModel = ViewModels().expressusStateViewModel().asObservableObject()
    @State private var composableState: ExpressusMobileState = ExpressusMobileState(
        isGrinding: false,
        isPouring: false,
        isMakingCoffee: false,
        status: ""
    )
    
    private let soundPlayer = SoundPlayer()
    private let vibratorManager = VibratorManager()
    
    var body: some View {
        ExpressusRepresentable(
            state: $composableState,
            action: { viewModel.makeCoffee() }
        )
        .onReceive(viewModel.$state) { new in
            composableState = ExpressusMobileState(
                isGrinding: new.isGrinding,
                isPouring: new.isPouring,
                isMakingCoffee: new.isMakingCoffee(),
                status: new.label()
            )
            
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

private struct ExpressusRepresentable: UIViewControllerRepresentable {
    
    @Binding var state: ExpressusMobileState
    let action: () -> Void
    
    func makeUIViewController(context: Context) -> UIViewController {
        return ExpressusMobileUIViewController().make(makeCoffee: action)
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        ExpressusMobileUIViewController().update(state: state)
    }
}

struct ExpressusComposeScreen_Previews: PreviewProvider {
    static var previews: some View {
        ExpressusComposeScreen()
            .previewInterfaceOrientation(.portrait)
    }
}
