package com.example.cryptocurrencyapp.presentation.coin_detail

import com.example.cryptocurrencyapp.domain.models.CoinDetail

class CoinDetailState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetail? = null,
    val error: String = ""
)