package com.anderson.schedulecake

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.anderson.schedulecake.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //chamada da activity Registration
       /* val account = findViewById<TextView>(R.id.tv_not_account)
        account.setOnClickListener{
            notaccount()
        }*/

        auth = Firebase.auth

        //initClicks()

    }

    //Função para ouvir os enventos de clicks
   /* private fun initClicks() {
        //botão de Login recebendo o metodo de validação
        binding.btnLogin.setOnClickListener { validaData() }

        binding.btnRegister.setOnClickListener {
            //captura o envento de click, fazendo a navegação entre os fragment (Login com register)
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.btnRecover.setOnClickListener {
            //captura o envento de click, fazendo a navegação entre os fragment (Login com recover)
            findNavController().navigate(R.id.action_loginFragment_to_recoverAccountFragment)
        }
    }*/

    private fun notaccount() {
        val intent = Intent(this@LoginActivity, RegistrationActivity::class.java)
        startActivity(intent)
    }
}