import SwiftUI
import SharedComposables

public struct ExpressusMobileRepresentable: UIViewControllerRepresentable {
    @Binding var state: ExpressusMobileState
    let makeCoffee: () -> Void
    
    public func makeUIViewController(context: Context) -> UIViewController {
        ExpressusMobileUIViewController().make(makeCoffee: makeCoffee)
    }
    
    public func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        ExpressusMobileUIViewController().update(state: state)
    }
}