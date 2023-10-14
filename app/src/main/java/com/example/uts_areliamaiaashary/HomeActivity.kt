package com.example.uts_areliamaiaashary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uts_areliamaiaashary.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(MainActivity.EXTRA_NAME)
        with(binding){
            getUsername.text = "Hallo " + name

            btnShow1.setOnClickListener{
                val intentToDetails = Intent(this@HomeActivity, DetailsActivity::class.java)
                startActivity(intentToDetails)
            }
        }
    }
}