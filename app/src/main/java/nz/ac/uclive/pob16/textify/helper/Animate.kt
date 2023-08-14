package nz.ac.uclive.pob16.textify.helper

import android.app.Activity
import android.os.Build
import android.window.OnBackInvokedDispatcher
import nz.ac.uclive.pob16.textify.R


class Animate(private val activity: Activity) {

    init{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            activity.onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) { this.goBack() }
        }
    }

    fun goForward() {
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }


    fun goBack() {
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}