package com.mona.nasa_pictures.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mona.nasa_pictures.Adapter.ViewPagerAdapter
import com.mona.nasa_pictures.Model.PictureDetails
import com.mona.nasa_pictures.R
import kotlinx.android.synthetic.main.activity_picture_details.*


class PictureDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_details)

        Toast.makeText(this, "You can swipe left or right", Toast.LENGTH_LONG).show()
        val pictureList = intent.getSerializableExtra(INTENT_PICTURE_LIST) as ArrayList<PictureDetails>

        val viewPagerAdapter = ViewPagerAdapter(this@PictureDetailsActivity, pictureList)
        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = intent.getIntExtra(INTENT_CURRENT_POSITION, 0)
    }
}