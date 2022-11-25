package com.pepivsky.nasaalbum.model

//import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
// 36:05
@Serializable
data class PhotoResponse(@SerialName("id") val id: String,@SerialName("img_src") val imageUrl: String)
//data class PhotoResponse(@SerializedName("id") val id: String,@SerializedName("img_src") val imageUrl: String)
