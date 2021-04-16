package com.aakash.fithub.fragments

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aakash.fithub.R
import com.aakash.fithub.adapter.FavAdapter
import com.aakash.fithub.api.ServiceBuilder
import com.aakash.fithub.db.UserDB
import com.aakash.fithub.entity.ForFavProduct
import com.aakash.fithub.repository.AddFavrepository
import com.aakash.fithub.repository.WorkOutRepository
import com.aakash.fithub.ui.LightActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private lateinit var favRecycle: RecyclerView;

class FavoriteFragment : Fragment()
//    , SensorEventListener
{

//    private lateinit var sensorManager: SensorManager
//    private var sensor: Sensor?=null


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favRecycle = view.findViewById(R.id.favRecycle);

//        sensorManager = activity?.getSystemService(AppCompatActivity.SENSOR_SERVICE) as SensorManager
//        if (!checkSensor())
//            return
//        else {
//            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
//            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
//        }

        loadvalue()
    }


    private fun loadvalue() {

        CoroutineScope(Dispatchers.IO).launch {
            val repository = AddFavrepository()
            val response = repository.getallFavProdcut(ServiceBuilder.id!!)
            if (response.success == true) {
                val data = response.data
                var favid: String? = null

                UserDB.getInstance(requireContext()).getFavDao().dropTable()
                for (i in data!!.indices) {
                    favid = data[i].productId
                    val workoutRepository = WorkOutRepository()
                    val workoutResponse = workoutRepository.getallProduct(favid!!)
                    if (workoutResponse.success == true) {
                        UserDB.getInstance(requireContext()).getFavDao().AddProdcut(workoutResponse.data)
                    }
                }
                val bookmarkedList = UserDB.getInstance(requireContext()).getFavDao().getproduct()
                withContext(Dispatchers.Main) {
                    val adpater = context?.let { FavAdapter(bookmarkedList as ArrayList<ForFavProduct>, it) }
                    favRecycle.setHasFixedSize(true);
                    favRecycle.layoutManager = LinearLayoutManager(activity)
                    favRecycle.adapter = adpater;
                }
            }
        }
    }

//    private fun checkSensor(): Boolean {
//        var flag=true
//        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)==null){
//            flag= false
//        }
//        return flag
//
//    }
//    override fun onSensorChanged(event: SensorEvent?) {
//        val values= event!!.values[0]
//        if(values<=10000){
//            startActivity(Intent(context, LightActivity::class.java))
//        }
//
//    }
//
//
//    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//
//    }

}