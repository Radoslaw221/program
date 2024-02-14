package com.example.program

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Call
import retrofit2.http.GET
import com.google.gson.annotations.SerializedName
import retrofit2.http.Query

data class CharacterResponse(
    @SerializedName("results") val characters: List<Character>
)
interface ApiService {
    @GET("api/character")
    fun getCharacters(@Query("page") page: Int, @Query("limit") limit: Int): Call<CharacterResponse>
}

object ApiClient {
    private const val BASE_URL = "https://rickandmortyapi.com"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
