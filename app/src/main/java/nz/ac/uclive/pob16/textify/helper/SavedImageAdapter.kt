package nz.ac.uclive.pob16.textify.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import nz.ac.uclive.pob16.textify.R


class SavedImageAdapter(
    private val imageList: List<Image>,
    private val onImageClickListener: (Image) -> Unit):
    RecyclerView.Adapter<SavedImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = imageList[position]

        Glide.with(holder.imageView.context)
            .load(image.absolutePath)
            .override(1000, 1000)
            .into(holder.imageView)

        holder.imageView.setOnClickListener {onImageClickListener(image)}
    }

    override fun getItemCount(): Int {
        return imageList.size
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

}