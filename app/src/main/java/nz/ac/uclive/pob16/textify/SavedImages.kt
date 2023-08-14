package nz.ac.uclive.pob16.textify

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider

import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nz.ac.uclive.pob16.textify.databinding.ActivitySavedImagesBinding
import nz.ac.uclive.pob16.textify.databinding.ProgressDialogBinding
import nz.ac.uclive.pob16.textify.helper.Animate
import nz.ac.uclive.pob16.textify.helper.Image
import nz.ac.uclive.pob16.textify.helper.SavedImageAdapter
import nz.ac.uclive.pob16.textify.helper.TextRecognizer
import java.io.File


class SavedImages: AppCompatActivity() {
    private lateinit var viewBinding: ActivitySavedImagesBinding
    private var imageList = mutableListOf<Image>()
    private lateinit var progressDialog: AlertDialog
    private lateinit var animate: Animate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animate = Animate(this)
        title = resources.getString(R.string.saved_images)
        viewBinding = ActivitySavedImagesBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        updateImageList()

        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = SavedImageAdapter(imageList, ::openPreviewView)

        viewBinding.recyclerView.adapter = adapter

        progressDialog = progressDialog()
    }

    private fun updateImageList() {
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


                val image = Image(absolutePathOfImage, it.position)
                imageList.add(image)
            }
        }
        cursor?.close()
    }

    private fun openPreviewView(image: Image) {
        val scope = CoroutineScope(Dispatchers.Main)
        val context = this
        val textRecognizer = TextRecognizer()
        progressDialog.show()
        scope.launch {
            val filePath = File(image.absolutePath).toURI().path


            val shareableUri = FileProvider.getUriForFile(context,
                "${context.packageName}.provider",
                File(filePath))

            textRecognizer.analyze(shareableUri, context)
            val visionText = textRecognizer.visionText

            val intent = Intent(context, Preview::class.java)
            intent.putExtra("visionText", visionText)
            intent.putExtra("previewImageURI", shareableUri.toString())
            startActivity(intent)

            progressDialog.dismiss()
            animate.goForward()
        }

    }

    private fun progressDialog(): AlertDialog {
        val builder = AlertDialog.Builder(this)
        val progressDialog = ProgressDialogBinding.inflate(layoutInflater)
        builder.setView(progressDialog.root)
        builder.setCancelable(false)
        return builder.create()
    }

    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            super.onBackPressed()
            animate.goBack()
        }
    }
}