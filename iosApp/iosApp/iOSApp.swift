import SwiftUI
import Shared

@main
struct iOSApp: App {

    init() {
        DependencyInjection().doInitKoin { _ in }        
    }

	var body: some Scene {
		WindowGroup {
            ExpressusScreen()
//            ExpressusComposeScreen()
		}
	}
}
