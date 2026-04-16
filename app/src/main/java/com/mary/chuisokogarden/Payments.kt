package com.mary.chuisokogarden

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class Payments : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payments)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        receive /retrieve extras data the product name and cost
//        this data is passed via intents
        val productname = intent.getStringExtra("product_name")
        val productcost = intent.getIntExtra("product_cost",0)
        val productphoto = intent.getStringExtra("product_photo")


//                find the views by their ids
        val photo=findViewById<ImageView>(R.id.product_photo)
        val name =findViewById<TextView>(R.id.product_name)
        val cost=findViewById<TextView>(R.id.product_cost)
        val phone=findViewById<EditText>(R.id.phone)
        val pay=findViewById<Button>(R.id.pay)


//        sudo chown-R $USER:$USER .

//        update Textviews with values passed via intent
        name.text=productname
        cost.text="Ksh $productcost"

        Glide.with(this)
            .load(productphoto)
            .circleCrop()
            .into(photo)

        pay.setOnClickListener {
            //set Api endpoint
            val api="http://mary.alwaysdata.net/api/mpesa_payment"
            //create data using RequestParams ,put phone and cost as keyvalue pairs
            val data= RequestParams()
            data.put("amount",productcost)//passed via intent
            data.put("phone",phone.text.toString())//entered b user in the phone edittext

            //Access API helper
            val helper= ApiHelper(applicationContext)
            //post data to api endpoint
            helper.post(api,data)
        }

    }
}