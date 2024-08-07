import SwiftUI
import ExpressusComposables

public struct CoffeeSelectorsMobileRepresentable: UIViewControllerRepresentable {
    @Binding var state: CoffeeSelectorsState
    let onAnyClick: () -> Void
    let onSwiftUiClick: () -> Void
    let onComposeClick: () -> Void

    public func makeUIViewController(context: Context) -> UIViewController {
        CoffeeSelectorsMobileUIViewController().make(onAnyClick: onAnyClick, onSwiftUiClick: onSwiftUiClick, onComposeClick: onComposeClick)
    }

    public func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        CoffeeSelectorsMobileUIViewController().update(state: state)
    }
}