package com.example.cryptocurrencyapp.presentation.coin_list

import com.example.cryptocurrencyapp.domain.models.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
) {}