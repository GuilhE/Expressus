import SwiftUI
import shared

struct ExpressusView: View {
    
    @StateObject private var viewModel = ViewModels().expressusStateViewModel().asObservableObject()
    @State private var isMakingCoffee: Bool = false
    private let anim = AnyTransition.opacity.animation(.easeInOut(duration: 0.2))
    
    var body: some View {
        VStack {
            coffeeStatusText
            makeCoffeeButton
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
        .onReceive(viewModel.$state) { new in
            isMakingCoffee = new.isMakingCoffee()
        }
    }
    
    private var coffeeStatusText: some View {
        let label = viewModel.state.label()
        return Text(label)
            .padding(.bottom, 50)
            .font(.title)
            .transition(anim)
            .id("state_" + label)
    }
    
    private var makeCoffeeButton: some View {
        Button("Make Coffee", action: { viewModel.makeCoffee() })
            .disabled(isMakingCoffee)
            .padding(10)
            .overlay(RoundedRectangle(cornerRadius: 4).stroke(Color.blue, lineWidth: 2))
    }
}

struct ExpresssusView_Previews: PreviewProvider {
    static var previews: some View {
        ExpressusView()
    }
}
