package com.aakash.fithub.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.aakash.fithub.R
import com.aakash.fithub.ui.videos.ChestActivity
import com.aakash.fithub.api.ServiceBuilder
import com.aakash.fithub.entity.AddFav
import com.aakash.fithub.entity.Workout
import com.aakash.fithub.repository.AddFavrepository
import com.aakash.fithub.ui.videos.AbsActivity
import com.aakash.fithub.ui.videos.FullBody
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WorkoutHomeAdapter (
        private val data: ArrayList<Workout>,
        var context: Context
): RecyclerView.Adapter<WorkoutHomeAdapter.HomeViewholder>() {
    private var listWorkout: ArrayList<Workout> = data


     class HomeViewholder(val view: View) : RecyclerView.ViewHolder(view) {
         val name: TextView;
         val program: TextView;
         val imageView: ImageView;
         val imagefav: ImageView;
         init {
             name= view.findViewById(R.id.wname)
             program= view.findViewById(R.id.program)
             imageView=view.findViewById(R.id.imageView)
             imagefav=view.findViewById(R.id.image_fav)

         }


        init {
            itemView.setOnClickListener{view:View->
                val position: Int= adapterPosition
                if (position==0){
                    val intent = Intent (view.context, FullBody::class.java)
                    view.context.startActivity(intent)
                }

                if (position==1){
                    val intent = Intent (view.context, AbsActivity::class.java)
                    view.context.startActivity(intent)
                }

                if (position==2){
                    val intent = Intent (view.context, ChestActivity::class.java)
                    view.context.startActivity(intent)
                }
                if (position==3){
                    val intent = Intent (view.context, ChestActivity::class.java)
                    view.context.startActivity(intent)
                }


            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewholder {
        val view= LayoutInflater.from(parent.context)
                .inflate(R.layout.homeworkoutlayout, parent, false)
        return HomeViewholder(view)
    }

    override fun onBindViewHolder(holder: HomeViewholder, position: Int) {
        val workout=listWorkout[position]
        holder.name.text=workout.wname
        holder.program.text=workout.program
        val _id=workout._id

        val imagePath = ServiceBuilder.loadImagepath() + workout.image
            println(imagePath)
            if (!workout.image.equals("no img")) {
                Glide.with(context)
                        .load(imagePath)
                        .fitCenter()
                        .into(holder.imageView)
            }

        holder.imagefav.setOnClickListener() {
            val builder = AlertDialog.Builder(context);
            builder.setMessage("Do you want add this product to Fav?")
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setPositiveButton("Yes") { dialogInterface, which ->
                CoroutineScope(Dispatchers.IO).launch {
                    val repository = AddFavrepository()
                    val response = repository.AddFav(AddFav(userId = ServiceBuilder.id!!, productId = workout._id))
                    if (response.success == true) {
                        showFavAdd()
                        withContext(Dispatchers.Main) {
                            val snack = Snackbar.make(it, "${response.msg}", Snackbar.LENGTH_SHORT)
                            snack.setAction("Ok") {
                                snack.dismiss()
                            }
                            snack.show()
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context, "error occur", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
            builder.setNegativeButton("No") { dialogInterface, which ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
            holder.imagefav.setBackgroundColor(Color.RED)
        }



    }

    private fun showFavAdd() {
        val notificationManager= NotificationManagerCompat.from(context)

        val notificationChannels= NotificationChannels(context)
        notificationChannels.createNotificationChannels()

        val notification= NotificationCompat.Builder(context, notificationChannels.CHANNEL_1)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Favorite Added")
                .setContentText("Program added to favorites successfully.")
                .setColor(Color.YELLOW)
                .build()
        notificationManager.notify(1, notification)
    }


    override fun getItemCount(): Int {
        return listWorkout.size;
    }





}