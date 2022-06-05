package com.se7sopro.advaegchallange.data.remote

import com.se7sopro.advaegchallange.data.model.PhotoModel
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyApi {

    @GET("photos")
    suspend fun getPhotos(): Response<ArrayList<PhotoModel>>

}