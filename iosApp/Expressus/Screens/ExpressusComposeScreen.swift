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
        ExpressusMobileRepresentable(
            state: $composableState,
            makeCoffee: { viewModel.makeCoffee() }
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

#Preview {
    ExpressusComposeScreen()
}
