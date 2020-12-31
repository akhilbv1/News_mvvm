package org.akiapps.sampletesting

import android.content.ComponentName
import android.content.Intent
import android.content.pm.ComponentInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var appPackageName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAllApps()
    }

    private fun getAllApps(){
        appPackageName =  getString(R.string.app)
        val comp = ComponentName(appPackageName,"com.honeycam.libservice.ui.activity.WelcomeActivity")
        packageManager.setComponentEnabledSetting(comp,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP)
    }
}