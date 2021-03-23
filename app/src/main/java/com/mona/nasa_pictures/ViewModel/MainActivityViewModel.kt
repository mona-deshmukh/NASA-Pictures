package com.mona.nasa_pictures.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mona.nasa_pictures.Activity.JSON_FILE_NAME
import com.mona.nasa_pictures.Model.PictureDetails
import com.mona.nasa_pictures.Util.Utils
import java.lang.reflect.Type


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var _pictures: MutableLiveData<ArrayList<PictureDetails>>? = null

    val pictures: MutableLiveData<ArrayList<PictureDetails>>
        get() {
            if (_pictures == null) {
                _pictures = MutableLiveData()
            }

            return _pictures!!
        }

    private var _loading: MutableLiveData<Boolean>? = null

    val loading: MutableLiveData<Boolean>
        get() {
            if (_loading == null) {
                _loading = MutableLiveData()
            }

            return _loading!!
        }

    private var _error: MutableLiveData<String>? = null

    val error: MutableLiveData<String>
        get() {
            if (_error == null) {
                _error = MutableLiveData()
            }

            return _error!!
        }

    fun getPictures() {
        _loading?.postValue(true)

        try {
            val jsonFileString: String = Utils.getJson(getApplication(), JSON_FILE_NAME)
            Log.i("data", jsonFileString)

            val listUserType: Type = object : TypeToken<List<PictureDetails>>() {}.type
            val pictureList: ArrayList<PictureDetails> =
                Gson().fromJson<List<PictureDetails>>(
                    jsonFileString,
                    listUserType
                ) as ArrayList<PictureDetails>

            _pictures?.postValue(pictureList)
        } catch (e: Exception) {
            _error?.postValue("Sorry. Please try again later")
        }

        _loading?.postValue(false)
    }

}