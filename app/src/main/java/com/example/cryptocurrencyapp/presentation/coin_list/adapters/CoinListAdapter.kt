package com.example.cryptocurrencyapp.presentation.coin_list.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.common.Constants
import com.example.cryptocurrencyapp.databinding.CoinListItemBinding
import com.example.cryptocurrencyapp.domain.models.Coin
import com.example.cryptocurrencyapp.presentation.coin_detail.CoinDetailActivity

class CoinListAdapter : ListAdapter<Coin, CoinListAdapter.MyViewHolder>(CoinDU()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: CoinListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.coin_list_item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MyViewHolder(private val binding: CoinListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: Coin) {
            binding.coin = coin
            binding.setClickListener {
                it.context.startActivity(Intent(it.context, CoinDetailActivity::class.java).apply {
                    putExtra(
                        Constants.PARAM_COIN_ID, coin.id
                    )
                })
            }
        }
    }

    class CoinDU : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }

    }
}