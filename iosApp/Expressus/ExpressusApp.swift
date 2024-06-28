import SwiftUI
import ExpressusShared

@main
struct ExpressusApp: App {

    init() {
        DependencyInjection().doInitKoin { _ in }        
    }

	var body: some Scene {
		WindowGroup {
            ExpressusSelectorScreen()            
		}
	}
}
