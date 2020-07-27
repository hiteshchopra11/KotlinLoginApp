package com.example.mvvm.View.Activities

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.R
import com.example.mvvm.ViewModel.UserViewModel

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private val regex: Regex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    private val activity = this@RegisterActivity
    private lateinit var textInputEditTextName: EditText
    private lateinit var textInputEditTextEmail: EditText
    private lateinit var textInputEditTextPassword: EditText
    private lateinit var textInputEditTextConfirmPassword: EditText
    private lateinit var buttonRegister: TextView
    private lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        // initializing the views
        initViews()
        // initializing the listeners
        initListeners()

    }

    private fun initViews() {
        textInputEditTextName = findViewById(R.id.name)
        textInputEditTextEmail = findViewById(R.id.email)
        textInputEditTextPassword = findViewById(R.id.password)
        textInputEditTextConfirmPassword = findViewById(R.id.confirm_password)
        buttonRegister = findViewById(R.id.signUpButton)
        userViewModel = ViewModelProvider(
            this,
            UserViewModel.Factory(applicationContext)
        ).get(UserViewModel::class.java)
    }

    private fun initListeners() {
        buttonRegister.setOnClickListener(this)
    }


    override fun onClick(p0: View?) {
        if (checkIfFilled(
                textInputEditTextName.text.toString(),
                textInputEditTextEmail.text.toString(),
                textInputEditTextPassword.text.toString(),
                textInputEditTextConfirmPassword.text.toString()
            ) && emailVerify(textInputEditTextEmail.text.toString()) && passwordMatch(
                textInputEditTextPassword.text.toString(),
                textInputEditTextConfirmPassword.text.toString()
            ) && minimumPasswordLength(textInputEditTextPassword.text.toString()) && minimumPasswordLength(
                textInputEditTextConfirmPassword.text.toString()
            )
        ) {
            createUser()
            Toast.makeText(this, "SUCCESSFULLY REGISTERED", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createUser() {
        userViewModel.createUser(
            textInputEditTextEmail.text.toString(),
            textInputEditTextName.text.toString(),
            textInputEditTextPassword.text.toString()
        )
    }


    private fun checkIfFilled(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        return if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
            true
        } else {
            Toast.makeText(activity, "Please fill all fields", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun emailVerify(email: String): Boolean {
        return if (email.matches(regex)) true
        else {
            Toast.makeText(activity, "Please enter a valid email", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun passwordMatch(password: String, confirmPassword: String): Boolean {
        return if (password == confirmPassword) true
        else {
            Toast.makeText(activity, "Passwords do not match", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun minimumPasswordLength(password: String): Boolean {
        return if (password.length > 5) true
        else {
            Toast.makeText(activity, "Please enter at least 6 digits", Toast.LENGTH_SHORT).show()
            false
        }
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}
