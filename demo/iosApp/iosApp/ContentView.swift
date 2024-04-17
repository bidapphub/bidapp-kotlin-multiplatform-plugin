import SwiftUI
import shared
import AppTrackingTransparency


struct ComposeView: UIViewControllerRepresentable {
    @State private var bidappAdsData = BIDAppAdsData()
    init() {
            setupAds()
        }
    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController(bidappAdsData: bidappAdsData)
    }
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
 
    
    private func setupAds() {
        if #available(iOS 14, *) {
            let deadlineTime = DispatchTime.now() + .seconds(1)
            DispatchQueue.main.asyncAfter(deadline: deadlineTime) {
                ATTrackingManager.requestTrackingAuthorization { _ in
                    BIDAppInitKt.bidappInit(activity: nil)
                }
            }
        } else {
            BIDAppInitKt.bidappInit(activity: nil)
        }
        bidappAdsData.initialization(activity: nil)
    }
}

struct ContentView: View {
    var body: some View {
        ComposeView()
               
    }
}






