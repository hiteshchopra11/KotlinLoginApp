package com.example.mvvm.View.Activities


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.R
import com.example.mvvm.ViewModel.UserViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val activity = this@LoginActivity
    private lateinit var textInputEditTextEmail: EditText
    private lateinit var textInputEditTextPassword: EditText
    private lateinit var loginButton: TextView
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViews()
        initListeners()
    }


    private fun initViews() {
        textInputEditTextEmail = findViewById(R.id.email)
        textInputEditTextPassword = findViewById(R.id.password)
        loginButton = findViewById(R.id.signInButton)
        userViewModel = ViewModelProvider(
            this,
            UserViewModel.Factory(applicationContext)
        ).get(UserViewModel::class.java)
    }

    private fun initListeners() {
        loginButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (textInputEditTextEmail.toString().isNotEmpty() && textInputEditTextPassword.toString()
                .isNotEmpty()
        ) {
            login()
        }
    }

    private fun login() {
        val isValid = userViewModel.checkValidLogin(
            textInputEditTextEmail.text.toString(),
            textInputEditTextPassword.text.toString()
        )
        if (isValid) {
            Toast.makeText(baseContext, "Successfully Logged In!", Toast.LENGTH_LONG).show()
            Log.i("Successful_Login", "Login was successful")
            val accountsIntent = Intent(activity, HomeActivity::class.java)
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail!!.text.toString().trim { it <= ' ' })
            startActivity(accountsIntent)
        } else {
            Toast.makeText(baseContext, "Invalid Login!", Toast.LENGTH_SHORT).show()
            Log.i("Unsuccessful_Login", "Login was not successful")
        }
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}