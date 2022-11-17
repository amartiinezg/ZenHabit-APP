package com.example.zenhabit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.zenhabit.R
import com.example.zenhabit.fragments.Home
import com.example.zenhabit.fragments.UserSettings
import com.example.zenhabit.fragments.jardi
import com.google.android.material.snackbar.Snackbar


class acitivity_menuNavigation : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_menu_navigation)

        val dailyBtn: Button = findViewById(R.id.daily_menu)
        val homeBtn: Button = findViewById<Button>(R.id.home_menu)
        val settingBtn: Button = findViewById(R.id.settings_menu)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        dailyBtn.setOnClickListener{
            dailyBtn.setEnabled(false)
            dailyBtn.background.setTint(resources.getColor(R.color.green))

            homeBtn.setEnabled(true)
            homeBtn.background.setTint(resources.getColor(R.color.transparent))
            settingBtn.setEnabled(true)
            settingBtn.background.setTint(resources.getColor(R.color.transparent))
        }
        homeBtn.setOnClickListener{
            dailyBtn.setEnabled(true)
            dailyBtn.background.setTint(resources.getColor(R.color.transparent))


            homeBtn.setEnabled(true)
            homeBtn.background.setTint(resources.getColor(R.color.green))

            settingBtn.setEnabled(true)
            settingBtn.background.setTint(resources.getColor(R.color.transparent))
            navController.navigate(R.id.nav_graph)
        }
        settingBtn.setOnClickListener{
            dailyBtn.setEnabled(true)
            dailyBtn.background.setTint(resources.getColor(R.color.transparent))
            homeBtn.setEnabled(true)
            homeBtn.background.setTint(resources.getColor(R.color.transparent))

            settingBtn.setEnabled(false)
            settingBtn.background.setTint(resources.getColor(R.color.green))
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, UserSettings()).commit()
        }
    }
}