import SwiftUI
import shared

struct ExpressusScreen: View {
    
    @StateObject private var viewModel = ViewModels().expressusStateViewModel().asObservableObject()
    @State private var isMakingCoffee: Bool = false
    @State private var status: String = ""
    private let soundPlayer = SoundPlayer()
    
    var body: some View {
        ZStack {
            //Themes.MachineFrame.background
            VStack {
                ZStack {
                    Slot(
                        strokeWidth: 30,
                        topOffset: 10,
                        bottomOffset: 10,
                        convexTop: false,
                        flatTop: false,
                        convexBottom: true,
                        flatBottom: true,
                        top: Themes.CoffeeSlot.primary,
                        bottom: Themes.CoffeeSlot.primaryVariant,
                        start: Themes.CoffeeSlot.primary,
                        end: Themes.CoffeeSlot.primary,
                        background: [Themes.CoffeeSlot.primary, Themes.CoffeeSlot.secondary]
                    )
                    .aspectRatio(1, contentMode: .fit)
                    .padding()
                }
                
                Display(text: $status).padding(50)
                CircularButton(size: 70, action: { viewModel.makeCoffee() })
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
            .onReceive(viewModel.$state) { new in
                isMakingCoffee = new.isMakingCoffee()
                status = new.label()
                if(new.isPouring) {
                    soundPlayer.playPouring()
                } else if(new.isGrinding) {
                    soundPlayer.playGriding()
                }
            }
        }
        //.ignoresSafeArea()
    }
}

struct ExpresssusView_Previews: PreviewProvider {
    static var previews: some View {
        ExpressusScreen()
    }
}
