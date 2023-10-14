package com.example.uts_areliamaiaashary

import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.uts_areliamaiaashary.databinding.ActivityOrderBinding
import com.example.uts_areliamaiaashary.databinding.ActivityPaymentBinding
import java.util.Locale

class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imgBack: ImageView = findViewById(R.id.img_back)


        imgBack.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@OrderActivity, PaymentActivity::class.java)
            startActivity(intent)
        })

        val tempat = intent.getStringExtra(PaymentActivity.extra_bioskop)
        val date=intent.getStringExtra(PaymentActivity.extra_date)
        val hitungseat=intent.getStringExtra(PaymentActivity.extra_kursi)
        val jenisseat=intent.getStringExtra(PaymentActivity.extra_jenisseat)
        val metodepembayaran =intent.getStringExtra(PaymentActivity.extra_method_bayar)
        val pilihpembayaran = intent.getStringExtra(PaymentActivity.extra_pilih_pembayaran)
        val totalamount = intent.getStringExtra(PaymentActivity.extra_total_bayar)


        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateObj = sdf.parse(date)
        val formattedDate = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(dateObj)

        val time=intent.getStringExtra(PaymentActivity.extra_time)

        with(binding){
            tempatNonton.text = tempat
            waktu.text = formattedDate
            timemovie.text = time
            isiSeat.text = jenisseat
            jumlahSeat.text = hitungseat
            namaSeat.text = jenisseat
            methodPayment.text =pilihpembayaran
            totalPembayaran.text = totalamount







        }
    }
}