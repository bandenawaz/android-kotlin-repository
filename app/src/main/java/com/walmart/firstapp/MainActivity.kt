package com.walmart.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var layout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initailization
        etUsername = findViewById(R.id.editTextUsername)
        etPassword = findViewById(R.id.editTextPassword)
        layout = findViewById(R.id.linearLayout)
        btnLogin = findViewById(R.id.buttonSubmit)

        //event listeners
        btnLogin.setOnClickListener{

            //Toast.makeText(applicationContext,"You clicked the button", Toast.LENGTH_LONG).show()
//            val snack = Snackbar.make(layout,"You clicked the button", Snackbar.LENGTH_LONG)
//            snack.show()

            val user: String = etUsername.text.toString()
            val password: String = etPassword.text.toString()

            if (user.equals("nawaz") and password.equals("123456")){

                val intent = Intent(applicationContext,WalmartActivity::class.java)
                startActivity(intent)
            }else{
                val snack = Snackbar.make(layout,"Access Denied", Snackbar.LENGTH_LONG)
               snack.show()
            }
        }
    }
}