import SwiftUI
import ExpressusShared
import ExpressusComposables

private enum Destination: Hashable {
    case swiftUi
    case compose
}

struct ExpressusSelectorScreen: View {
    
    @StateObject private var viewModel = ViewModels().expressusStateViewModel().asObservableObject()
    @State private var isMakingCoffee: Bool = false
    @State private var navigation = NavigationPath()
    
    private let soundPlayer = SoundPlayer()
    private let vibratorManager = VibratorManager()
    
    var body: some View {
        NavigationStack(path: $navigation) {
            CoffeeSelectorsMobileRepresentable(
                isMakingCoffee: $isMakingCoffee,
                onAnyClick: { if(!isMakingCoffee) { viewModel.makeCoffee() }},
                onSwiftUiClick: { if(!isMakingCoffee) { navigation.append(Destination.swiftUi) }},
                onComposeClick: { if(!isMakingCoffee) { navigation.append(Destination.compose) }}
            )
            .onReceive(viewModel.$state) { new in
                isMakingCoffee = new.isMakingCoffee()
                if(new.isPouring) {
                    soundPlayer.playPouring()
                    vibratorManager.stop()
                } else if(new.isGrinding) {
                    soundPlayer.playGriding()
                    vibratorManager.vibrate()
                }
            }
            .navigationDestination(for: Destination.self) { destination in
                switch destination {
                case .swiftUi:
                    ExpressusScreen()
                case .compose:
                    ExpressusComposeScreen()
                }
            }
            .ignoresSafeArea()
        }
    }
}

#Preview {
    ExpressusSelectorScreen()
}
