package nz.ac.uclive.pob16.textify

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.core.net.toUri

import nz.ac.uclive.pob16.textify.databinding.PreviewBinding
import nz.ac.uclive.pob16.textify.helper.Animate


class Preview : AppCompatActivity() {
    private lateinit var viewBinding: PreviewBinding
    private var visionText: String? = null
    private var previewImageURI: Uri? = null
    private var animate = Animate(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.title_activity_preview)
        viewBinding = PreviewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        visionText = intent.getStringExtra("visionText")
        previewImageURI = intent.getStringExtra("previewImageURI")?.toUri()

        viewBinding.editButton.setOnClickListener{openEditTextWindow()}

        viewBinding.shareLink.setOnClickListener{openShareDialog()}

        if (visionText != null && previewImageURI != null) {
            viewBinding.processedText.text = visionText
            viewBinding.previewImage.setImageURI(previewImageURI)

        }
    }

    private fun openEditTextWindow() {
        val intent = Intent(this, EditVisionText::class.java)
        intent.putExtra("visionText", visionText)
        intent.putExtra("previewImageURI", previewImageURI.toString())
        startActivity(intent)
        animate.goForward()
    }

    private fun openShareDialog() {
        val sharedDialog = ShareDialog(this, visionText, previewImageURI)
        sharedDialog.show()
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        animate.goBack()
    }
}