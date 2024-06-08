import SwiftUI
import Shared

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
