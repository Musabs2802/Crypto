package com.example.cryptocurrencyapp.presentation.coin_detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.data.remote.dto.TeamMember
import com.example.cryptocurrencyapp.databinding.MemberListItemBinding
import com.example.cryptocurrencyapp.domain.models.CoinDetail

class MemberListAdapter : ListAdapter<TeamMember, MemberListAdapter.MyViewHolder>(MemberDU()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<MemberListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.member_list_item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MyViewHolder(private val binding: MemberListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(member: TeamMember) {
            binding.member = member
        }
    }

    class MemberDU : DiffUtil.ItemCallback<TeamMember>() {
        override fun areItemsTheSame(oldItem: TeamMember, newItem: TeamMember): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TeamMember, newItem: TeamMember): Boolean {
            return oldItem == newItem
        }

    }
}