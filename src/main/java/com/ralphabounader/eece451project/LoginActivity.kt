package com.ralphabounader.eece451project

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import models.UserLogin
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize ApiService
        apiService = RetrofitInstance.apiService

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val userTypeSpinner = findViewById<Spinner>(R.id.userTypeSpinner)
        val registerTextView = findViewById<TextView>(R.id.registerTextView)

        val loginOptions = arrayOf("Patient", "Medical Personnel", "Admin")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, loginOptions)
        userTypeSpinner.adapter = adapter

        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val userType = userTypeSpinner.selectedItem.toString()

            loginUser(username, password)
        }
    }

    private fun loginUser(username: String, password: String) {
        val userLogin = UserLogin(username, password)
        apiService.loginUser(userLogin).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val userTypeSpinner = findViewById<Spinner>(R.id.userTypeSpinner)

                    // Save the auth token
                    authToken = response.headers()["Authorization"]

                    // Login successful, navigate to the appropriate activity based on the user type
                    when (userTypeSpinner.selectedItem.toString()) {
                        "Patient" -> startActivity(Intent(this@LoginActivity, MenuPatientActivity::class.java))
                        "Medical Personnel" -> startActivity(Intent(this@LoginActivity, MenuPersonnelActivity::class.java))
                        "Admin" -> startActivity(Intent(this@LoginActivity, MenuGeneralActivity::class.java))
                    }
                } else {
                    // Login failed, show an error message
                    Toast.makeText(this@LoginActivity, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Network error or other issues, show an error message
                Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        var authToken: String? = null
    }

}
