package com.anderson.schedulecake

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anderson.schedulecake.databinding.ActivityCadastroBinding

class RegistrationActivity : AppCompatActivity() {

    private val binding by lazy {ActivityCadastroBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}