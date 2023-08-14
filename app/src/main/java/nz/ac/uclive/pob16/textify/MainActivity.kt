package nz.ac.uclive.pob16.textify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import nz.ac.uclive.pob16.textify.databinding.ActivityMainBinding
import android.os.Build
import androidx.appcompat.app.AlertDialog
import nz.ac.uclive.pob16.textify.databinding.ChangeLanguageBinding
import nz.ac.uclive.pob16.textify.helper.Animate
import nz.ac.uclive.pob16.textify.helper.ChangeLanguage

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var changeLanguageDialog: AlertDialog
    private lateinit var animate: Animate
    private lateinit var changeLanguageHelper: ChangeLanguage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeLanguageHelper = ChangeLanguage(this)
        changeLanguageHelper.let {
            it.updateLanguage(it.getSavedLanguage(), null) }

        animate = Animate(this)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

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


        changeLanguage.english.setOnClickListener{changeLanguageHelper.updateLanguage("en", changeLanguageDialog)}
        changeLanguage.hindi.setOnClickListener{changeLanguageHelper.updateLanguage("hi", changeLanguageDialog)}
    }

    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            super.onBackPressed()
            animate.goBack()
        }
    }


}
