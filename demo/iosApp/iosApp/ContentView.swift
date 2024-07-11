import SwiftUI
import shared
import AppTrackingTransparency


struct ComposeView: UIViewControllerRepresentable {
    init() {
            setupAds()
        }
    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController(bidappAdsData: BIDAppAdsData())
    }
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
 
    
    private func setupAds() {
        if #available(iOS 14, *) {
            let deadlineTime = DispatchTime.now() + .seconds(1)
            DispatchQueue.main.asyncAfter(deadline: deadlineTime) {
                ATTrackingManager.requestTrackingAuthorization { _ in
                    BIDAppAds.shared.start()
                }
            }
        } else {
            BIDAppAds.shared.start()
        }
    }
}

struct ContentView: View {
    var body: some View {
        ComposeView()
               
    }
}






