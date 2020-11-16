package co.id.akbar.masakapa.data.network

import co.id.akbar.masakapa.BuildConfig
import co.id.akbar.masakapa.data.api.ApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RfConfig {

    companion object Factory{
        fun getRetrofit() : ApiService {

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}