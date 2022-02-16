package com.example.cryptocurrencyapp.presentation.coin_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.ActivityCoinDetailBinding
import com.example.cryptocurrencyapp.databinding.TagItemBinding
import com.example.cryptocurrencyapp.presentation.coin_detail.adapters.MemberListAdapter
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoinDetailBinding
    private lateinit var memberAdapter: MemberListAdapter
    private val viewModel: CoinDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_coin_detail
        )

        setUpRecyclerView()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
        }


        viewModel.state.observe(this) {
            it.coinDetail?.let { it1 ->
                it1.tags?.let { it11 ->
                    it11.map { t ->
                        val chip = DataBindingUtil.inflate<TagItemBinding>(
                            LayoutInflater.from(this),
                            R.layout.tag_item,
                            binding.coinTags,
                            false
                        )
                        chip.tag = t
                        binding.coinTags.addView(chip.root)
                    }
                }

                memberAdapter.submitList(it1.team)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    private fun setUpRecyclerView() {
        binding.memberRecyclerview.apply {
            memberAdapter = MemberListAdapter()
            layoutManager = LinearLayoutManager(this@CoinDetailActivity)
            adapter = memberAdapter
        }
    }
}