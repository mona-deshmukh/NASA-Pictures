package com.mona.nasa_pictures.Util

import android.content.Context
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class Utils {
    companion object {
        fun getJson(context: Context, fileName: String): String {
            val jsonString: String
            jsonString = try {
                val inputStream: InputStream = context.getAssets().open(fileName)
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                val charset: Charset = Charsets.UTF_8

                inputStream.read(buffer)
                inputStream.close()
                String(buffer, charset)
            } catch (e: IOException) {
                e.printStackTrace()
                return ""
            }
            return jsonString
        }
    }
}