package com.example.recipe.api

import com.example.recipe.model.RecipeInformation
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
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
    suspend fun getDiets(@Query("diet") diet: String) : RecipeList

    @GET("search/")
    suspend fun getMealTypes(@Query("type") type : String) : RecipeList

    //Directions Page
    @GET("{id}/information")
    suspend fun getRecipe(@Path("id") id: Int, @Query("includeNutrition") includeNutrition: Boolean = false) : RecipeInformation

    // Detail Page
    @GET("complexSearch/")
    suspend fun getRecipeComplex(
            @Query("cuisine") cuisine: String,
            @Query("diet") diet: String,
            @Query("type")type: String,
            @Query("sort") sort: String
    ) : ComplexRecipeList


}

object RecipeApi {
    val retrofitService : RecipeApiService by lazy {
        retrofit.create(RecipeApiService::class.java)
    }
}


