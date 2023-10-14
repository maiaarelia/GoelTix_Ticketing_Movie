package com.example.uts_areliamaiaashary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uts_areliamaiaashary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val EXTRA_NAME = "extra_name"
    }

    private val Username = "maia"
    private val Password = "492561"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            button.setOnClickListener {
                val UsernameMasuk = email.text.toString()
                val PasswordMasuk = password.text.toString()

                if (UsernameMasuk == Username && PasswordMasuk == Password) {
                    val intentToHome = Intent(this@MainActivity, HomeActivity::class.java)
                    intentToHome.putExtra(EXTRA_NAME, UsernameMasuk)
                    startActivity(intentToHome)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Username atau password yang dimasukan salah",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
