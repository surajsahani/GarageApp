package com.martial.garageapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.martial.garageapp.database.UserDatabase
import com.martial.garageapp.database.UserEntity
import com.martial.garageapp.utils.Constant
import com.martial.garageapp.utils.SharedHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var  et_username : EditText
    private lateinit var  et_password : EditText
    private lateinit var  bt_signup : Button
    private lateinit var  bt_login : Button
    private  var user: UserEntity? = null
    private var dataUser: UserDatabase? = null
    private lateinit var context: Context
    private lateinit var shared: SharedHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        initialize()
        onclick()
    }

    override fun onStart() {
        super.onStart()
        if(shared.getBoolean(Constant.LOGIN, false)) {
            val alreadyLoggedIn = Intent(context, MainActivity::class.java)
            startActivity(alreadyLoggedIn)
        }
    }

    private fun initialize(){
        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        bt_signup = findViewById(R.id.bt_signup)
        bt_login = findViewById(R.id.bt_login)
        context = this
        dataUser = UserDatabase.getInstance(context)
        shared = SharedHelper(context)
    }
    private fun onclick(){
        bt_signup.setOnClickListener {
            val signup = Intent(this, SignupActivity::class.java)
            startActivity(signup)
        }
        bt_login.setOnClickListener {

            val user = et_username.text.toString()
            val pass = et_password.text.toString()

            when {
                user.isEmpty() && pass.isEmpty() -> {
                    et_username.error = "Fill the username"
                    et_password.error = "Fill the password"
                }
                user.isEmpty() -> et_username.error = "Fill the username"
                pass.isEmpty() -> et_password.error = "Fill the password"
                else -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        val data = dataUser?.userDao()?.getUsername(user)
                        when(data?.username) {
                            user -> when(data.password) {
                                pass ->  {
                                    loginSession(user,pass)
                                    Toast.makeText(context, "Login success", Toast.LENGTH_SHORT).show()

                                    val loginSuccess = Intent(context, MainActivity::class.java)
                                    startActivity(loginSuccess)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private fun loginSession(user: String, pass: String) {
        shared.apply {
            put(Constant.USERNAME, user)
            put(Constant.PASSWORD, pass)
            put(Constant.LOGIN, true)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val closeApp = Intent(Intent.ACTION_MAIN)
        closeApp.addCategory(Intent.CATEGORY_HOME)
        closeApp.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(closeApp)

    }
}