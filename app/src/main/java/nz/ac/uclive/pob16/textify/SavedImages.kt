package nz.ac.uclive.pob16.textify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import nz.ac.uclive.pob16.textify.databinding.ActivitySavedImagesBinding

class SavedImages : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySavedImagesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySavedImagesBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}