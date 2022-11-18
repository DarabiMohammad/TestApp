package com.darabi.testapplication.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.darabi.testapplication.R
import com.darabi.testapplication.databinding.RcvItemMonthBinding
import com.darabi.testapplication.model.Month
import javax.inject.Inject

class MonthRecyclerViewAdapter @Inject constructor() : RecyclerView.Adapter<MonthViewHolder>() {

    private var source: List<Month> = emptyList()

    fun setSource(source: List<Month>) {
        this.source = source
        notifyItemRangeChanged(0, source.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder =
        MonthViewHolder(RcvItemMonthBinding.bind(inflateLayout(parent, R.layout.rcv_item_month)))

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) = source[position].run {
        holder.bindModel(this)
    }

    override fun getItemCount(): Int = source.size

    private fun inflateLayout(view: ViewGroup, @LayoutRes layout: Int): View =
        LayoutInflater.from(view.context).inflate(layout, view, false)
}