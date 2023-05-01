package com.ralphabounader.eece451project

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.ralphabounader.eece451project.RetrofitInstance.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPatientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_patient)

        val authToken = intent.getStringExtra("authToken") ?: ""
        fetchListData(authToken, isPatient = true)
    }


    private fun fetchListData(authToken: String, isPatient: Boolean) {
        val call: Call<List<String>> = if (isPatient) {
            apiService.listPatients("Token $authToken")
        } else {
            apiService.listMedicalPersonnel("Token $authToken")
        }

        call.enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    val dataList = response.body() ?: emptyList()
                    updateListView(dataList)
                } else {
                    // Handle error response
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                // Handle network error or other issues
            }
        })
    }

    private fun updateListView(dataList: List<String>) {
        val listView = findViewById<ListView>(R.id.listView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)
        listView.adapter = adapter
    }

}
