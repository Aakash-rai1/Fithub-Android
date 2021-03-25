package com.aakash.fithub.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.aakash.fithub.EditProfileActivity
import com.aakash.fithub.R
import api.ServiceBuilder
import com.aakash.fithub.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class ProfileFragment : Fragment() {
    private lateinit var editProfile: Button
    private lateinit var userName: TextView
    private lateinit var userAge: TextView
    private lateinit var UserGender: TextView



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)



        editProfile= view.findViewById(R.id.editProfile)
        userName= view.findViewById(R.id.userName)
        userAge= view.findViewById(R.id.userAge)
        UserGender= view.findViewById(R.id.UserGender)

        CoroutineScope(Dispatchers.IO).launch {
            try{
                Log.d("Id is",ServiceBuilder.id)
                val repositry=UserRepository()
                val response=repositry.getUser(ServiceBuilder.id!!)
                if(response.success==true){
                    val data=response.data
                    Log.d("Data is: ",response.data!!.toString())
                    val name="${data!!.fname} ${data!!.lname}"

                    withContext(Main){
                        userName.text=name
                    }

                }
                else{
                    withContext(Main){
                        Toast.makeText(context , response.message, Toast.LENGTH_SHORT).show()
                    }
            }

            }catch (e:Exception){
                withContext(Main){
                    Toast.makeText(context , "Here", Toast.LENGTH_SHORT).show()
                }
            }
        }

        editProfile.setOnClickListener {
            activity?.let{
                val intent = Intent (getActivity(), EditProfileActivity::class.java)
                getActivity()?.startActivity(intent)
            }
        }

        return view
    }



}