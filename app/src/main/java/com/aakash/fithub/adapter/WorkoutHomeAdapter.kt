package com.aakash.fithub.adapter

import android.content.Context
import android.content.Intent
import android.provider.SyncStateContract.Helpers.update
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.aakash.fithub.EditProfileActivity
import com.aakash.fithub.R
import com.aakash.fithub.YoutubePlayerActivity
import com.aakash.fithub.api.ServiceBuilder
import com.aakash.fithub.entity.Workout
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
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
         init {
             name= view.findViewById(R.id.wname)
             program= view.findViewById(R.id.program)
             imageView=view.findViewById(R.id.imageView)
         }


        init {
            itemView.setOnClickListener{view:View->
                val position: Int= adapterPosition
                if (position==0){
                    Toast.makeText(view.context,"0",Toast.LENGTH_SHORT).show()
//                    val intent = Intent (view.context, YoutubePlayerActivity::class.java)
//                    view.context.startActivity(intent)
                }

                if (position==1){
                    Toast.makeText(view.context,"1",Toast.LENGTH_SHORT).show()
                }
                if (position==3){
                    val intent = Intent (view.context, YoutubePlayerActivity::class.java)
                    view.context.startActivity(intent)
                }



//                    val intent = Intent (view.context, YoutubePlayerActivity::class.java)
//
//                    view.context.startActivity(intent)

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


    }

    override fun getItemCount(): Int {
        return listWorkout.size;
    }





}