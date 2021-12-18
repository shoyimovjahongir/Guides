package com.example.cleanarchitecture.di.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.databinding.RvItemBinding
import com.example.domain.mymodule1.Data
import com.example.domain.mymodule1.MyData
import com.squareup.picasso.Picasso

class RvAdapter(val list: List<Data>, val listener:OnClicListener) : RecyclerView.Adapter<RvAdapter.MV>() {

    inner class MV(val itemBinding: RvItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(course: Data, position: Int) {

            Picasso.get().load(course.icon).into(itemBinding.img1)
            itemBinding.tv2.text = course.name
            itemBinding.text.text = course.startDate
            itemBinding.text2.text = course.objType
            itemBinding.text3.text= course.endDate

            itemView.setOnClickListener {
                listener.onItemClic(list,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MV {
        return MV(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MV, position: Int) {

        holder.onBind(list[position], position)
    }

    interface OnClicListener {

        fun onItemClic(modelList: List<Data>, position: Int)

    }
}