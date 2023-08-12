package nz.ac.uclive.pob16.textify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.loader.content.CursorLoader
import nz.ac.uclive.pob16.textify.databinding.ActivityMainBinding
import nz.ac.uclive.pob16.textify.helper.Image
import android.Manifest
import android.app.LocaleManager
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import nz.ac.uclive.pob16.textify.databinding.ChangeLanguageBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private var localeManager: LocaleManager? = null
    private lateinit var changeLanguageDialog: AlertDialog
    var  REQUEST_CODE = 1234

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            localeManager = getSystemService(Context.LOCALE_SERVICE) as LocaleManager
        }

        viewBinding.useCamera.setOnClickListener{ cameraActivity() }
        viewBinding.useSavedImages.setOnClickListener{ savedImagesActivity() }
        viewBinding.changeLanguage.setOnClickListener{ changeLanguageDialog() }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
            // Request permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_MEDIA_IMAGES), REQUEST_CODE)
            return
        }


    }

    private fun cameraActivity() {
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
    }

    private fun savedImagesActivity() {
        val imageList = mutableListOf<Image>()
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        val selection: String? = null
        val selectionArgs = arrayOf<String>()
        val sortOrder: String? = MediaStore.Images.Media.DATE_MODIFIED

        val cursor = contentResolver.query(
            uri,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )

        cursor?.let {
            val columnIndexData = it.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            val columnIndexFolderName = it.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

            while (it.moveToNext()) {
                val absolutePathOfImage = it.getString(columnIndexData)
                val folderName = it.getString(columnIndexFolderName)

                // Assuming Image is a data class or model class that you have defined
                // Replace with your own logic if necessary
                val image = Image(absolutePathOfImage, folderName)
                imageList.add(image)
                Log.e("TESTING", imageList.toString())
            }


        }
        cursor?.close()

        // TODO: Use imageList to display or process the images further
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

}
