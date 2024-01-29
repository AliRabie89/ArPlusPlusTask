/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


abstract class CustomBaseAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private var dataList: MutableList<T> = mutableListOf()

    fun setInitialData(newDataList: List<T>) {
        val diffResult = DiffUtil.calculateDiff(DataDiffCallback(dataList, newDataList))
        dataList.clear()
        dataList.addAll(newDataList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun addItem(item: T) {
        dataList.add(item)
        notifyItemInserted(dataList.size - 1)
    }

    fun addItems(items: List<T>) {
        val insertPosition = dataList.size
        dataList.addAll(items)
        notifyItemRangeInserted(insertPosition, items.size)
    }

    fun updateItem(position: Int, item: T) {
        dataList[position] = item
        notifyItemChanged(position)
    }

    fun removeItem(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    protected fun getItem(position: Int): T {
        return dataList[position]
    }

    private class DataDiffCallback<T>(
        private val oldList: List<T>,
        private val newList: List<T>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}