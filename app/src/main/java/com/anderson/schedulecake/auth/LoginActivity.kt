package com.anderson.schedulecake.auth


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anderson.schedulecake.BuildConfig
import com.anderson.schedulecake.MainActivity
import com.anderson.schedulecake.R
import com.anderson.schedulecake.databinding.ActivityLoginBinding
import com.anderson.schedulecake.helper.SharedPreferencesHelper
import com.anderson.schedulecake.model.UserResponse
import com.anderson.schedulecake.network.apiService
import com.anderson.schedulecake.rest.LoginResquest
import com.anderson.schedulecake.rest.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class LoginActivity : AppCompatActivity() {

    private val apiService = RetrofitClient.create()

    private lateinit var binding: ActivityLoginBinding
    private lateinit var etEmail: EditText
    private lateinit var etSenha: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etEmail = findViewById(R.id.editEmailLogin)
        etSenha = findViewById(R.id.editPasswordLogin)


        //botão de Login recebendo o metodo de validação
        binding.btnLogin.setOnClickListener {loginUser()}

        initClicks()
//        verificarSeUsuarioEstaLogado()
    }

    //Função para ouvir os enventos de clicks
    private fun initClicks() {

        binding.textCriarConta.setOnClickListener {
            //captura o envento de click, fazendo a navegação entre as activitys (Login com register)
            val createAccount = Intent(this@LoginActivity, RegistrationActivity::class.java)
            startActivity(createAccount)
        }

        binding.textRecuperarConta.setOnClickListener {
            //captura o envento de click, fazendo a navegação entre as activitys (Login com register)
            val recoveryAccount = Intent(this@LoginActivity, RecoveryAccountActivity::class.java)
            startActivity(recoveryAccount)
        }
    }

    fun loginUser() {
        val login = etEmail.text.toString()
        val password = etSenha.text.toString()

        if (login.isNotEmpty() && password.isNotEmpty()){
            val loginResquest = LoginResquest(login = login,password = password, role = null)

            val call: Call<AuthResponse> = apiService.login(loginResquest)
            call.enqueue(object : Callback<AuthResponse> {
                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    if (response.isSuccessful){
                        val authResponse = response.body()
                        val token = authResponse?.token

                        // Salvando o token no SharedPreferences
                        val sharedPreferences = getSharedPreferences("tokenLogin", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("token",token)
                        editor.apply()

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        showToast("Login bem-sucedido")
                    }else{
                        // Trate a resposta de erro (pode ser problema de credenciais inválidas, por exemplo)
                        showToast("Email ou password invalid: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    // Trate exceções, como IOException ou outras
                    showToast("Erro durante o login")
                }
            })
        }else{
            // Exemplo: Mostrar uma mensagem se os campos estiverem vazios
            showToast("Por favor, preencha todos os campos")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


   // Verificação se o usuario está logado
    override fun onStart() {
       super.onStart()
       verificarSeUsuarioEstaLogado()
   }

    private fun verificarSeUsuarioEstaLogado() {
//        val email = binding.editEmailLogin.text.toString().trim()
//        val password = binding.editPasswordLogin.text.toString().trim()
//
//        val apiService = RetrofitClient.getUserService()
//        val call = apiService.login(LoginResquest(email, password, "USER"))
//
//        call.enqueue(object : Callback<UserResponse> {
//            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
//                val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                startActivity(intent)
//            }
//
//            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                Toast.makeText(
//                    applicationContext,
//                    "Falha ao tentar conectar com servidor." + t,
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//
//        })
    }


}


