package com.app.spotfyclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.app.spotfyclone.Fragments.Home
import com.app.spotfyclone.Fragments.Library
import com.app.spotfyclone.Fragments.Search
import com.app.spotfyclone.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment_home = Home()
        loadFragment(fragment_home)

        binding.bottomMenu.setOnItemSelectedListener{
            when(it.itemId){
                R.id.nav_home->{
                    val fragment_home = Home()
                    loadFragment(fragment_home)
                }
                R.id.nav_search->{
                    val fragment_search = Search()
                    loadFragment(fragment_search)
                }
                R.id.nav_library->{
                    val fragment_library = Library()
                    loadFragment(fragment_library)
                }
            }
            return@setOnItemSelectedListener true
        }

    }


    private fun loadFragment(fragment: Fragment){
        val classFragment = fragment
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.mainFrame, classFragment)
        fragment.commit()
    }


}