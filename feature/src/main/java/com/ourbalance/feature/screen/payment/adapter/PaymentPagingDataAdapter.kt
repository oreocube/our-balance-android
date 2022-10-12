package com.ourbalance.feature.screen.payment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ourbalance.domain.model.payment.PaymentItemModel
import com.ourbalance.feature.R
import com.ourbalance.feature.databinding.ItemPaymentBinding
import com.ourbalance.feature.databinding.ItemPaymentHeaderBinding

class PaymentPagingDataAdapter :
    PagingDataAdapter<PaymentItemModel, RecyclerView.ViewHolder>(diffUtil) {

    class PaymentViewHolder(
        private val binding: ItemPaymentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PaymentItemModel.Payment) {
            binding.payment = item
        }
    }

    class HeaderViewHolder(
        private val binding: ItemPaymentHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PaymentItemModel.Header) {
            binding.date = item.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.item_payment -> {
                PaymentViewHolder(
                    ItemPaymentBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            R.layout.item_payment_header -> {
                HeaderViewHolder(
                    ItemPaymentHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalArgumentException()
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PaymentViewHolder -> holder.bind(getItem(position) as PaymentItemModel.Payment)
            is HeaderViewHolder -> holder.bind(getItem(position) as PaymentItemModel.Header)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PaymentItemModel.Payment -> R.layout.item_payment
            is PaymentItemModel.Header -> R.layout.item_payment_header
            else -> throw IllegalArgumentException()
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PaymentItemModel>() {
            override fun areItemsTheSame(
                oldItem: PaymentItemModel,
                newItem: PaymentItemModel
            ): Boolean {
                return if (oldItem is PaymentItemModel.Payment && newItem is PaymentItemModel.Payment) {
                    oldItem.paymentId == newItem.paymentId
                } else {
                    oldItem == newItem
                }
            }

            override fun areContentsTheSame(
                oldItem: PaymentItemModel,
                newItem: PaymentItemModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
