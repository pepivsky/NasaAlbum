package com.pepivsky.nasaalbum.model

import com.google.gson.annotations.SerializedName


data class PhotoResponse(@SerializedName("id") val id: String,@SerializedName("img_src") val imageUrl: String)
