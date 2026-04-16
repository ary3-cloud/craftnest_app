package com.mary.chuisokogarden

import android.content.Intent
import android.os.Bundle
import android.view.textclassifier.TextLinks
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



//        intents
        val up=findViewById<Button>(R.id.up)
        up.setOnClickListener {
            val upIntent= Intent(applicationContext, SignUp::class.java)
            startActivity(upIntent)
        }

        val pop=findViewById<Button>(R.id.pop)
        pop.setOnClickListener {
            val popIntent= Intent(applicationContext, SignIn::class.java)
            startActivity(popIntent)
        }

        val progressBar=findViewById<ProgressBar>(R.id.progressbar)
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerview)


        val api="http://mary.alwaysdata.net/api/getproductdetails"

        val helper= ApiHelper(applicationContext)
        helper.loadProducts(api,recyclerView,progressBar)






    }
}