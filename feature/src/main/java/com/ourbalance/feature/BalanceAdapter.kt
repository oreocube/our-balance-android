package com.ourbalance.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.feature.databinding.ItemBalanceBinding

class BalanceAdapter : ListAdapter<BalanceDetail, BalanceAdapter.ViewHolder>(diffUtil) {

    class ViewHolder(
        private val binding: ItemBalanceBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BalanceDetail) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBalanceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<BalanceDetail>() {
            override fun areItemsTheSame(oldItem: BalanceDetail, newItem: BalanceDetail): Boolean {
                return oldItem.roomId == newItem.roomId
            }

            override fun areContentsTheSame(
                oldItem: BalanceDetail,
                newItem: BalanceDetail
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
