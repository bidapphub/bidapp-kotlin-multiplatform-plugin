package io.bidapp.kmp




public class BIDNetworkId {
    private var currentNetworkId : Int? = null
    internal fun setNetwork(networkId : Int) : BIDNetworkId {
        this.currentNetworkId = networkId
        return this
    }
    internal fun getNetwork() : Int {
        return currentNetworkId ?: 0
    }
    public companion object {
       private const val ApplovinId = 1
       private const val ApplovinMaxId = 2
       private const val UnityId = 3
       private const val LiftoffId = 4
       private const val ChartboostId = 6
       private const val AdmobId = 7
       private const val StartIoId = 8
       private const val DigitalTurbineId = 9
       private const val FacebookId = 10
       private const val MyTargetId = 11
       private const val YandexId = 12
       public val Applovin: BIDNetworkId = BIDNetworkId().setNetwork(ApplovinId)
       public val ApplovinMax: BIDNetworkId = BIDNetworkId().setNetwork(ApplovinMaxId)
       public val Unity: BIDNetworkId = BIDNetworkId().setNetwork(UnityId)
       public val Liftoff: BIDNetworkId = BIDNetworkId().setNetwork(LiftoffId)
       public val Chartboost: BIDNetworkId = BIDNetworkId().setNetwork(ChartboostId)
       public val Admob: BIDNetworkId = BIDNetworkId().setNetwork(AdmobId)
       public val StartIo: BIDNetworkId = BIDNetworkId().setNetwork(StartIoId)
       public val DigitalTurbine: BIDNetworkId = BIDNetworkId().setNetwork(DigitalTurbineId)
       public val Facebook: BIDNetworkId = BIDNetworkId().setNetwork(FacebookId)
       public val MyTarget: BIDNetworkId = BIDNetworkId().setNetwork(MyTargetId)
       public val Yandex: BIDNetworkId = BIDNetworkId().setNetwork(YandexId)
    }
}
