package com.ralphabounader.eece451project
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuGeneralActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_general)

        val searchPatientsButton = findViewById<Button>(R.id.patientsInfoButton)
        val searchPersonnelButton = findViewById<Button>(R.id.personnelInfoButton)

        searchPatientsButton.setOnClickListener {
            val intent = Intent(this, SearchPatientActivity::class.java)
            intent.putExtra("authToken", LoginActivity.authToken)
            startActivity(intent)
        }

        searchPersonnelButton.setOnClickListener {
            val intent = Intent(this, SearchPersonnelActivity::class.java)
            intent.putExtra("authToken", LoginActivity.authToken)
            startActivity(intent)
        }
    }
}

