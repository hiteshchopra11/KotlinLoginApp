package com.example.mvvm.View.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm.R

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var signIn: TextView
    private lateinit var signUp: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initListeners()
    }

    private fun initViews() {
        signIn = findViewById(R.id.signIn)
        signUp = findViewById(R.id.signUp)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.signUp -> startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
            R.id.signIn -> startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }
    }

    private fun initListeners() {
        signIn.setOnClickListener(this)
        signUp.setOnClickListener(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        return
    }
}