package com.martial.garageapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.martial.garageapp.utils.SharedHelper

class MainActivity : AppCompatActivity() {
    private lateinit var shared: SharedHelper
    private lateinit var  bt_logout : Button
    private lateinit var context: Context
    var doubleBackToExitPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        onclick()
    }

    private fun initialize(){
        bt_logout = findViewById(R.id.bt_logout)
        context = this
        shared = SharedHelper(context)
    }
    private fun onclick(){
        bt_logout.setOnClickListener {
            shared.clear()
            Toast.makeText(context, "Logout Success", Toast.LENGTH_SHORT).show()
            val backToLogin = Intent(context, LoginActivity::class.java)
            backToLogin.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(backToLogin)
            finish()
        }
    }

    override fun onBackPressed() {
        if(doubleBackToExitPressedOnce) {
            super.onBackPressed()
            val closeApp = Intent(Intent.ACTION_MAIN)
            closeApp.addCategory(Intent.CATEGORY_HOME)
            closeApp.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(closeApp)
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(context, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
//        Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}