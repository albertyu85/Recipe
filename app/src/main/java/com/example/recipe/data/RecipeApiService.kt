package com.example.recipe.data

import com.example.recipe.model.Recipe
import com.example.recipe.model.RecipeList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.spoonacular.com/recipes/"
private const val API_KEY = "492d05005a9a4973bc1b87cdf49312c7"
private val authInterceptor = Interceptor {chain ->
    val newUrl = chain.request().url()
        .newBuilder()
        .addQueryParameter("apiKey", API_KEY)
        .build()

    val newRequest = chain.request()
        .newBuilder()
        .url(newUrl)
        .build()

    chain.proceed(newRequest)
}
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val client = OkHttpClient().newBuilder()
    .addInterceptor(authInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(client)
    .baseUrl(BASE_URL)
    .build()


interface RecipeApiService {
    @GET("search/")
    suspend fun getCuisines(@Query("cuisine") cuisine: String) : RecipeList

    @GET("search/")
    fun getDiets(@Query("diet") diet: String) : Call<String>

    @GET("search/")
    fun getMealTypes(@Query("type") type : String) : Call<String>

}

object RecipeApi {
    val retrofitService : RecipeApiService by lazy {
        retrofit.create(RecipeApiService::class.java)
    }
}


