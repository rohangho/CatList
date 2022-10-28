package com.example.catlist.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catlist.R
import com.example.catlist.domain.model.CatDetail
import com.example.catlist.ui.viewholder.CatIndividualView

class CatDisplayAdapter(context: Context) : RecyclerView.Adapter<CatIndividualView>() {
    private var catList: MutableList<CatDetail> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatIndividualView {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.cat_individual, parent, false)
        return CatIndividualView(v)
    }

    override fun onBindViewHolder(holder: CatIndividualView, position: Int) {
        holder.bind(catList[position])
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    fun setupdatedList(listOfImage: List<CatDetail>) {
        if (catList.isEmpty()) {
            this.catList = listOfImage as MutableList<CatDetail>
            notifyDataSetChanged()
        } else {
            catList.addAll(listOfImage)
            notifyItemInserted(catList.size - 1)
        }
    }
}