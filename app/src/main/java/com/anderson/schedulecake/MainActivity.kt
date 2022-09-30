package com.anderson.schedulecake

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.anderson.schedulecake.databinding.ActivityMainBinding
import com.anderson.schedulecake.fragment.CalendarFragment
import com.anderson.schedulecake.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var transaction: FragmentTransaction
    private lateinit var fragmentCalendar: Fragment
    private lateinit var fragmentHome: Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        fragmentCalendar = CalendarFragment ()
        fragmentHome = HomeFragment ()

        supportFragmentManager.beginTransaction().add(R.id.frameLayoutFragment, fragmentHome)
            .commit()

        transaction = supportFragmentManager.beginTransaction()


    }
}