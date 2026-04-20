package com.mary.chuisokogarden

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class About : AppCompatActivity() {
    //Declare a tt variable
    lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        find view by id

        val abouttext=findViewById<TextView>(R.id.textview)
        val inputText =findViewById<EditText>(R.id.inputText)
        val speakButton=findViewById<Button>(R.id.speakButton)

        //start the tts engin
        tts= TextToSpeech(this){
            //check if the speech is successful
            if (it== TextToSpeech.SUCCESS){
                tts.language= Locale.US
            }
        }
//        set button listener
        speakButton.setOnClickListener {
            val textt=abouttext.text.toString()
            tts.speak(textt, TextToSpeech.QUEUE_FLUSH,null,null)

            val text=inputText.text.toString()
            tts.speak(text, TextToSpeech.QUEUE_FLUSH,null,null)

        }

    }

    override fun onDestroy() {
        tts.stop()//stops tts
        tts.shutdown()
        super.onDestroy()
    }
}