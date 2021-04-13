package com.aakash.fithub.response

import com.aakash.fithub.entity.ForFavProduct

data class AddFavResponse (val success:Boolean?=null,
                           val data:List<ForFavProduct>?=null,
                           val msg:String?=null,
                           val id:String?=null){
}