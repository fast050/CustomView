package com.example.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.customview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            button.setOnClickListener {

                customView.swapColor()

            }

            var isClicked= false
            customCardView.setOnClickListener {
                customCardView.setImage(R.drawable.rick_and_morty_surprise)
                customCardView.setCheckBoxState(!isClicked)
                customCardView.setText("First custom View Ever")
            }
        }
    }
}