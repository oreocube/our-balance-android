package com.ourbalance.feature.payment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ourbalance.feature.databinding.ItemPaymentSummaryBinding
import com.ourbalance.feature.payment.PaymentSummary

class PaymentSummaryAdapter : RecyclerView.Adapter<PaymentSummaryAdapter.ViewHolder>() {
    private lateinit var paymentSummary: PaymentSummary

    fun setData(paymentSummary: PaymentSummary) {
        this.paymentSummary = paymentSummary
        notifyItemChanged(0)
    }

    class ViewHolder(private val binding: ItemPaymentSummaryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(paymentSummary: PaymentSummary) {
            binding.summary = paymentSummary
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPaymentSummaryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(paymentSummary)
    }

    override fun getItemCount(): Int = 1
}
