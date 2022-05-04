import SwiftUI
import shared

struct ExpressusScreen: View {
    
    @StateObject private var viewModel = ViewModels().expressusStateViewModel().asObservableObject()
    @State private var isMakingCoffee: Bool = false
    @State private var status: String = ""
    
    var body: some View {
        VStack {
            Display(text: $status).padding(50)
            CircularButton(size: 70, action: { viewModel.makeCoffee() })
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
        .onReceive(viewModel.$state) { new in
            isMakingCoffee = new.isMakingCoffee()
            status = new.label()
        }
    }
}

struct ExpresssusView_Previews: PreviewProvider {
    static var previews: some View {
        ExpressusScreen()
    }
}
