import SwiftUI
import SharedUi

private enum Destination: Hashable {
    case swiftUi
    case compose
}

struct ExpressusSelectorScreen: View {
    
    @State var navigation = NavigationPath()
    
    var body: some View {
        NavigationStack(path: $navigation) {
            CoffeeSelectorsUIViewController(
                onSwiftUI: { navigation.append(Destination.swiftUi) },
                onCompose: { navigation.append(Destination.compose) }
            )
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

private struct CoffeeSelectorsUIViewController: UIViewControllerRepresentable {
    
    let onSwiftUI: () -> Void
    let onCompose: () -> Void
    
    func makeUIViewController(context: Context) -> UIViewController {
        return SharedViewControllers().coffeeSelectors(onSwiftUiClick: onSwiftUI, onComposeClick: onCompose)
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ExpressusSelectorScreen_Previews: PreviewProvider {
    static var previews: some View {
        ExpressusSelectorScreen()
            .previewInterfaceOrientation(.portrait)
    }
}
