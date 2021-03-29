package com.aakash.fithub.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aakash.fithub.R
import com.aakash.fithub.adapter.WorkoutHomeAdapter
import com.aakash.fithub.db.UserDB
import com.aakash.fithub.entity.Workout
import com.aakash.fithub.repository.WorkOutRepository
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {


    val sampleImages = intArrayOf(
            R.drawable.c1,
            R.drawable.c2,
            R.drawable.c3,
            R.drawable.c4

    )

    val imageListener = ImageListener { position, imageView ->
        imageView.setImageResource(sampleImages[position])

    }

    private lateinit var carouselView: CarouselView
    private lateinit var homeWorkOutrec: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)



        carouselView = view.findViewById(R.id.carousel)

        carouselView.pageCount = sampleImages.size
        carouselView.setImageListener(imageListener)

        homeWorkOutrec = view.findViewById(R.id.homeWorkOutrec)

        getData()
        return view;
    }

    fun getData() {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val WorkOutRepository = WorkOutRepository()
                val response = WorkOutRepository.getWorkOutApiData()
                if (response.success == true) {
                    withContext(Main) {
                        println(response)
                        val workoutitemlist = response.data
                        UserDB.getInstance(requireContext()).getWorkOutDAO().deleteWorkOutData()
                        UserDB.getInstance(requireContext()).getWorkOutDAO().insertWorkOutData(response.data)
                        val adapter = WorkoutHomeAdapter(
                                workoutitemlist as ArrayList<Workout>,
                                requireContext()
                        )
                        homeWorkOutrec.layoutManager = LinearLayoutManager(context)
                        homeWorkOutrec.adapter = adapter
                    }
                }
            }
        } catch (ex: Exception) {
            Toast.makeText(
                    context,
                    "Error : ${ex.toString()}", Toast.LENGTH_SHORT
            ).show()
        }


    }
}
