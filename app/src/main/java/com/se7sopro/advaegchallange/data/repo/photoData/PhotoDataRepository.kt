package com.se7sopro.advaegchallange.data.repo.photoData

import com.se7sopro.advaegchallange.data.model.PhotoModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PhotoDataRepository {

    suspend fun getPhotos(): Flow<Response<ArrayList<PhotoModel>>>

}