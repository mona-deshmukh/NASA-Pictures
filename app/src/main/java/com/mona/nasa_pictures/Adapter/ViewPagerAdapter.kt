package com.mona.nasa_pictures.Adapter

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.mona.nasa_pictures.Model.PictureDetails
import com.mona.nasa_pictures.R
import com.squareup.picasso.Picasso
import java.util.Objects;


class ViewPagerAdapter(var context: Context, var images: ArrayList<PictureDetails>) : PagerAdapter() {

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val itemView: View = layoutInflater.inflate(R.layout.item, container, false)

        val imageView: ImageView = itemView.findViewById(R.id.imageView) as ImageView

        Picasso.get().load(images[position].url).into(imageView)

        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout?)
    }
}