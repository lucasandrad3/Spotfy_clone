package com.app.spotfyclone.Model

import com.app.spotfyclone.R
import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("titulo") var title:String = "",
    @SerializedName("albuns") var albuns:List<Album> = arrayListOf()

)

data class Album(
    @SerializedName("url_imagem") var img:String = "",
    @SerializedName("id") var id:Int = 0


)

data class Categorys(
    @SerializedName("categoria")
    val categorys: List<Category>
)

//-------------------------------------------------------
//Search

data class Secao(
    var imgSection:Int= 0,
    var nameSection:String = ""
)



