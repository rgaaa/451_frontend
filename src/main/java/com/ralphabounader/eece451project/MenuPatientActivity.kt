package com.ralphabounader.eece451project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuPatientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_patient)
        val bookAppointmentButton = findViewById<Button>(R.id.bookAppointmentButton)
        val myInfoPatientButton = findViewById<Button>(R.id.myInfoPatientButton)

        bookAppointmentButton.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        myInfoPatientButton.setOnClickListener {
            startActivity(Intent(this, UserInfoActivity::class.java))
        }


        // Add your button click listeners here
    }
}
