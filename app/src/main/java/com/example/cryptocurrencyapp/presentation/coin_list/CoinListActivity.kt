package com.example.cryptocurrencyapp.presentation.coin_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.ActivityCoinListBinding
import com.example.cryptocurrencyapp.presentation.coin_list.adapters.CoinListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoinListBinding
    private lateinit var coinAdapter: CoinListAdapter
    private val viewModel: CoinListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_coin_list)

        setUpRecyclerView()


        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.state.observe(this) { state ->
            coinAdapter.submitList(state.coins)
        }
    }

    private fun setUpRecyclerView() {
        binding.coinsRecyclerview.apply {
            coinAdapter = CoinListAdapter()
            layoutManager = LinearLayoutManager(this@CoinListActivity)
            adapter = coinAdapter
        }
    }
}