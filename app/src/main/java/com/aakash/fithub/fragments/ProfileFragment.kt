package com.aakash.fithub.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.aakash.fithub.EditProfileActivity
import com.aakash.fithub.R
import com.aakash.fithub.`object`.RegisterActivity


class ProfileFragment : Fragment() {
    private lateinit var editProfile: Button



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)

        editProfile= view.findViewById(R.id.editProfile)

        editProfile.setOnClickListener {
            activity?.let{
                val intent = Intent (getActivity(), EditProfileActivity::class.java)
                getActivity()?.startActivity(intent)
            }
        }

        return view
    }



}