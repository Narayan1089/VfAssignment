package com.example.vfassignment.presentation.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vfassignment.R
import com.example.vfassignment.databinding.ActivityHomePageBinding
import com.example.vfassignment.databinding.ActivitySignUpBinding

class HomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val email = intent.getStringExtra("EMAIL")
        val password = intent.getStringExtra("PASSWORD")

        binding.tvWelcomeText.text = email
    }
}