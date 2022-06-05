package com.se7sopro.advaegchallange.data.repo.photoData

import com.se7sopro.advaegchallange.data.model.PhotoModel
import com.se7sopro.advaegchallange.data.remote.CurrencyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class PhotoDataRepositoryImp @Inject constructor(private val api: CurrencyApi) :
    PhotoDataRepository {

    override suspend fun getPhotos(): Flow<Response<ArrayList<PhotoModel>>> {
        return flow {
            emit(
                api.getPhotos()
            )
        }.flowOn(Dispatchers.IO)
    }

}