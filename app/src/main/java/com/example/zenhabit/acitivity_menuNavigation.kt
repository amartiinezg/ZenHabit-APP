package com.example.zenhabit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class acitivity_menuNavigation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_menu_navigation)

        val dailyBtn: Button = findViewById(R.id.daily_menu)
        val homeBtn: Button = findViewById(R.id.home_menu)
        val settingBtn: Button = findViewById(R.id.settings_menu)

        dailyBtn.setOnClickListener{
            dailyBtn.setEnabled(false)
            dailyBtn.background.setTint(resources.getColor(R.color.white))

            homeBtn.setEnabled(true)
            homeBtn.background.setTint(resources.getColor(R.color.transparent))
            settingBtn.setEnabled(true)
            settingBtn.background.setTint(resources.getColor(R.color.transparent))
            supportFragmentManager.beginTransaction().replace(R.id.contenedorFragments, DiaryScreen()).commit()
        }
        homeBtn.setOnClickListener{
            dailyBtn.setEnabled(true)
            dailyBtn.background.setTint(resources.getColor(R.color.transparent))

            homeBtn.setEnabled(false)
            homeBtn.background.setTint(resources.getColor(R.color.white))

            settingBtn.setEnabled(true)
            settingBtn.background.setTint(resources.getColor(R.color.transparent))
            supportFragmentManager.beginTransaction().replace(R.id.contenedorFragments, Home()).commit()
        }
        settingBtn.setOnClickListener{
            dailyBtn.setEnabled(true)
            dailyBtn.background.setTint(resources.getColor(R.color.transparent))
            homeBtn.setEnabled(true)
            homeBtn.background.setTint(resources.getColor(R.color.transparent))

            settingBtn.setEnabled(false)
            settingBtn.background.setTint(resources.getColor(R.color.white))

            supportFragmentManager.beginTransaction().replace(R.id.contenedorFragments, UserSettings()).commit()
        }
    }
}