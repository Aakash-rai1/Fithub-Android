package com.aakash.fithub.fragments

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aakash.fithub.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
//        val mMap= googleMap
//        mMap.addMarker(
//            MarkerOptions().position(LatLng(27.7061949, 85.3300394))
//                .title("Our Office")
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
//
//        )
//
//        mMap.animateCamera(
//            CameraUpdateFactory.newLatLngZoom(LatLng(27.7061949,85.3300394 ), 15F),3000,null
//        )
//        mMap.uiSettings.isZoomControlsEnabled= true


        val Office = LatLng(27.7061949, 85.3300394)
        googleMap.addMarker(MarkerOptions().position(Office).title("Our Office"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Office))
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_maps, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}