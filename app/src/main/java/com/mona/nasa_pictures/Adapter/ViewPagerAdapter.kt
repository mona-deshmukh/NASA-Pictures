package com.mona.nasa_pictures.Adapter

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter;
import com.mona.nasa_pictures.Model.PictureDetails
import com.mona.nasa_pictures.R
import com.squareup.picasso.Picasso
import java.util.Objects

class ViewPagerAdapter(var context: Context, var images: ArrayList<PictureDetails>) : PagerAdapter() {

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj as View
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val itemView: View = layoutInflater.inflate(R.layout.single_picture_details_item, container, false)

        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textDate: TextView = itemView.findViewById(R.id.textDate)
        val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        val textExplanation: TextView = itemView.findViewById(R.id.textExplanation)

        Picasso.get().load(images[position].hdurl).placeholder(R.drawable.product_placeholder)
            .error(R.drawable.product_placeholder).into(imageView)

        textDate.text = "Date : ${images[position].date}"
        textExplanation.text = images[position].explanation
        textTitle.text = images[position].title

        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}