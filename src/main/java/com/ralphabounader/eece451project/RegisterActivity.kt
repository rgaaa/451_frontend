package com.ralphabounader.eece451project

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import models.UserRegistration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var fullNameEditText: EditText
    private lateinit var dateOfBirthEditText: EditText
    private lateinit var idCardNumberEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var cityAndCountryEditText: EditText
    private lateinit var medicalConditionsEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Get references to views
        fullNameEditText = findViewById(R.id.full_name_edit_text)
        dateOfBirthEditText = findViewById(R.id.date_of_birth_edit_text)
        idCardNumberEditText = findViewById(R.id.id_card_number_edit_text)
        phoneNumberEditText = findViewById(R.id.phone_number_edit_text)
        emailEditText = findViewById(R.id.email_edit_text)
        cityAndCountryEditText = findViewById(R.id.city_and_country_edit_text)
        medicalConditionsEditText = findViewById(R.id.medical_conditions_edit_text)
        usernameEditText = findViewById(R.id.username_edit_text)
        passwordEditText = findViewById(R.id.password_edit_text)
        registerButton = findViewById(R.id.register_button)

        // Set click listener for register button
        registerButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val fullName = fullNameEditText.text.toString()
        val dateOfBirth = dateOfBirthEditText.text.toString()
        val idCardNumber = idCardNumberEditText.text.toString()
        val phoneNumber = phoneNumberEditText.text.toString()
        val email = emailEditText.text.toString()
        val cityAndCountry = cityAndCountryEditText.text.toString()
        val medicalConditions = medicalConditionsEditText.text.toString()
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (fullName.isEmpty() || dateOfBirth.isEmpty() || idCardNumber.isEmpty() ||
            phoneNumber.isEmpty() || email.isEmpty() || cityAndCountry.isEmpty() ||
            medicalConditions.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        } else {
            val userRegistration = UserRegistration().apply {
                this.fullName = fullName
                this.dateOfBirth = dateOfBirth
                this.idCardNumber = idCardNumber
                this.phoneNumber = phoneNumber
                this.email = email
                this.cityAndCountry = cityAndCountry
                this.medicalConditions = medicalConditions
                this.username = username
                this.password = password
            }

            RetrofitInstance.apiService.registerUser(userRegistration).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@RegisterActivity, "Registration successful", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@RegisterActivity, "Registration failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    Log.e("RegisterActivity", t.message, t)

                }
            })
        }
    }
}

