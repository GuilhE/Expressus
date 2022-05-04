import SwiftUI
import shared

@main
struct iOSApp: App {

    init() {
        DependencyInjection().doInitKoin { _ in }
    }

	var body: some Scene {
		WindowGroup {
            ExpressusScreen()
		}
	}
}
