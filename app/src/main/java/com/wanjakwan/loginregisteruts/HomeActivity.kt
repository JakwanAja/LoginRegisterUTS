package com.wanjakwan.loginregisteruts

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.wanjakwan.loginregisteruts.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var userId: Int = 0 // <<< deklarasi di sini

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari intent
        userId = intent.getIntExtra("ID", 0) // <<< ambil id user
        val nama = intent.getStringExtra("NAMA")
        val email = intent.getStringExtra("EMAIL")
        val noHp = intent.getStringExtra("NOHP")
        val alamat = intent.getStringExtra("ALAMAT")

        // Tampilkan ke TextView
        binding.tvWelcome.text = "Selamat Datang, $nama!"
        binding.tvEmail.text = "Email: $email"
        binding.tvNoHp.text = "No HP: $noHp"
        binding.tvAlamat.text = "Alamat: $alamat"

        // Navigasi ke UpdateProfileActivity
        binding.btnUpdateProfile.setOnClickListener {
            val intent = Intent(this, UpdateProfileActivity::class.java)
            intent.putExtra("ID", userId)
            intent.putExtra("NAMA", nama)
            intent.putExtra("EMAIL", email)
            intent.putExtra("NOHP", noHp)
            intent.putExtra("ALAMAT", alamat)
            startActivity(intent)
        }
    }
}
