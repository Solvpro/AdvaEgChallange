package com.se7sopro.advaegchallange.presenter.view.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.se7sopro.advaegchallange.R
import com.se7sopro.advaegchallange.data.model.PhotoModel
import com.se7sopro.advaegchallange.databinding.CurrencyItemBinding
import com.se7sopro.advaegchallange.utils.listeners.ItemClickListener
import com.se7sopro.advaegchallange.utils.loadImage


class PhotosRecyclerViewAdapter(
    private var list: MutableList<PhotoModel>,
    private val itemClickListener: ItemClickListener<PhotoModel>
) :
    RecyclerView.Adapter<PhotosRecyclerViewAdapter.MainMenuViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = MainMenuViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_item, parent, false)
    )


    override fun getItemCount() = list.size

    fun setListUpdated(listUpdated: MutableList<PhotoModel>) {
        list = listUpdated
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) {
        holder.onBind(list[position])

    }

    inner class MainMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(item: PhotoModel) = with(CurrencyItemBinding.bind(itemView)) {
            tvTitle.text = item.title!!
            progressBar.visibility = View.VISIBLE
            item.url?.let {
                ivPhoto.loadImage(
                    it,
                    progressBar
                )
            }
            ivPhoto.setOnClickListener { itemClickListener.onItemClicked(item) }
        }
    }
}