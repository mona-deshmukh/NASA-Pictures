package com.mona.nasa_pictures.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mona.nasa_pictures.Adapter.PicturesAdapter
import com.mona.nasa_pictures.Model.PictureDetails
import com.mona.nasa_pictures.R
import com.mona.nasa_pictures.Util.Utils
import java.lang.reflect.Type
import kotlinx.android.synthetic.main.activity_main.*

const val INTENT_PICTURE_LIST = "pictureData"
const val INTENT_CURRENT_POSITION = "currentPosition"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchPicturesData()
    }

    private fun fetchPicturesData() {
        val jsonFileString: String = Utils.getJson(this, "picture_data.json")
        Log.i("data", jsonFileString)

        val gson = Gson()
        val listUserType: Type = object : TypeToken<List<PictureDetails>>() {}.type
        val pictureList: ArrayList<PictureDetails> =
            gson.fromJson<List<PictureDetails>>(jsonFileString, listUserType) as ArrayList<PictureDetails>

        val picturesAdapter = PicturesAdapter(pictureList)
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = picturesAdapter
        }

        picturesAdapter.notifyDataSetChanged()

        picturesAdapter.onCamClick = {
            val intent = Intent(this@MainActivity, PictureDetailsActivity::class.java).apply {
                putExtra(INTENT_PICTURE_LIST, pictureList)
                putExtra(INTENT_CURRENT_POSITION, it)
            }
            startActivity(intent)
        }
    }
}