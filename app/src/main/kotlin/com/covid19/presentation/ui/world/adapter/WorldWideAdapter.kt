package com.covid19.presentation.ui.world.adapter

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.covid19.R
import com.covid19.data.model.response.global.Row
import com.covid19.presentation.ui.base.BaseFooterRecyclerAdapter
import com.covid19.presentation.ui.base.HolderContainer
import com.fptechscience.tila.common.extension.gone
import com.fptechscience.tila.common.extension.inflateLayout
import com.fptechscience.tila.common.extension.load
import com.fptechscience.tila.common.extension.visible
import kotlinx.android.synthetic.main.item_world_wide.view.*
import kotlinx.android.synthetic.main.view_loading.view.*


/**
 * Created by Amit Singh.
 * Tila
 * asingh@tila.com
 */

class WorldWideAdapter :
    BaseFooterRecyclerAdapter<WorldWideAdapter.Holder, WorldWideAdapter.Footer, Row>() {

    inner class Holder(view: View) : HolderContainer<Row>(view) {
        override fun bind(position: Int, item: Row) {
            view.imageView.load(item.flag.toString(), R.mipmap.ic_launcher_round, CircleCrop())
            view.country.text = item.country
            view.confirmed.text = item.totalCases
            view.recovered.text = item.totalRecovered
            view.active.text = item.activeCases
            view.death.text = item.totalDeaths
        }

    }

    inner class Footer(view: View) : HolderContainer<Boolean>(view) {
        override fun bind(position: Int, item: Boolean) {
            if (useFooter) {
                view.animationView.gone()
            } else {
                view.animationView.gone()
            }

        }

    }

    override fun onCreateFooterViewHolder(viewGroup: ViewGroup, viewType: Int): Footer {
        return Footer(viewGroup.context.inflateLayout(R.layout.view_loading, viewGroup))
    }

    override fun onBindFooterViewHolder(holder: Footer, position: Int) {
        holder.bind(position, useFooter)
    }

    override fun onCreateItemViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        return Holder(viewGroup.context.inflateLayout(R.layout.item_world_wide, viewGroup))
    }

    override fun onBindItemViewHolder(holder: Holder, position: Int) {
        holder.bind(position, getItem(position))
    }
}