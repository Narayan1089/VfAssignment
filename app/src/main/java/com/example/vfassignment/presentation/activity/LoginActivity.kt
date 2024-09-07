package com.example.vfassignment.presentation.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vfassignment.R
import com.example.vfassignment.presentation.adapter.ImageAdapter
import com.example.vfassignment.presentation.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageAdapter
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        adapter = ImageAdapter(listOf())
        recyclerView.adapter = adapter

        // Observe the LiveData from ViewModel
        viewModel.loginImages.observe(this, { images ->
            adapter.updateImages(images)
        })

        // Fetch images from API
        viewModel.fetchLoginImages()
    }
}