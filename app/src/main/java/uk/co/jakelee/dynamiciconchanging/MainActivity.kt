package uk.co.jakelee.dynamiciconchanging

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    enum class ICON_COLOUR { RED, GREEN, BLUE }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        red.setOnClickListener { setIcon(ICON_COLOUR.RED) }
        green.setOnClickListener { setIcon(ICON_COLOUR.GREEN) }
        blue.setOnClickListener { setIcon(ICON_COLOUR.BLUE) }
    }

    private fun setIcon(targetColour: ICON_COLOUR) {
        for (value in ICON_COLOUR.values()) {
            packageManager.setComponentEnabledSetting(
                ComponentName(BuildConfig.APPLICATION_ID, "${BuildConfig.APPLICATION_ID}.${value.name}"),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP
            )
        }
        for (value in ICON_COLOUR.values()) {
            if (value != targetColour) {
                packageManager.setComponentEnabledSetting(
                    ComponentName(BuildConfig.APPLICATION_ID, "${BuildConfig.APPLICATION_ID}.${value.name}"),
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP
                )
            }
        }
    }
}
