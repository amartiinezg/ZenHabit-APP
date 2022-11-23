package com.example.zenhabit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.zenhabit.R
import com.example.zenhabit.databinding.ActivityMenuNavigationBinding
import com.example.zenhabit.fragments.DiaryScreen
import com.example.zenhabit.fragments.UserSettings


class acitivity_menuNavigation : AppCompatActivity() {

    private lateinit var binding: ActivityMenuNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMenuNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.dailyMenu.setOnClickListener{
            binding.dailyMenu.setEnabled(false)
            binding.dailyMenu.background.setTint(resources.getColor(R.color.green))
            binding.homeMenu.setEnabled(true)
            binding.homeMenu.background.setTint(resources.getColor(R.color.transparent))
            binding.settingsMenu.setEnabled(true)
            binding.settingsMenu.background.setTint(resources.getColor(R.color.transparent))
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DiaryScreen()).commit()
        }
        binding.homeMenu.setOnClickListener{
            binding.dailyMenu.setEnabled(true)
            binding.dailyMenu.background.setTint(resources.getColor(R.color.transparent))


            binding.homeMenu.setEnabled(true)
            binding.homeMenu.background.setTint(resources.getColor(R.color.green))

            binding.settingsMenu.setEnabled(true)
            binding.settingsMenu.background.setTint(resources.getColor(R.color.transparent))
            navController.navigate(R.id.nav_graph)
        }
        binding.settingsMenu.setOnClickListener{
            binding.dailyMenu.setEnabled(true)
            binding.dailyMenu.background.setTint(resources.getColor(R.color.transparent))
            binding.homeMenu.setEnabled(true)
            binding.homeMenu.background.setTint(resources.getColor(R.color.transparent))

            binding.settingsMenu.setEnabled(false)
            binding.settingsMenu.background.setTint(resources.getColor(R.color.green))
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, UserSettings()).commit()
        }
    }
}