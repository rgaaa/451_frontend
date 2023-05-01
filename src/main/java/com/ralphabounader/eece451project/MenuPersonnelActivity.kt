package com.ralphabounader.eece451project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuPersonnelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_personnel)

        val checkPatientsInfoButton = findViewById<Button>(R.id.checkPatientsInfoButton)
        val myInfoPersonnelButton = findViewById<Button>(R.id.myInfoPersonnelButton)

        checkPatientsInfoButton.setOnClickListener {
            startActivity(Intent(this, ConfirmationActivity::class.java))
        }

        myInfoPersonnelButton.setOnClickListener {
            startActivity(Intent(this, InfoAdminPersonnelActivity::class.java))
        }



        // Add your button click listeners here
    }
}
