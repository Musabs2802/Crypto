package com.example.cryptocurrencyapp.domain.use_case.get_coins

import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.data.remote.dto.toCoin
import com.example.cryptocurrencyapp.domain.models.Coin
import com.example.cryptocurrencyapp.domain.repositories.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke(): Flow<Resource<List<Coin>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val coins = repository.getCoins().map { it.toCoin() }
                emit(Resource.Success(coins))
            } catch (e: HttpException) {
                emit(
                    Resource.Error<List<Coin>>(
                        e.localizedMessage ?: "An unexpected error occured"
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error<List<Coin>>("No Internet connection"))
            }
        }
    }
}