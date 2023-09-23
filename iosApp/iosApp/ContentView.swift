import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel

	var body: some View {
        Text(viewModel.text)
	}
}

extension ContentView {
    class ViewModel : ObservableObject {
        @Published var text: String = "Loading..."
        
        init() {
            Greeting().greet { greeting, error in
                DispatchQueue.main.async {
                    if let g = greeting {
                        self.text = g
                    } else {
                        self.text = error?.localizedDescription ?? "error"
                    }
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView(viewModel: ContentView.ViewModel())
	}
}
