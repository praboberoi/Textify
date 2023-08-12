package nz.ac.uclive.pob16.textify.helper

import android.net.Uri

data class Image(val path: String, val name: String) {

    override fun toString(): String {
        return "path -> $path name -> $name"
    }
}