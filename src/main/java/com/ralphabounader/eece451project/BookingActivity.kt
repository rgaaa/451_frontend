package com.ralphabounader.eece451project

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.ralphabounader.eece451project.RetrofitInstance.apiService
import models.AppointmentData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class BookingActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val datePicker = findViewById<DatePicker>(R.id.datePicker)
        val timePicker = findViewById<TimePicker>(R.id.timePicker)
        val scheduleButton = findViewById<Button>(R.id.scheduleButton)

        scheduleButton.setOnClickListener {
            val day = datePicker.dayOfMonth
            val month = datePicker.month
            val year = datePicker.year
            val hour = timePicker.hour
            val minute = timePicker.minute

            val calendar = Calendar.getInstance().apply {
                set(year, month, day, hour, minute)
            }

            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
            val appointmentDate = dateFormat.format(calendar.time)

            val appointmentDateTime = dateFormat.format(calendar.time)

            scheduleAppointment(appointmentDate, appointmentDateTime)
        }
    }

    private fun scheduleAppointment(date: String, time: String) {
        val pk = 1 // Replace this with the actual patient ID obtained from the server.
        val appointmentData = AppointmentData(date, time)

        val call = apiService.scheduleAppointment("Token ${LoginActivity.authToken!!}", pk, appointmentData)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    showSuccessDialog()
                } else {
                    showErrorDialog()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                showErrorDialog()
            }
        })
    }


    private fun showSuccessDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Success")
        builder.setMessage("Your appointment has been scheduled.")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            finish()
        }
        builder.show()
    }

    private fun showErrorDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Failed to schedule your appointment. Please try again.")
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }
}

