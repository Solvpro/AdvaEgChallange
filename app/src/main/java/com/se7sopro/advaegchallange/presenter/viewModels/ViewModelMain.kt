package com.se7sopro.advaegchallange.presenter.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.se7sopro.advaegchallange.data.remote.ViewState
import com.se7sopro.advaegchallange.data.repo.photoData.PhotoDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelMain @Inject constructor(
    private val photoDataRepository: PhotoDataRepository
) : ViewModel() {
    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    private val _getCurrencyDataStateFlow =
        MutableLiveData<ViewState<Any>>()
    val getCurrencyDataStateFlow: MutableLiveData<ViewState<Any>>
        get() = _getCurrencyDataStateFlow

    fun getPhotos() {
        scope.launch {
            _getCurrencyDataStateFlow.postValue(ViewState.Loading(true))
            photoDataRepository.getPhotos()
                .catch {
                    _getCurrencyDataStateFlow.postValue(ViewState.Loading(false))

                    it.message?.let {
                        _getCurrencyDataStateFlow.postValue(
                            ViewState.GeneralError(0, it)
                        )
                    }

                }.buffer()
                .collect {
                    _getCurrencyDataStateFlow.postValue(ViewState.Loading(false))
                    if (it.isSuccessful) {
                        _getCurrencyDataStateFlow.postValue(ViewState.Success(it.body()!!))
                    } else
                        _getCurrencyDataStateFlow.postValue(
                            ViewState.DataError(
                                it.code(),
                                it.errorBody()?.string()!!
                            )
                        )
                }
        }
    }

}