package com.martial.garageapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.martial.garageapp.database.UserEntity
import com.martial.garageapp.repository.UserRepository

class SignupActivity : AppCompatActivity() {
    private lateinit var et_username: EditText
    private lateinit var et_password: EditText
    private lateinit var bt_signup: Button
    private lateinit var bt_login: Button
    var user: UserEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.hide()
        initialize()
        onclick()
    }

    private fun initialize() {
        et_username = findViewById(R.id.et_signup_username)
        et_password = findViewById(R.id.et_signup_password)
        bt_signup = findViewById(R.id.bt_signup)
        bt_login = findViewById(R.id.bt_login)
    }

    private fun onclick() {

        val repo = UserRepository(this)

        bt_signup.setOnClickListener {

            if (et_username.text.isNotEmpty() && et_password.text.isNotEmpty()) {

                user?.let {
                    val user = UserEntity(
                        id = it.id,
                        username = et_username.text.toString(),
                        password = et_password.text.toString()
                    )
                    repo.insertUser(user)

                } ?: kotlin.run {
                    val user = UserEntity(
                        id = it.id,
                        username = et_username.text.toString(),
                        password = et_password.text.toString()
                    )
                    repo.insertUser(user)

                    val login = Intent(this, LoginActivity::class.java)
                    Toast.makeText(this, "Register sucessfull", Toast.LENGTH_SHORT).show()
                    startActivity(login)
                    finish()
                }

            } else {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
            }

        }
        bt_login.setOnClickListener {
            val signup = Intent(this, LoginActivity::class.java)
            startActivity(signup)
        }

    }
}