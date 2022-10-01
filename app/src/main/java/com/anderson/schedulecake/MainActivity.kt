package com.anderson.schedulecake

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.anderson.schedulecake.auth.LoginActivity
import com.anderson.schedulecake.databinding.ActivityMainBinding
import com.anderson.schedulecake.fragment.CalendarFragment
import com.anderson.schedulecake.fragment.HomeFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var transaction: FragmentTransaction
    private lateinit var fragmentCalendar: Fragment
    private lateinit var fragmentHome: Fragment
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        fragmentCalendar = CalendarFragment ()
        fragmentHome = HomeFragment ()

        //carregamento do fragmento na activityMain
        supportFragmentManager.beginTransaction().add(R.id.frameLayoutFragment, fragmentHome)
            .commit()
        transaction = supportFragmentManager.beginTransaction()


        //logoutApp()
    }

   /* private fun logoutApp(){
        //auth.singOut() desloga usuario do app
        auth.signOut()
        //após sair do app volta para tela de login
        val logout = Intent(this@MainActivity,LoginActivity::class.java)
        startActivity(logout)

    }*/
}