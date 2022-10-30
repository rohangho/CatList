package com.example.catlist.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catlist.domain.model.CatDetail
import com.example.catlist.utils.AdapterTypeFactory
import com.example.catlist.utils.BaseViewHolder
import com.example.catlist.utils.Visitable

class CatDisplayAdapter(context: Context, val typeFactory: AdapterTypeFactory) :
    RecyclerView.Adapter<BaseViewHolder<Visitable>>() {

    private var allItems = mutableListOf<Visitable>()

    override fun getItemViewType(position: Int): Int {
        return allItems[position].getType(typeFactory)
    }


    override fun getItemCount(): Int {
        return allItems.size
    }

    fun setupdatedList(listOfImage: List<CatDetail>) {
        if (allItems.isEmpty()) {
            this.allItems = listOfImage as MutableList<Visitable>
            notifyDataSetChanged()
        } else {
            allItems.addAll(listOfImage)
            notifyItemInserted(allItems.size - 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Visitable> {
        if (parent != null) {
            val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
            return typeFactory.holder(viewType, view) as BaseViewHolder<Visitable>
        }
        throw RuntimeException("Parent is null")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Visitable>, position: Int) {
        holder.bind(allItems[position])
    }
}