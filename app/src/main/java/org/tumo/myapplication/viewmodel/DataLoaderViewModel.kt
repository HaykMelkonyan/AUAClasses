package org.tumo.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.tumo.myapplication.repository.DataLoaderRepository
import org.tumo.myapplication.repository.NewsResponse

class DataLoaderViewModel : ViewModel() {
    private val repository = DataLoaderRepository()
    private val _liveDataNames = MutableLiveData<List<String>>()
    val liveDataNames: LiveData<List<String>> = _liveDataNames
    private val _liveDataImages = MutableLiveData<List<String>>()
    val liveDataImages: LiveData<List<String>> = _liveDataImages
    private val _liveDataNews = MutableLiveData<NewsResponse>()
    val liveDataNews: LiveData<NewsResponse> = _liveDataNews


    fun loadData() {
        val result = repository.getData()
        _liveDataNames.postValue(result)
    }

    fun loadImages() {
        _liveDataImages.postValue(repository.getImages())
    }

    fun loadNews() {
        viewModelScope.launch {
            _liveDataNews.postValue(repository.loadNews())
        }
    }
}