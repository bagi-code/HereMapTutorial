package com.bagicode.heremaptutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.here.android.mpa.common.GeoCoordinate
import com.here.android.mpa.common.OnEngineInitListener
import com.here.android.mpa.mapping.AndroidXMapFragment
import com.here.android.mpa.mapping.Map
import java.io.File


class MainActivity : FragmentActivity() {

    private lateinit var map: Map
    private lateinit var mapFragment: AndroidXMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapFragment = supportFragmentManager.findFragmentById(R.id.mapfragment) as AndroidXMapFragment
        com.here.android.mpa.common.MapSettings.setDiskCacheRootPath(
            getApplicationContext().getExternalFilesDir(null).toString()+ File.separator.toString() + ".here-maps");

        mapFragment!!.init(OnEngineInitListener { error ->
            if (error == OnEngineInitListener.Error.NONE) {
                map = mapFragment.getMap()!!
                map.setCenter(GeoCoordinate(37.7397, -121.4252, 0.0), Map.Animation.NONE)
                map.setZoomLevel((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2)
                System.out.println("SUCCESS: Cannot initialize Map Fragment");
            } else {
                System.out.println("ERROR: Cannot initialize Map Fragment");
            }
        })
    }
}