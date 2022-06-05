package com.se7sopro.advaegchallange.presenter.view.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.se7sopro.advaegchallange.data.model.*
import com.se7sopro.advaegchallange.data.remote.ViewState
import com.se7sopro.advaegchallange.databinding.FragmentMainBinding
import com.se7sopro.advaegchallange.presenter.view.ui.adapter.PhotosRecyclerViewAdapter
import com.se7sopro.advaegchallange.presenter.view.ui.fullView.FullImageActivity
import com.se7sopro.advaegchallange.presenter.viewModels.ViewModelMain
import com.se7sopro.advaegchallange.utils.listeners.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), ItemClickListener<PhotoModel> {

    private lateinit var photosRecyclerViewAdapter: PhotosRecyclerViewAdapter

    private val viewModel: ViewModelMain by viewModels()
    private lateinit var binding: FragmentMainBinding
    var photoList = ArrayList<PhotoModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        viewModel.getPhotos()
        setUpRecyclerView()
        viewModel.getCurrencyDataStateFlow.observe(this, { state ->
            when (state) {
                is ViewState.Loading -> {
                    showLoading(state.isVisible)
                }
                is ViewState.Success -> {
                    showLoading(false)
                    handlePhotosResponse(state.data as ArrayList<PhotoModel>)
                }
                is ViewState.DataError -> {
                    showLoading(false)
                    Log.e("Map", "key: error - value: ${state.data as String} \n")
                    handleErrorResponse(state.data)
                }
                is ViewState.GeneralError -> {
                    showLoading(false)
                    Log.e("Map", "key: error - value: ${state.data.toString()} \n")
                    showErrorMessage(state.data.toString())
                }
            }
        })

    }

    private fun showLoading(visible: Boolean) {
        binding.progressBar.isVisible = visible
    }

    private fun handleErrorResponse(errorResponse: String) {
        Toast.makeText(context, errorResponse, Toast.LENGTH_LONG).show()
    }

    private fun showErrorMessage(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun setUpRecyclerView() {
        photosRecyclerViewAdapter = PhotosRecyclerViewAdapter(photoList, this)
        binding.rvPhotoList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = photosRecyclerViewAdapter
            setRecycledViewPool(RecyclerView.RecycledViewPool())
        }
    }

    private fun handlePhotosResponse(response: ArrayList<PhotoModel>) {
        if (response.size != 0) {
            photoList = response
            photosRecyclerViewAdapter.setListUpdated(photoList)
        }
    }

    override fun onItemClicked(item: PhotoModel) {
        openFullImageActivity(item.url!!)
    }

    private fun openFullImageActivity(url: String) {
        val intent = Intent(activity, FullImageActivity::class.java)
        intent.putExtra("imageUrl", url)
        activity?.startActivity(intent)
    }

}