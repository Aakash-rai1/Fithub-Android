package com.aakash.fithub.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aakash.fithub.R
import com.aakash.fithub.api.ServiceBuilder
import com.aakash.fithub.entity.ForFavProduct
import com.aakash.fithub.repository.AddFavrepository
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavAdapter (val listpost:ArrayList<ForFavProduct>,
                  val context: Context): RecyclerView.Adapter<FavAdapter.FavviewHolder>() {
    class FavviewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView
        val removeFav: ImageView
        val wname: TextView
        val program: TextView

        init {
            image=view.findViewById(R.id.image12)
            removeFav=view.findViewById(R.id.removeFav)
            wname=view.findViewById(R.id.wname)
            program=view.findViewById(R.id.program)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavviewHolder {
        val view= LayoutInflater.from(parent.context)
                .inflate(R.layout.favproduct,parent,false)

        return FavAdapter.FavviewHolder(view)


    }

    override fun onBindViewHolder(holder: FavviewHolder, position: Int) {
        val fav = listpost[position]
        holder.wname.text="Area:"+fav.wname
        holder.program.text="price:"+fav.program

        val imagePath = ServiceBuilder.loadImagepath() + fav.image
        if (!fav.image.equals("noimg")) {
            Glide.with(context)
                    .load(imagePath)
                    .into(holder.image)
        }

        holder.removeFav.setOnClickListener(){
            val builder= AlertDialog.Builder(context);
            builder.setMessage("Do you want remove from fav")
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setPositiveButton("Yes"){dialogInterface,which->
                CoroutineScope(Dispatchers.IO).launch {
                    val repository= AddFavrepository()
                    val response=repository.deleteFavProduct(fav._id!!)
                    if(response.success==true){
                        withContext(Dispatchers.Main){
                            listpost.removeAt(position)
                            notifyDataSetChanged()
                            val snack=  Snackbar.make(it,"${response.msg}.  remove from Fav", Snackbar.LENGTH_SHORT)
                            snack.setAction("Ok") {
                                snack.dismiss()
                            }
                            snack.show()

                        }
                    }
                }
            }
            builder.setNegativeButton("No"){
                dialogInterface,which->
            }
            builder.show()
        }
    }

    override fun getItemCount(): Int {
        return listpost.size
    }
}