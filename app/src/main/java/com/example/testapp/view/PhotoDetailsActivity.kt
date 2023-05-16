package com.example.testapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_photo_details.*

class PhotoDetailsActivity : AppCompatActivity() {
    private lateinit var photosViewModel: PhotosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)

        val photoId = intent.getStringExtra("photoId")

        photosViewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
        photosViewModel.photoDetails.observe(this, Observer { photoDetails ->
            photoDetails?.let {
                tvPhotoTitle.text = it.title
                tvPhotoDescription.text = it.description
                tvUserName.text = it.user.name
                tvUserBio.text = it.user.bio
                tvLikesCount.text = it.likesCount.toString()
                tvDownloadsCount.text = it.downloadsCount.toString()
                Glide.with(this)
                    .load(it.imageUrl)
                    .into(ivPhoto)
            }
        })

        photosViewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        })

        photosViewModel.isError.observe(this, Observer { isError ->
            if (isError) {
                showError()
            } else {
                hideError()
            }
        })

        photosViewModel.loadPhotoDetails(photoId)
    }

    private fun showLoading() {
        // Отображаем индикатор загрузки
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        // Скрываем индикатор загрузки
        progressBar.visibility = View.GONE
    }

    private fun showError() {
        // Отображаем сообщение об ошибке
        errorTextView.visibility = View.VISIBLE
    }

    private fun hideError() {
        // Скрываем сообщение об ошибке
        errorTextView.visibility = View.GONE
    }
}
