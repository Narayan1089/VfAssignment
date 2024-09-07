package com.example.vfassignment.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vfassignment.databinding.ActivityLoginBinding
import com.example.vfassignment.presentation.adapter.ImageAdapter
import com.example.vfassignment.presentation.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var adapter: ImageAdapter
    private val viewModel: LoginViewModel by viewModels()
    private val handler = Handler(Looper.getMainLooper())
    private val scrollRunnable = object : Runnable {
        override fun run() {
            binding.recyclerView.smoothScrollBy(0, 2)
            handler.postDelayed(this, 50)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        adapter = ImageAdapter(listOf())
        binding.recyclerView.adapter = adapter

        viewModel.loginImages.observe(this) { images ->
            if (images.isNotEmpty()) {
                val shuffledImages = images.shuffled()
                adapter.updateImages(shuffledImages)
                startImageLoop()
            }
        }

        viewModel.fetchLoginImages()
        checkValidationsForLogin()
        binding.googleSigninButton.setOnClickListener {
            Toast.makeText(this, "Would be implemented later", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkValidationsForLogin() {
        binding.loginButton.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, HomePageActivity::class.java).apply {
                putExtra("EMAIL", email)
                putExtra("PASSWORD", password)
            }
            startActivity(intent)
        }    }

    private fun startImageLoop() {
        handler.removeCallbacks(scrollRunnable)
        handler.post(scrollRunnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(scrollRunnable)
    }
}