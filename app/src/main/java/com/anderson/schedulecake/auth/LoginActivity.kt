package com.anderson.schedulecake.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anderson.schedulecake.MainActivity
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

        auth = Firebase.auth

        initClicks()

    }

    //Função para ouvir os enventos de clicks
   private fun initClicks() {
        //botão de Login recebendo o metodo de validação
        binding.btnLogin.setOnClickListener { validaData() }

        binding.textCriarConta.setOnClickListener {
            //captura o envento de click, fazendo a navegação entre os fragment (Login com register)
            val intent = Intent(this@LoginActivity,RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    //Função que confere se os campos está preenchidos
    private fun validaData(){
        //Campos a serem verificados
        val email = binding.editEmailLogin.text.toString().trim()
        val password = binding.editPasswordLogin.text.toString().trim()
        //verificação dos campos
        if (email.isNotEmpty()){
            if (password.isNotEmpty()){

               loginUser(email,password)

            }else{
                Toast.makeText(applicationContext, "Digite a E-mail", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(applicationContext, "Digite o Senha", Toast.LENGTH_SHORT).show()
        }

    }
    //Salvando os Dados do Login no Firebase
    private fun loginUser(email: String, password: String){
        //executa o login do email e senha no firebase
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){login ->
                if (login.isSuccessful){
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext, "Erro ao ao Logar", Toast.LENGTH_SHORT).show()
                }
            }
    }

}