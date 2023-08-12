package nz.ac.uclive.pob16.textify

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.net.toUri
import nz.ac.uclive.pob16.textify.databinding.EditVisonTextBinding

class EditVisionText : AppCompatActivity() {
    private lateinit var viewBinding: EditVisonTextBinding
    private var visionText: String? = null
    private var imageURI: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.title_edit)
        viewBinding = EditVisonTextBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        visionText = intent.getStringExtra("visionText")
        imageURI = intent.getStringExtra("previewImageURI")?.toUri()
        viewBinding.visionText.setText(visionText)

        viewBinding.shareLink.setOnClickListener{share()}
    }

    private fun share() {
        visionText = viewBinding.visionText.text.toString()
        val shareDialog = ShareDialog(this, visionText, imageURI)
        shareDialog.show()

    }
}