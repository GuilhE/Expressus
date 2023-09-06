import SwiftUI
import Shared
import SharedComposables

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
            CoffeeSelectorsRepresentable(
                onAny: { if(!composableState.isMakingCoffee) { viewModel.makeCoffee() }},
                onSwiftUI: { if(!composableState.isMakingCoffee) { navigation.append(Destination.swiftUi) }},
                onCompose: { if(!composableState.isMakingCoffee) { navigation.append(Destination.compose) }},
                state: $composableState
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

private struct CoffeeSelectorsRepresentable: UIViewControllerRepresentable {
    
    let onAny: () -> Void
    let onSwiftUI: () -> Void
    let onCompose: () -> Void
    @Binding var state: CoffeeSelectorsState
    
    func makeUIViewController(context: Context) -> UIViewController {
        return CoffeeSelectorsMobileUIViewController()
            .make(
                onAnyClick: onAny,
                onSwiftUiClick: onSwiftUI,
                onComposeClick: onCompose
            )
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        CoffeeSelectorsMobileUIViewController().update(state: state)
    }
}

struct ExpressusSelectorScreen_Previews: PreviewProvider {
    static var previews: some View {
        ExpressusSelectorScreen()
            .previewInterfaceOrientation(.portrait)
    }
}
