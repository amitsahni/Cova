package com.covid19.presentation.ui.notification.adapter

import android.view.ViewGroup
import com.covid19.R
import com.covid19.data.model.response.india.NotificationItem
import com.covid19.presentation.ui.base.BaseRecyclerAdapter
import com.covid19.presentation.ui.base.Holder
import com.fptechscience.tila.common.extension.inflateLayout
import kotlinx.android.synthetic.main.item_notification.view.*


/**
 * Created by Amit Singh.
 * Tila
 * asingh@tila.com
 */

class NotificationAdapter : BaseRecyclerAdapter<Holder, NotificationItem>() {
    override fun onCreateItemViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        return Holder(viewGroup.context.inflateLayout(R.layout.item_notification, viewGroup))
    }

    override fun onBindItemViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)
        holder.itemView.textView.text = item.update
    }
}