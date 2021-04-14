package com.aakash.fithub.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aakash.fithub.R
import com.aakash.fithub.adapter.FavAdapter
import com.aakash.fithub.api.ServiceBuilder
import com.aakash.fithub.db.UserDB
import com.aakash.fithub.entity.ForFavProduct
import com.aakash.fithub.repository.AddFavrepository
import com.aakash.fithub.repository.WorkOutRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var favRecycle: RecyclerView;

class FavorateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favRecycle = view.findViewById(R.id.favRecycle);
        loadvlaue()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FavorateFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    private fun loadvlaue() {

        CoroutineScope(Dispatchers.IO).launch {
            val repository = AddFavrepository()
            val response = repository.getallFavProdcut(ServiceBuilder.id!!)
            if (response.success == true) {
                val data = response.data
                var allnoteid: String? = null

                UserDB.getInstance(requireContext()).getFavDao().dropTable()
                for (i in data!!.indices) {
                    allnoteid = data[i].productId
                    val noteRepository = WorkOutRepository()
                    val noteResponse = noteRepository.getallProduct(allnoteid!!)
                    if (noteResponse.success == true) {
                        UserDB.getInstance(requireContext()).getFavDao().AddProdcut(noteResponse.data)
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
}