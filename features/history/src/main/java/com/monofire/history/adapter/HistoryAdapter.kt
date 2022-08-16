package com.monofire.history.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.monofire.common.ui.bindingadapter.ui.BaseDiffUtilItemCallback
import com.monofire.domain.model.CountryUiData
import com.monofire.domain.model.HistoryUiData

class HistoryAdapter : ListAdapter<HistoryUiData, HistoryViewHolder>(
    BaseDiffUtilItemCallback<HistoryUiData>()
) {

    private var onItemCountryClickListener: ((HistoryUiData) -> Unit)? = null

    fun setOnItemCountryClickListener(onItemCountryClickListener: ((HistoryUiData) -> Unit)?) {
        this.onItemCountryClickListener = onItemCountryClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder.createHolder(parent)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position), onItemCountryClickListener)
    }
}