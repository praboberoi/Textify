package nz.ac.uclive.pob16.textify


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import nz.ac.uclive.pob16.textify.databinding.ShareDialogBinding

class ShareDialog(context: Context, private var visionText: String?, private var imageURI: Uri?): AlertDialog(context) {
    private var shareDialogBinding: ShareDialogBinding = ShareDialogBinding.inflate(layoutInflater)

    init {
        this.setView(shareDialogBinding.root)
        this.setCancelable(true)
        this.create()

        shareDialogBinding.share.setOnClickListener { shareResources() }
        shareDialogBinding.cancelButton.setOnClickListener{ dismiss() }
    }


    private fun shareResources() {

        val shareIntent: Intent = Intent().apply{
            action = Intent.ACTION_SEND
            if (shareDialogBinding.includeImageSwitch.isChecked) {
                putExtra(Intent.EXTRA_STREAM, imageURI)
            }

            putExtra(Intent.EXTRA_TEXT, visionText)
            type = "image/jpeg"
        }
        this.dismiss()
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        context.startActivity(Intent.createChooser(shareIntent, "Textify"))

    }
}

