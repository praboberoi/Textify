package nz.ac.uclive.pob16.textify.helper

import android.app.Activity
import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AlertDialog
import java.util.Locale

class ChangeLanguage(private var activity: Activity) {

    private var localeManager: LocaleManager? = null

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            localeManager = activity.getSystemService(Context.LOCALE_SERVICE) as LocaleManager
        }
    }

     private fun saveLanguageToPreferences(language: String) {
        val sharedPreferences = activity.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("selected_language", language)
        editor.apply()
    }


    fun getSavedLanguage(): String {
        val sharedPreferences = activity.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("selected_language", "en") ?: "en"
    }

     fun updateLanguage(language: String, changeLanguageDialog : AlertDialog?) {
         changeLanguageDialog?.dismiss()
         saveLanguageToPreferences(language)
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
             if (language == "hi") {
                 localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("hi"))
             } else {
                 localeManager?.applicationLocales = LocaleList(Locale.ENGLISH)
             }

         } else {
             val configuration = activity.resources.configuration
             configuration.locale = Locale(language)
             activity.resources.updateConfiguration(configuration, activity.resources.displayMetrics)
         }
    }

}