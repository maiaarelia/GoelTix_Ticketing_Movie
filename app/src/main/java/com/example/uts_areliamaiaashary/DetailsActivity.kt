package com.example.uts_areliamaiaashary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.uts_areliamaiaashary.databinding.ActivityDetailsBinding
import com.example.uts_areliamaiaashary.databinding.ActivityHomeBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imgBack: ImageView = findViewById(R.id.img_back)

        imgBack.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@DetailsActivity, HomeActivity::class.java)
            startActivity(intent)
        })

        with(binding) {
            btnGetTicket.setOnClickListener {
                val intentToDetails = Intent(this@DetailsActivity, PaymentActivity::class.java)
                startActivity(intentToDetails)
            }
        }

    }



}
