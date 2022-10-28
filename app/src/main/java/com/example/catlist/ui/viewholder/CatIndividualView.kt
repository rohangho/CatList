package com.example.catlist.ui.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catlist.R
import com.example.catlist.domain.model.CatDetail

class CatIndividualView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(catDetail: CatDetail) {
        val catImage = itemView.findViewById<ImageView>(R.id.individualCat)
        Glide.with(itemView.context).load(catDetail.url).into(catImage)

    }
}