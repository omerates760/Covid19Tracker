package com.monofire.country.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.monofire.common.ui.bindingadapter.ui.BaseDiffUtilItemCallback
import com.monofire.domain.model.CountryUiData

class CountryAdapter : ListAdapter<CountryUiData, CountryViewHolder>(
    BaseDiffUtilItemCallback<CountryUiData>()
) {
    private var onItemCountryClickListener: ((CountryUiData) -> Unit)? = null

    fun setOnItemCountryClickListener(onItemCountryClickListener: ((CountryUiData) -> Unit)?) {
        this.onItemCountryClickListener = onItemCountryClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder.createHolder(parent)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position), onItemCountryClickListener)
    }
}