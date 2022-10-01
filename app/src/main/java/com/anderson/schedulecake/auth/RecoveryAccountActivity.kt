package com.anderson.schedulecake.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anderson.schedulecake.databinding.ActivityRecoveryAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RecoveryAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecoveryAccountBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecoveryAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Initialize Firebase Auth
        auth = Firebase.auth

        initClicks()
    }

    private fun initClicks() {
        //botão de Login recebendo o metodo de validação
        binding.btnRecoveryAccount.setOnClickListener { validateData() }

        //botão de voltar
        binding.btnback.setOnClickListener {
            //faz voltar sem criar novamente a activity de login
            val back = Intent(this@RecoveryAccountActivity, LoginActivity::class.java)
            back.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(back)
        }
    }

    //Função valida dados Confere os se os campos foram preenchidos
    private fun validateData() {

        val email = binding.edtEmailRecoveryAccount.text.toString().trim()

        // verificação se foi digitado algo no campo
        if (email.isNotEmpty()) {
            // após o progressBar chamar função registerUser
            recoverAccountUser(email)
        } else {
            Toast.makeText(applicationContext, "Informe seu E-mail", Toast.LENGTH_SHORT).show()
        }
    }

    //função responsavel por recuperar a conta
    private fun recoverAccountUser(email: String) {
        //auth.sendPasswordResetEmail quando chamado enviar um email para reset da senha ao email informado
        Firebase.auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Link enviado ao seu E-mail Informado!",
                        Toast.LENGTH_SHORT
                    ).show()
                    //se não mostre a progressbar
                } else {
                    //retorna a mensagem de erro
                    Toast.makeText(
                        applicationContext,
                        "Erro ao enviar E-mail, verifique sua conexão",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }
        /*Firebase.auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                }
            }*/

    }
}