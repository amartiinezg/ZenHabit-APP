package com.example.zenhabit

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.graphics.drawable.DrawableCompat

class acitivity_menuNavigation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_menu_navigation)

        val dailyBtn: Button = findViewById(R.id.daily_menu)
        val homeBtn: Button = findViewById<Button>(R.id.home_menu)
        val settingBtn: Button = findViewById(R.id.settings_menu)

        dailyBtn.setOnClickListener{
            dailyBtn.setEnabled(false)
            dailyBtn.background.setTint(resources.getColor(R.color.green))

            homeBtn.setEnabled(true)
            homeBtn.background.setTint(resources.getColor(R.color.transparent))
            settingBtn.setEnabled(true)
            settingBtn.background.setTint(resources.getColor(R.color.transparent))
            supportFragmentManager.beginTransaction().replace(R.id.contenedorFragments, TasksAndHabits_Fragment()).commit()
        }
        homeBtn.setOnClickListener{
            dailyBtn.setEnabled(true)
            dailyBtn.background.setTint(resources.getColor(R.color.transparent))


            homeBtn.setEnabled(false)
            homeBtn.background.setTint(resources.getColor(R.color.green))

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
            settingBtn.background.setTint(resources.getColor(R.color.green))

            supportFragmentManager.beginTransaction().replace(R.id.contenedorFragments, UserSettings()).commit()
        }
    }
}