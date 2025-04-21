package com.wanjakwan.loginregisteruts

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.wanjakwan.loginregisteruts.database.AppDatabase
import com.wanjakwan.loginregisteruts.model.User
import com.wanjakwan.loginregisteruts.databinding.ActivityUpdateProfileBinding
import kotlinx.coroutines.launch

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateProfileBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(this)

        // Ambil data dari intent
        val nama = intent.getStringExtra("NAMA")
        val email = intent.getStringExtra("EMAIL")
        val noHp = intent.getStringExtra("NOHP")
        val alamat = intent.getStringExtra("ALAMAT")

        // Tampilkan data lama ke EditText
        binding.etNama.setText(nama)
        binding.etEmail.setText(email)
        binding.etNoHp.setText(noHp)
        binding.etAlamat.setText(alamat)

        // Klik tombol update
        binding.btnUpdate.setOnClickListener {
            val updatedNama = binding.etNama.text.toString()
            val updatedEmail = binding.etEmail.text.toString()
            val updatedNoHp = binding.etNoHp.text.toString()
            val updatedAlamat = binding.etAlamat.text.toString()
            val updatedPassword = binding.etPassword.text.toString()

            if (updatedNama.isEmpty() || updatedEmail.isEmpty() || updatedNoHp.isEmpty() || updatedAlamat.isEmpty() || updatedPassword.isEmpty()) {
                Toast.makeText(this, "Semua data wajib diisi!", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    // Ambil user dari database berdasarkan email dan password
                    val user = db.userDao().login(updatedEmail, updatedPassword)
                    if (user != null) {
                        val updatedUser = User(user.id, updatedNama, updatedEmail, updatedNoHp, updatedAlamat, updatedPassword)
                        db.userDao().update(updatedUser)
                        runOnUiThread {
                            Toast.makeText(this@UpdateProfileActivity, "Update berhasil!", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this@UpdateProfileActivity, "User tidak ditemukan!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}
