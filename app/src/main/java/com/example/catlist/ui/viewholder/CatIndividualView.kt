package com.example.catlist.ui.viewholder

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.catlist.R
import com.example.catlist.domain.model.CatDetail
import com.example.catlist.utils.BaseViewHolder

class CatIndividualView(itemView: View) : BaseViewHolder<CatDetail>(itemView) {

    override fun bind(catDetail: CatDetail) {
        val catImage = itemView.findViewById<ImageView>(R.id.individualCat)
        Glide.with(itemView.context).load(catDetail.url)
            .placeholder(R.drawable.ic_baseline_cloud_download_24).into(catImage)
    }


}