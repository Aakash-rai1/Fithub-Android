package com.aakash.fithub.fragments

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aakash.fithub.R
import com.aakash.fithub.adapter.WorkoutHomeAdapter
import com.aakash.fithub.db.UserDB
import com.aakash.fithub.entity.Workout
import com.aakash.fithub.repository.WorkOutRepository
import com.aakash.fithub.ui.PocketMode
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment()
//    ,SensorEventListener
{
//    private lateinit var sensorManager: SensorManager
//    private var sensor: Sensor?=null

    val sampleImages = intArrayOf(
            R.drawable.c1,
            R.drawable.c2,
            R.drawable.c3,
            R.drawable.c4

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    val imageListener = ImageListener { position, imageView ->
        imageView.setImageResource(sampleImages[position])

    }

    private lateinit var carouselView: CarouselView
    private lateinit var homeWorkOutrec: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carouselView = view.findViewById(R.id.carousel)


        carouselView.setImageListener(imageListener)
        carouselView.pageCount = sampleImages.size


        homeWorkOutrec = view.findViewById(R.id.homeWorkOutrec)

//        sensorManager = activity?.getSystemService(AppCompatActivity.SENSOR_SERVICE) as SensorManager
//        if (!checkSensor())
//            return
//        else {
//            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
//            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
//        }
        getData()
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
//    private fun checkSensor(): Boolean {
//        var flag=true
//        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)==null){
//            flag= false
//        }
//        return flag
//
//    }
//    override fun onSensorChanged(event: SensorEvent?) {
//        val values= event!!.values[0]
//        if(values<=4){
//            startActivity(Intent(context, PocketMode::class.java))
//        }
//
//    }
//
//
//    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//
//    }



}
