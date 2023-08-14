import SwiftUI
import Shared
import SharedUi

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
            CoffeeSelectorsRepresentable(
                onAny: { if(!isMakingCoffee) { viewModel.makeCoffee() }},
                onSwiftUI: { if(!isMakingCoffee) { navigation.append(Destination.swiftUi) }},
                onCompose: { if(!isMakingCoffee) { navigation.append(Destination.compose) }},
                isMakingCoffee: $isMakingCoffee
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

private struct CoffeeSelectorsRepresentable: UIViewControllerRepresentable {
    
    let onAny: () -> Void
    let onSwiftUI: () -> Void
    let onCompose: () -> Void
    @Binding var isMakingCoffee: Bool
    
    func makeUIViewController(context: Context) -> UIViewController {
        return CoffeeSelectorsUIViewController()
            .composable(
                onAnyClick: onAny,
                onSwiftUiClick: onSwiftUI,
                onComposeClick: onCompose
            )
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        CoffeeSelectorsUIViewController().update(isMakingCoffee: isMakingCoffee)
    }
}

struct ExpressusSelectorScreen_Previews: PreviewProvider {
    static var previews: some View {
        ExpressusSelectorScreen()
            .previewInterfaceOrientation(.portrait)
    }
}
