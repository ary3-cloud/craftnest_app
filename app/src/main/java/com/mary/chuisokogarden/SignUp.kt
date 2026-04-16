package com.mary.chuisokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val signuplink=findViewById<TextView>(R.id.signup_link)
        signuplink.setOnClickListener {
            val signuplinkIntent= Intent(applicationContext, SignIn::class.java)
            startActivity(signuplinkIntent)
        }
        val username=findViewById<EditText>(R.id.name)
        val email=findViewById<EditText>(R.id.email)
        val number=findViewById<EditText>(R.id.phone)
        val password=findViewById<EditText>(R.id.password)
        val signUp=findViewById<Button>(R.id.signup)
        signUp.setOnClickListener {
            val api="http://mary.alwaysdata.net/api/signup"
            val data= RequestParams()
            data.put("email",email.text.toString().trim())
            data.put("password",password.text.toString().trim())
            data.put("username",username.text.toString().trim())
            data.put("phone",number.toString().trim())


            val helper= ApiHelper(applicationContext)
            helper.post(api,data)


        }


    }
}