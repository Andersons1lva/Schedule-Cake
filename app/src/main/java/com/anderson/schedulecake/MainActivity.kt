package com.anderson.schedulecake

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.anderson.schedulecake.auth.LoginActivity
import com.anderson.schedulecake.fragment.CalendarFragment
import com.anderson.schedulecake.databinding.ActivityMainBinding
import com.anderson.schedulecake.fragment.HomeFragment
import com.anderson.schedulecake.helper.SharedPreferencesHelper
import com.anderson.schedulecake.model.ClienteActivity
import com.anderson.schedulecake.model.EncomendaActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var transaction: FragmentTransaction
    private lateinit var fragmentCalendar: Fragment
    private lateinit var fragmentHome: Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //chamada do menu inferior
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        fragmentCalendar = CalendarFragment()
        fragmentHome = HomeFragment()

        //carregamento do fragmento na activityMain
        supportFragmentManager.beginTransaction().add(R.id.frameLayoutFragment, fragmentHome)
            .commit()
        transaction = supportFragmentManager.beginTransaction()


        clicksMenu()

        initClicks()
    }


    //função de clicks geral
    private fun initClicks() {
        val fab: View = binding.bntFab
        fab.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, EncomendaActivity::class.java)
            startActivity(intent)
        }

    }

    private fun clicksMenu() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnMenuHome -> {
                    val home = Intent(this@MainActivity, MainActivity::class.java)
                    startActivity(home)
                }

                R.id.btnMenuPerson -> {
                    val person = Intent(this@MainActivity, ClienteActivity::class.java)
                    startActivity(person)
                }

                R.id.btnMenuExit -> {
                    logoutApp()
                }

            }
            true
        }
    }

    private fun logoutApp() {
        //auth.singOut() desloga usuario do app
        SharedPreferencesHelper.clearAuthToken(this)
        //após sair do app volta para tela de login
        val logout = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(logout)

    }

}