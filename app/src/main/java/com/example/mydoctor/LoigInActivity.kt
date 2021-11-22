package com.example.mydoctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LoigInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loig_in)

        val clickRegister = findViewById(R.id.clickRegister) as TextView
        clickRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java);
            startActivity(intent)

        }

        val loginbtm = findViewById(R.id.loginbtm) as TextView
        loginbtm.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)

        }


    }
}