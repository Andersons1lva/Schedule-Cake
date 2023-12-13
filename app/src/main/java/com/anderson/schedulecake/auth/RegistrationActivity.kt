package com.anderson.schedulecake.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anderson.schedulecake.BuildConfig
import com.anderson.schedulecake.R
import com.anderson.schedulecake.databinding.ActivityCadastroBinding
import com.anderson.schedulecake.model.UserResponse
import com.anderson.schedulecake.rest.LoginResquest
import com.anderson.schedulecake.rest.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding:ActivityCadastroBinding
    private lateinit var registerEmail: EditText
    private lateinit var registerPassword:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerEmail = findViewById(R.id.edtEmailCadastro)
        registerPassword = findViewById(R.id.edtPasswordCadastro)

        //botão de Login recebendo o metodo de validação
        binding.btnRegistration.setOnClickListener {
            validaData()
        }

    }


    //Função que confere se os campos está preenchidos
    private fun validaData(){
        //Campos a serem verificados
        val login = binding.edtEmailCadastro.text.toString().trim()
        val password = binding.edtPasswordCadastro.text.toString().trim()

        //verificação dos campos
            if (login.isNotEmpty()) {
                if (password.isNotEmpty()) {
                    registerUser(LoginResquest())
                } else {
                    Toast.makeText(applicationContext, "Digite a E-mail", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "Digite o Senha", Toast.LENGTH_SHORT).show()
            }

    }


    //Salvando os Dados do Cadastro no Firebase

    private fun registerUser(loginResquest: LoginResquest) {
//        loginResquest.login = binding.edtEmailCadastro.text.toString().trim()
//        loginResquest.password = binding.edtPasswordCadastro.text.toString().trim()
//        loginResquest.role = "USER"
//
//        val loginResponseCall: Call<UserResponse> = RetrofitClient.getUserService().registerUser(loginResquest)
//
//        loginResponseCall.enqueue(object : Callback<UserResponse>{
//            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
//                if (BuildConfig.DEBUG) {
//                    Log.d("API", response.body().toString())
//                }
//                if (response.isSuccessful){
//                    val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
//                    startActivity(intent)
//                    Toast.makeText(applicationContext,"Register Successful", Toast.LENGTH_SHORT).show()
//                }else{
//                    Toast.makeText(applicationContext,"Error Register failed!", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                Toast.makeText(applicationContext,"Throwable" + t.localizedMessage, Toast.LENGTH_SHORT).show()
//            }
//
//        })
    }

//    private fun registerUser(email: String, password: String){
//        // Criação do email e senha no Firebase
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this){cadastro ->
//                //condição para verificar se o cadatro ocorreu com sucesso no Firebase
//                if (cadastro.isSuccessful){
//                    // após cadastro efetuado com sucesso direciona para MainActivity
//                    val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
//                    startActivity(intent)
//                }else{
//                    Toast.makeText(applicationContext, "Erro ao Cadastrar", Toast.LENGTH_SHORT).show()
//                }
//            }
//    }
}

