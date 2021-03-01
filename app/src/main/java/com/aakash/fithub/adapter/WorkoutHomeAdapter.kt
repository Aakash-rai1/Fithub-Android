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
import com.aakash.fithub.R
import com.aakash.fithub.api.ServiceBuilder
import com.aakash.fithub.entity.Workout
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WorkoutHomeAdapter (
        private val data: ArrayList<Workout>,
        var context: Context
): RecyclerView.Adapter<WorkoutHomeAdapter.HomeViewholder>() {
    private var listWorkout: ArrayList<Workout> = data
    inner class HomeViewholder(val view: View) : RecyclerView.ViewHolder(view) {


        fun bind(workout: Workout, index: Int) {
            val name = view.findViewById<TextView>(R.id.wname)
            val program = view.findViewById<TextView>(R.id.program)
            val imageView = view.findViewById<ImageView>(R.id.imageView)
            val link = view.findViewById<TextView>(R.id.link)
//
            val _id=workout._id
            name.text = workout.wname
            program.text = workout.program
            link.text = workout.link
            val imagePath = ServiceBuilder.loadImagepath() + workout.image
            println(imagePath)
            if (!workout.image.equals(null)) {
                Glide.with(context)
                        .load(imagePath)
                        .fitCenter()
                        .into(imageView)
            }
//            val student=Student(name.text.toString() ,age.text.toString(),gender,address)



        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewholder {
        val view= LayoutInflater.from(parent.context)
                .inflate(R.layout.homeworkoutlayout, parent, false)
        return HomeViewholder(view)
    }

    override fun onBindViewHolder(holder: HomeViewholder, position: Int) {
        holder.bind(listWorkout[position], position)

    }

    override fun getItemCount(): Int {
        return listWorkout.size;
    }



}