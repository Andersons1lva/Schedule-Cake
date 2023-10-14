package com.anderson.schedulecake.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anderson.schedulecake.R

class ClienteActivity : AppCompatActivity() {
    private lateinit var nome: String
    private lateinit var encomenda: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente)
    }
}