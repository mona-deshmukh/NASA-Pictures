package com.mona.nasa_pictures.Adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.mona.nasa_pictures.Model.PictureDetails
import com.mona.nasa_pictures.R
import com.squareup.picasso.Picasso

class PicturesAdapter(private var dataSource: List<PictureDetails>) :
    RecyclerView.Adapter<PicturesAdapter.ViewHolder>() {

    var onCamClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.picuters_adapter_item, parent, false)
        val holder = ViewHolder(view)

        holder.imageView.setOnClickListener {
            onCamClick?.invoke(holder.bindingAdapterPosition)
        }
        return holder
    }

    override fun getItemCount() = dataSource.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataSource[position].title

        if (dataSource[position].url.isNullOrEmpty()) {
            holder.imageView.setImageResource(R.drawable.product_placeholder)
        } else {
            Picasso.get().load(dataSource[position].url).placeholder(R.drawable.product_placeholder)
                .error(R.drawable.product_placeholder).into(holder.imageView)
        }
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val imageView: ImageView = itemView.findViewById(R.id.image)
    }
}

