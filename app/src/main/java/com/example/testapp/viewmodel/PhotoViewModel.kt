package com.example.testapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhotosViewModel : ViewModel() {
    private val photoRepository = PhotoRepository()
    val photos: MutableLiveData<List<Photo>> = MutableLiveData()
    val photoDetails: MutableLiveData<PhotoDetails> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isError: MutableLiveData<Boolean> = MutableLiveData()

    fun loadPhotos() {
        isLoading.value = true
        isError.value = false

        photoRepository.getPhotos { photosList ->
            isLoading.value = false

            if (photosList != null) {
                photos.value = photosList
            } else {
                isError.value = true
            }
        }
    }

    fun loadPhotoDetails(photoId: String) {
        isLoading.value = true
        isError.value = false

        photoRepository.getPhotoDetails(photoId) { details ->
            isLoading.value = false

            if (details != null) {
                photoDetails.value = details
            } else {
                isError.value = true
            }
        }
    }
}
