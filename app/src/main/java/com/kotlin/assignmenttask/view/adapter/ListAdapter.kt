package com.kotlin.assignmenttask.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlin.assignmenttask.R
import com.kotlin.finaltarget.data.image.Row
import kotlinx.android.synthetic.main.adapter_view.view.*


class ListAdapter(private val users: ArrayList<Row>) : RecyclerView.Adapter<ListAdapter.DataViewHolder>() {

    class DataViewHolder(adap: View) : RecyclerView.ViewHolder(adap) {

        fun bind(user: Row) {
            itemView.apply {
                title_mtv.text = "Title :->  "+user.title
                desc_mtv.text = "Description:->  "+ user.description
                Glide.with(image_mIv.context)
                        .load(user.imageHref)
                        .into(image_mIv)


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
            DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_view, parent, false))

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUsers(users: List<Row>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }
}