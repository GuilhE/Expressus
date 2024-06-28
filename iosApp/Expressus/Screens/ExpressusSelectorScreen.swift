import SwiftUI
import ExpressusShared
import ExpressusComposables

private enum Destination: Hashable {
    case swiftUi
    case compose
}

struct ExpressusSelectorScreen: View {
    
    @StateObject private var viewModel = ViewModels().expressusStateViewModel().asObservableObject()
    @State private var composableState: CoffeeSelectorsState = CoffeeSelectorsState(isMakingCoffee: false)
    @State private var navigation = NavigationPath()
    
    private let soundPlayer = SoundPlayer()
    private let vibratorManager = VibratorManager()
    
    var body: some View {
        NavigationStack(path: $navigation) {
            CoffeeSelectorsMobileRepresentable(
                state: $composableState,
                onAnyClick: { if(!composableState.isMakingCoffee) { viewModel.makeCoffee() }},
                onSwiftUiClick: { if(!composableState.isMakingCoffee) { navigation.append(Destination.swiftUi) }},
                onComposeClick: { if(!composableState.isMakingCoffee) { navigation.append(Destination.compose) }}
            )
            .onReceive(viewModel.$state) { new in
                composableState = CoffeeSelectorsState(isMakingCoffee: new.isMakingCoffee())
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
