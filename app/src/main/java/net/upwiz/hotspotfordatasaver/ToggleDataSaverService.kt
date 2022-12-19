package net.upwiz.hotspotfordatasaver

import android.content.Context
import android.service.quicksettings.TileService
import android.net.wifi.WifiManager
import android.service.quicksettings.Tile
import android.util.Log
import java.lang.reflect.Method

class ToggleDataSaverService : TileService() {

    override fun onTileAdded() {
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        // Handle the tile being added to the Quick Settings panel
        val method: Method = wifiManager.javaClass.getMethod("getWifiApState")
        method.isAccessible = true
        val invoke = method.invoke(wifiManager)

        if (invoke != null) {
            Log.d("added invoke", invoke.toString())
        }

        if (invoke == 13) {
            qsTile.state = Tile.STATE_ACTIVE
        } else {
            qsTile.state = Tile.STATE_INACTIVE
        }
    }

    override fun onTileRemoved() {
        // Handle the tile being removed from the Quick Settings panel
    }

    override fun onClick() {
        // Handle the tile being clicked
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val method: Method = wifiManager.javaClass.getMethod("getWifiApState")
        method.isAccessible = true
        val invoke = method.invoke(wifiManager)

        // log invoke
        if (invoke != null) {
            Log.d("click invoke", invoke.toString())
        }

        if (invoke == 13) {
            qsTile.state = Tile.STATE_ACTIVE
        } else {
            qsTile.state = Tile.STATE_INACTIVE
        }
    }

}