package com.mona.nasa_pictures.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.mona.nasa_pictures.Adapter.PicturesAdapter
import com.mona.nasa_pictures.Model.PictureDetails
import com.mona.nasa_pictures.R
import com.mona.nasa_pictures.ViewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

const val INTENT_PICTURE_LIST = "pictureData"
const val INTENT_CURRENT_POSITION = "currentPosition"
const val JSON_FILE_NAME = "picture_data.json"

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var pictureList: ArrayList<PictureDetails>
    private lateinit var picturesAdapter: PicturesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        viewModel.getPictures()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        picturesAdapter = PicturesAdapter(ArrayList())
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = picturesAdapter
        }

        picturesAdapter.notifyDataSetChanged()
        picturesAdapter.onImageClick = {
            val intent = Intent(this@MainActivity, PictureDetailsActivity::class.java).apply {
                putExtra(INTENT_PICTURE_LIST, pictureList)
                putExtra(INTENT_CURRENT_POSITION, it)
            }
            startActivity(intent)
        }

        viewModel.pictures.observe(this, Observer {
            if (it == null)
                return@Observer

            it.reverse()
            pictureList = it
            picturesAdapter.dataSource = pictureList
            picturesAdapter.notifyDataSetChanged()
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                progressView.visibility = View.VISIBLE
                progressView.start()
            } else {
                progressView.visibility = View.GONE
                progressView.stop()
            }
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}