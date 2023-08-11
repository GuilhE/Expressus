import SwiftUI
import SharedUi

struct ExpressusSelectorScreen: View {
    var body: some View {
        CoffeeSelectorsUIViewController(
            onSwiftUI: {},
            onCompose: {}
        )
        .ignoresSafeArea()
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
