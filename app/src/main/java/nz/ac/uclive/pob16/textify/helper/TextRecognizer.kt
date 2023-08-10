package nz.ac.uclive.pob16.textify.helper

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.camera.core.ImageCapture
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.tasks.await
import java.io.IOException
import java.lang.Exception

class TextRecognizer  {
    private var TAG = "TextRecognizer"
     lateinit var visionText: String
    private fun degreesToFirebaseRotation(degrees: Int): Int = when(degrees) {
        0 -> FirebaseVisionImageMetadata.ROTATION_0
        90 -> FirebaseVisionImageMetadata.ROTATION_90
        180 -> FirebaseVisionImageMetadata.ROTATION_180
        270 -> FirebaseVisionImageMetadata.ROTATION_270
        else -> throw Exception("Rotation must be 0, 90, 180, or 270.")
    }


    suspend fun analyze(uri: Uri?, context: Context) {
        if (uri != null) {
            try {
                val image = FirebaseVisionImage.fromFilePath(context, uri)
                val detector = FirebaseVision.getInstance().onDeviceTextRecognizer

                visionText = detector.processImage(image).await().text

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}

