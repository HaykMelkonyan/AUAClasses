package org.tumo.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tumo.myapplication.repository.DataLoaderRepository

class DataLoaderViewModel : ViewModel() {
    private val repository = DataLoaderRepository()
    private val _liveDataNames = MutableLiveData<List<String>>()
    val liveDataNames: LiveData<List<String>> = _liveDataNames
    private val _liveDataImages = MutableLiveData<List<String>>()
    val liveDataImages: LiveData<List<String>> = _liveDataImages


    fun loadData() {
        val result = repository.getData()
        _liveDataNames.postValue(result)
    }

    fun loadImages() {
        _liveDataImages.postValue(repository.getImages())
    }
}