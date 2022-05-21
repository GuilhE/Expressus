import SwiftUI
import shared

struct ExpressusScreen: View {
    
    @StateObject private var viewModel = ViewModels().expressusStateViewModel().asObservableObject()
    @State private var isMakingCoffee: Bool = false
    @State private var isPouring: Bool = false
    @State private var isGrinding: Bool = false
    @State private var status: String = ""
    
    private let soundPlayer = SoundPlayer()
    private let vibratorManager = VibratorManager()
    
    var body: some View {
        MachineFrame {
            VStack {
                CoffeeSlot(grinding: $isGrinding, pouring: $isPouring)
                    .aspectRatio(1, contentMode: .fit)
                    .padding()
                Display(text: $status)
                    .frame(height: 50)
                    .padding(50)
                CircularButton(size: 70, action: { viewModel.makeCoffee() })
                    .disabled(isMakingCoffee)
                    .opacity(isMakingCoffee ? 0.5 : 1)
            }
            .frame(maxWidth: .infinity, alignment: .top)
            .padding()
            .onReceive(viewModel.$state) { new in
                isMakingCoffee = new.isMakingCoffee()
                isGrinding = new.isGrinding
                isPouring = new.isPouring
                status = new.label()
                if(new.isPouring) {
                    soundPlayer.playPouring()
                    vibratorManager.stop()
                } else if(new.isGrinding) {
                    soundPlayer.playGriding()
                    vibratorManager.vibrate()
                }
            }
        }
        .ignoresSafeArea()
    }
}

struct ExpresssusView_Previews: PreviewProvider {
    static var previews: some View {
        ExpressusScreen()
            .previewInterfaceOrientation(.portrait)
    }
}
