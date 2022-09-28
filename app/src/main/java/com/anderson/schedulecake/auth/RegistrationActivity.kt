package com.anderson.schedulecake.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anderson.schedulecake.MainActivity
import com.anderson.schedulecake.databinding.ActivityCadastroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding:ActivityCadastroBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        initClicks()
    }

    //Função para ouvir os enventos de clicks
    private fun initClicks() {
        //botão de Login recebendo o metodo de validação
        binding.btnRegistration.setOnClickListener { validaData() }
    }

    //Função que confere se os campos está preenchidos
    private fun validaData(){
        //Campos a serem verificados
        val email = binding.edtEmailCadastro.text.toString().trim()
        val password = binding.edtPasswordCadastro.text.toString().trim()

        //verificação dos campos
            if (email.isNotEmpty()) {
                if (password.isNotEmpty()) {

                    registerUser(email, password)

                } else {
                    Toast.makeText(applicationContext, "Digite a E-mail", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "Digite o Senha", Toast.LENGTH_SHORT).show()
            }

    }
    //Salvando os Dados do Cadastro no Firebase
    private fun registerUser(email: String, password: String){
        // Criação do email e senha no Firebase
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){cadastro ->
                //condição para verificar se o cadatro ocorreu com sucesso no Firebase
                if (cadastro.isSuccessful){
                    // após cadastro efetuado com sucesso direciona para MainActivity
                    val intent = Intent(this@RegistrationActivity, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext, "Erro ao Cadastrar", Toast.LENGTH_SHORT).show()
                }
            }
    }
}