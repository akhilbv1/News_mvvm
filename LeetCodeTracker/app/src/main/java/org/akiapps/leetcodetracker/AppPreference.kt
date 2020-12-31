package org.akiapps.leetcodetracker

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AppPreference (context: Context){
    private val appContext = context.applicationContext

    private var _sharedPrefs: SharedPreferences
    private var _prefsEditor: SharedPreferences.Editor

     val APP_SHARED_PREFS = "org.akiapps.leetcodetracker.preferences"

    private val PROBLEMCOUNT = "$APP_SHARED_PREFS.ProblemCount"


    fun setCount(count:ArrayList<CountSelectedModel>){
        _prefsEditor.putString(PROBLEMCOUNT,Gson().toJson(count))
        _prefsEditor.commit()
    }

    fun getProblemCountList():ArrayList<CountSelectedModel>{
        val defaultList = getProblemCount()
        val jsonString =  _sharedPrefs.getString(PROBLEMCOUNT, null)
        return  jsonString?.let {
            Gson().fromJson(
                it,
                object : TypeToken<ArrayList<CountSelectedModel>>() {}.type
            )
        }?:defaultList
    }

    init {
        this._sharedPrefs = appContext.getSharedPreferences(
            APP_SHARED_PREFS,
            Activity.MODE_PRIVATE
        )
        this._prefsEditor = _sharedPrefs.edit()
        this._prefsEditor.apply()
    }


}