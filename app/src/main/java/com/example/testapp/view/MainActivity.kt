package com.example.testapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.model.PhotosAdapter
import com.example.testapp.viewmodel.PhotosViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var photosViewModel: PhotosViewModel
    private lateinit var photosAdapter: PhotosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        photosAdapter = PhotosAdapter { photoId ->
            openPhotoDetails(photoId)
        }
        recyclerView.adapter = photosAdapter

        photosViewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
        photosViewModel.photos.observe(this, Observer { photos ->
            photos?.let {
                photosAdapter.submitList(photos)
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

        photosViewModel.loadPhotos()
    }

    private fun openPhotoDetails(photoId: String) {
        // Открываем экран детальной информации с передачей photoId
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
