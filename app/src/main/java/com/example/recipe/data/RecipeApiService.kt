package com.example.recipe.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.spoonacular.com/recipes"
private const val API_KEY = "?apiKey=f7bb0374b3a4405cbf49cb736993799f"
private val retrofit = Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

//?apiKey=YOUR-API-KEY.
//TODO: add data classes for api call
interface RecipeApiService {
    @GET("search/$API_KEY")
    fun getCuisines(@Query("query") cuisineType : String) : Call<String>

    @GET("search/$API_KEY")
    fun getDiets(@Query("query") dietType : String) : Call<String>

    @GET("search/$API_KEY")
    fun getMealTypes(@Query("query") mealType : String) : Call<String>

}

object RecipeApi {
    val retrofitService : RecipeApiService by lazy {
        retrofit.create(RecipeApiService::class.java)
    }
}


