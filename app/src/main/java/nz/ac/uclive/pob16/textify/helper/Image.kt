package nz.ac.uclive.pob16.textify.helper

import android.net.Uri

data class Image(val absolutePath: String, val id: Int) {

    override fun toString(): String {
        return "path -> $absolutePath name -> $id"
    }
}