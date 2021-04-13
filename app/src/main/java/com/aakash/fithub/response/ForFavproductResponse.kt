package com.aakash.fithub.response

import com.aakash.fithub.entity.AddFav

data class ForFavproductReponse (
        val success:Boolean?=null,
        val msg:String?=null,
        val data:List<AddFav>?=null,
        val id:String?=null
)