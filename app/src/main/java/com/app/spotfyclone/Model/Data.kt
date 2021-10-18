package com.app.spotfyclone.Model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("titulo") var title:String = "",
    @SerializedName("albuns") var albuns:List<Album> = arrayListOf()

)

data class Album(
    @SerializedName("url_imagem") var img:String = ""

)

data class Categorys(
    @SerializedName("categoria")
    val categorys: List<Category>
)