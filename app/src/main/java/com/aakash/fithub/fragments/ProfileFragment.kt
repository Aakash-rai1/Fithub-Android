package com.aakash.fithub.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aakash.fithub.R
import com.aakash.fithub.api.ServiceBuilder
import com.aakash.fithub.db.UserDB
import com.aakash.fithub.repository.UserRepository
import com.aakash.fithub.ui.EditProfileActivity
import com.aakash.fithub.ui.LoginActivity
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProfileFragment : Fragment() {
    private lateinit var editProfile: Button
    private lateinit var userName: TextView
    private lateinit var useremail: TextView
    private lateinit var userImage: ImageView
    private lateinit var logout: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editProfile = view.findViewById(R.id.editProfile)
        userName = view.findViewById(R.id.userName)
        useremail = view.findViewById(R.id.useremail)
        userImage= view.findViewById(R.id.userImage)
        logout=view.findViewById(R.id.logout)

        CoroutineScope(Dispatchers.IO).launch {
            val repositry = UserRepository()
            val response = repositry.getUser(ServiceBuilder.id!!)
            if (response.success == true) {
                val data = response.data
                val dat=data?.get(0)
                Log.d("Data is: ", response.data!!.toString())
                val name = "Name: ${dat!!.fname} ${dat!!.lname}"
                val email = "Email: ${dat!!.email}"
                val imageurl = dat!!.image

                withContext(Main) {
                    userName.text = name
                    useremail.text = email
                    val imagePath = ServiceBuilder.loadImagepath() + imageurl
                    if (imageurl != null) {
                        if (!imageurl.equals("noimg")!!) {
                            Glide.with(requireActivity())
                                .load(imagePath)
                                .into(userImage)
                        }
                    }

                }

            } else {
                withContext(Main) {
                    Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        editProfile.setOnClickListener {
            activity?.let {
                val intent = Intent(getActivity(), EditProfileActivity::class.java)
                getActivity()?.startActivity(intent)
            }
        }


        logout.setOnClickListener {
//            loadFragment(fragment)
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Do you want to logout?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("yes"){dialogInterface, which->
                val sharePref = requireActivity().getSharedPreferences("MyPref", AppCompatActivity.MODE_PRIVATE )
                val editor = sharePref.edit()
                editor.remove("email")
                editor.remove("password")
                editor.remove("_id")
                        .apply()
                CoroutineScope(Dispatchers.IO).launch{
                    UserDB.getInstance(requireContext()).getUserDAO().logout()
                    withContext(Main){
                        startActivity(Intent(context, LoginActivity::class.java))
                    }
                }
            }
            builder.setNegativeButton("No"){
                dialogInterface, which ->
            }
            builder.show()
        }
    }

}