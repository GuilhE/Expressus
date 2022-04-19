import SwiftUI
import Combine
import shared

public extension ExpressusViewModel {
    func asObservableObject() -> ExpressusViewModelObservableObject {
        return ExpressusViewModelObservableObject(wrapped: self)
    }
}

public class ExpressusViewModelObservableObject : ObservableObject {
    
    private var wrapped: ExpressusViewModel
    @Published private(set) var state: ExpressusUiState
    
    init(wrapped: ExpressusViewModel) {
        self.wrapped = wrapped
        self.wrapped.handleSavedState(restore: false)
        state = wrapped.state.value as! ExpressusUiState
        (wrapped.state.asPublisher() as AnyPublisher<ExpressusUiState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)
    }
    
    func makeCoffee() {
        wrapped.makeCoffee()
    }
    
    deinit {
        wrapped.onCleared()
    }
}
