package com.example.uts_areliamaiaashary

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.example.uts_areliamaiaashary.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    companion object{
        const val extra_bioskop = "extra_place"
        const val extra_date = "extra_date"
        const val extra_time = "extra_time"
        const val extra_kursi = "extra_jumlahkursi"
        const val extra_jenisseat = "extra_seat"
        const val extra_method_bayar = "extre_metodepembayaran"
        const val extra_pilih_pembayaran= "extra_pilihanpembayaran"
        const val extra_total_bayar= "extra_totalbayar"
        const val extra_username = "extra_username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imgBack: ImageView = findViewById(R.id.img_back)


        imgBack.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@PaymentActivity, DetailsActivity::class.java)
            startActivity(intent)
        })

        val daftarBioskop = resources.getStringArray(R.array.bioskop)
        val daftarSeat = resources.getStringArray(R.array.jenisSeat)
        val method_bayar = resources.getStringArray(R.array.metode_pembayaran)
        val MacamBank= resources.getStringArray(R.array.Bank)
        val MacamEmoney = resources.getStringArray(R.array.E_Money)
//
//


        with(binding) {
            var num = 0;
            val bioskopAdapter = ArrayAdapter(this@PaymentActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, daftarBioskop)
            bioskopAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
            spinBioskop.adapter=bioskopAdapter

            val seatAdapter = ArrayAdapter(this@PaymentActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, daftarSeat)
            seatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinSeat.adapter = seatAdapter

            val PembayaranAdaptor = ArrayAdapter(this@PaymentActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, method_bayar)
            PembayaranAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinpayment.adapter = PembayaranAdaptor


            btnPlus.setOnClickListener {
                num++
                jumlahKursiFix.text = num.toString()
                jumlahKursiFix2.text = num.toString()
            }
            btnMin.setOnClickListener {
                num--
                if (num < 0) num = 0
                jumlahKursiFix.text = num.toString()
                jumlahKursiFix2.text = num.toString()
            }

            val pilihanSeat = binding.spinSeat
            val hargaKursiTV = binding.hargaKursi
            val totalHarga = binding.rupiah2

            pilihanSeat.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val selectedSeat = daftarSeat[position]
                        val harga: Int = when (selectedSeat) {
                            "Reguler Seat" -> 35000
                            "Exclusive Seat" -> 50000
                            "Premiere Seat" -> 120000
                            else -> 0
                        }
                        val hargaString = String.format("Rp%,d", harga)
                        hargaKursiTV.text = hargaString
                        val harga_total = harga * num
                        val hargaTotalString = String.format("Rp%,d", harga_total)
                        totalHarga.text = hargaTotalString

                    }
                    override fun onNothingSelected(parent: AdapterView<*>) {
                    }
                }


            buttonTgl.setOnClickListener {
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                val dpd = DatePickerDialog(
                    this@PaymentActivity,
                    DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                        mdy.setText("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year) }, year, month, day)
                dpd.show()
            }


            buttonTime.setOnClickListener {
                val c = Calendar.getInstance()
                val hour = c.get(Calendar.HOUR_OF_DAY)
                val minute = c.get(Calendar.MINUTE)

                val timePickerDialog = TimePickerDialog(
                    this@PaymentActivity,
                    TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                        time.text = String.format("%02d:%02d", selectedHour, selectedMinute) }, hour, minute,
                    false
                )
                timePickerDialog.show()
            }



            val pilihMethod = binding.spinpayment

            pilihMethod.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View?,
                        position: Int,
                        id: Long
                    ){
                        val selectedMethod = method_bayar[position]
                        if(selectedMethod == "Transfer Bank"){
                            val BankAdaptor = ArrayAdapter(this@PaymentActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, MacamBank)
                            BankAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            spinner1Payment.adapter = BankAdaptor
                        } else {
                            val WalletAdaptor = ArrayAdapter(this@PaymentActivity,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                                MacamEmoney)
                            WalletAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            spinner1Payment.adapter = WalletAdaptor
                        }
                    }
                    override fun onNothingSelected(parent: AdapterView<*>) {
                    }
                }



            btnGetTicket.setOnClickListener{
                val intentToTicket = Intent (this@PaymentActivity, OrderActivity::class.java)
                intentToTicket.putExtra(extra_bioskop, spinBioskop.selectedItem.toString())
                intentToTicket.putExtra(extra_jenisseat, spinSeat.selectedItem.toString())
                intentToTicket.putExtra(extra_date,mdy.text.toString())
                intentToTicket.putExtra(extra_time,time.text.toString())
                intentToTicket.putExtra(extra_kursi,jumlahKursiFix.text.toString())
                intentToTicket.putExtra(extra_method_bayar, spinpayment.selectedItem.toString())
                intentToTicket.putExtra(extra_pilih_pembayaran, spinner1Payment.selectedItem.toString())
                intentToTicket.putExtra(extra_total_bayar,totalHarga.text.toString())


                startActivity(intentToTicket)
            }

        }




    }
}