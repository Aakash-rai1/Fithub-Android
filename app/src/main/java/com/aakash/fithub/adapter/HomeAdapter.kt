package com.aakash.fithub.adapter

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aakash.fithub.R
import com.aakash.fithub.api.ServiceBuilder
import com.aakash.fithub.entity.AddFav
import com.aakash.fithub.entity.Workout
import com.aakash.fithub.repository.AddFavrepository
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//class HomeAdapter(
//    val listpost:ArrayList<Workout>,
//    val context: Context
//): RecyclerView.Adapter<HomeAdapter.HomwviewHolder>() {
//    class HomwviewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val image: ImageView
//        val image_fav: ImageView
//        val wname: TextView
//        val program: TextView
//        init {
//            image_fav=view.findViewById(R.id.image_fav)
//            image=view.findViewById(R.id.image)
//
//            wname=view.findViewById(R.id.wname)
//            program=view.findViewById(R.id.program)
//        }
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomwviewHolder {
//        val view= LayoutInflater.from(parent.context)
//            .inflate(R.layout.favproduct,parent,false)
//        return HomeAdapter.HomwviewHolder(view)
//    }
//    override fun onBindViewHolder(holder: HomwviewHolder, position: Int) {
//        val post=listpost[position]
//        holder.wname.text=post.wname
//        holder.program.text=post.program
//
//        val id=post._id
//        val imagePath = ServiceBuilder.loadImagepath() +post.image
//        if (!post.image.equals("noimg")) {
//            Glide.with(context)
//                .load(imagePath)
//                .into(holder.image)
//        }
//        holder.image_fav.setOnClickListener() {
//            val builder = AlertDialog.Builder(context);
//            builder.setMessage("Do you want add this product to Fav.")
//            builder.setIcon(android.R.drawable.ic_dialog_alert);
//            builder.setPositiveButton("Yes") { dialogInterface, which ->
//                CoroutineScope(Dispatchers.IO).launch {
//                    val repository = AddFavrepository()
//                    val response = repository.AddFav(AddFav(userId = ServiceBuilder.id!!, productId = post._id))
//                    if (response.success == true) {
//                        withContext(Dispatchers.Main) {
//                            val snack = Snackbar.make(it, "${response.msg}", Snackbar.LENGTH_SHORT)
//                            snack.setAction("Ok") {
//                                snack.dismiss()
//                            }
//                            snack.show()
//                        }
//                    } else {
//                        withContext(Dispatchers.Main) {
//                            Toast.makeText(context, "error occur", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                }
//            }
//            builder.setNegativeButton("No") { dialogInterface, which ->
//            }
//            val alertDialog: AlertDialog = builder.create()
//            alertDialog.setCancelable(false)
//            alertDialog.show()
//            holder.image_fav.setBackgroundColor(Color.RED)
//        }
//    }
//    override fun getItemCount(): Int {
//        return listpost.size
//    }
//}