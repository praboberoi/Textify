package nz.ac.uclive.pob16.textify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import nz.ac.uclive.pob16.textify.databinding.ActivityMainBinding
import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AlertDialog
import nz.ac.uclive.pob16.textify.databinding.ChangeLanguageBinding
import nz.ac.uclive.pob16.textify.helper.Animate
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private var localeManager: LocaleManager? = null
    private lateinit var changeLanguageDialog: AlertDialog
    private lateinit var animate: Animate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animate = Animate(this)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            localeManager = getSystemService(Context.LOCALE_SERVICE) as LocaleManager
        }

        viewBinding.useCamera.setOnClickListener{ cameraActivity() }
        viewBinding.useSavedImages.setOnClickListener{ savedImagesActivity() }
        viewBinding.changeLanguage.setOnClickListener{ changeLanguageDialog() }

    }

    private fun cameraActivity() {
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
        animate.goForward()
    }

    private fun savedImagesActivity() {
        val intent = Intent(this, SavedImages::class.java)
        startActivity(intent)
        animate.goForward()
    }

    private fun changeLanguageDialog() {
        val builder = AlertDialog.Builder(this)
        val changeLanguage = ChangeLanguageBinding.inflate(layoutInflater)
        builder.setView(changeLanguage.root)
        builder.setCancelable(true)

        val currentAppLanguage = resources.configuration.locale.language
        if (currentAppLanguage == "en") {
            changeLanguage.english.isChecked = true
            changeLanguage.hindi.isChecked = false

            changeLanguage.english.isEnabled = false
        } else {
            changeLanguage.hindi.isChecked = true
            changeLanguage.english.isChecked = false

            changeLanguage.hindi.isEnabled = false
        }
        changeLanguageDialog = builder.create()
        changeLanguageDialog.show()


        changeLanguage.english.setOnClickListener{languageLanguage("en")}
        changeLanguage.hindi.setOnClickListener{languageLanguage("hi")}
    }


    private fun languageLanguage(language: String) {
        changeLanguageDialog.dismiss()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (language == "hi") {
                localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("hi"))
            } else {
                localeManager?.applicationLocales = LocaleList(Locale.ENGLISH)
            }

        } else {
            val configuration = resources.configuration
            configuration.locale = Locale(language)
            resources.updateConfiguration(configuration, resources.displayMetrics)
            onConfigurationChanged(configuration)
            recreate()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        animate.goBack()
    }

}
