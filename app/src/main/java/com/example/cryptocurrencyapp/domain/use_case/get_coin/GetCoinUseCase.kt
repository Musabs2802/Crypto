package com.example.cryptocurrencyapp.domain.use_case.get_coin

import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.data.remote.dto.toCoinDetail
import com.example.cryptocurrencyapp.domain.models.CoinDetail
import com.example.cryptocurrencyapp.domain.repositories.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> {
        return flow {
            try {
                emit(Resource.Loading())
                val coin = repository.getCoinById(coinId).toCoinDetail()
                emit(Resource.Success(coin))
            } catch (e: HttpException) {
                emit(
                    Resource.Error<CoinDetail>(
                        e.localizedMessage ?: "An unexpected error occured"
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error<CoinDetail>("No internet connection"))
            }
        }
    }
}