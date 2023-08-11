import SwiftUI
import SharedUi

struct ExpressusSelectorScreen: View {
    
    @State private var isActiveA = false
    @State private var isActiveB = false
    
    var body: some View {
        NavigationView {
            ZStack {
                NavigationLink("", destination: ExpressusScreen(), isActive: $isActiveA)
                NavigationLink("", destination: ExpressusComposeScreen(), isActive: $isActiveB)
                CoffeeSelectorsUIViewController(
                    onSwiftUI: { isActiveA = true},
                    onCompose: { isActiveB = true}
                )
            }.ignoresSafeArea()
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
