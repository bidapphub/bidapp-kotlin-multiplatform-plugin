package io.bidapp.kotlin_multiplatform




class BIDNetworkId {
    private var currentNetworkId : Int? = null
    internal fun setNetwork(networkId : Int) : BIDNetworkId {
        this.currentNetworkId = networkId
        return this
    }
    internal fun getNetwork() : Int {
        return currentNetworkId ?: 0
    }
    companion object {
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
       val Applovin = BIDNetworkId().setNetwork(ApplovinId)
       val ApplovinMax = BIDNetworkId().setNetwork(ApplovinMaxId)
       val Unity = BIDNetworkId().setNetwork(UnityId)
       val Liftoff = BIDNetworkId().setNetwork(LiftoffId)
       val Chartboost = BIDNetworkId().setNetwork(ChartboostId)
       val Admob = BIDNetworkId().setNetwork(AdmobId)
       val StartIo = BIDNetworkId().setNetwork(StartIoId)
       val DigitalTurbine = BIDNetworkId().setNetwork(DigitalTurbineId)
       val Facebook = BIDNetworkId().setNetwork(FacebookId)
       val MyTarget = BIDNetworkId().setNetwork(MyTargetId)
       val Yandex = BIDNetworkId().setNetwork(YandexId)
    }
}
