package com.mona.nasa_pictures.Model

import java.io.Serializable

data class PictureDetails(
    var copyright: String,
    var media_type: String,
    var url: String?,
    var date: String,
    var explanation: String,
    var hdurl: String?,
    var service_version: String,
    var title: String?
) : Serializable