package com.martial.garageapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class GetAllMakersActivity : AppCompatActivity() {
    private lateinit var carRVModalArrayList : ArrayList<CarRVModal>
    private lateinit var carRVAdapter: CarRVAdapter
    private lateinit var carGetAllRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_all_makers)

        initialize()

        carRVModalArrayList = ArrayList()
        carRVAdapter = CarRVAdapter(carRVModalArrayList, this)
        carGetAllRV.layoutManager = LinearLayoutManager(this)
        carGetAllRV.setAdapter(carRVAdapter)
        carGetAllRV = findViewById(R.id.get_all_make_rv)

        getCarData()

    }
    private fun initialize() {

        carGetAllRV = findViewById(R.id.get_all_make_rv)
    }
    private fun getCarData() {
        val url = "https://vpic.nhtsa.dot.gov/api/vehicles/getallmakes?format=json"
        val requestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(
            Method.GET, url, null,
            Response.Listener { response ->

                try {
                    val dataArray = response.getJSONArray("Results")
                    for (i in 0 until dataArray.length()) {
                        val dataObj = dataArray.getJSONObject(i)
                        val Make_Name = dataObj.getString("Make_Name")
                        val Make_ID = dataObj.getString("Make_ID")
                        carRVModalArrayList.add(CarRVModal(Make_Name,Make_ID))

                    }
                    carRVAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(
                        this@GetAllMakersActivity,
                        "Failed to extract JSON data...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            Response.ErrorListener { error: VolleyError? ->
                Toast.makeText(this@GetAllMakersActivity, "Failed to get the data...", Toast.LENGTH_SHORT)
                    .show()
            })
        {


        }
        requestQueue.add(jsonObjectRequest)
    }

    private fun putCarData() {
        val url = "https://vpic.nhtsa.dot.gov/api/vehicles/getallmakes?format=json"
        val requestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(
            Method.PUT, url, null,
            Response.Listener { response ->

                try {
                    val dataArray = response.getJSONArray("Results")
                    for (i in 0 until dataArray.length()) {
                        val dataObj = dataArray.getJSONObject(i)
                        val Make_Name = dataObj.getString("Make_Name")
                        val Make_ID = dataObj.getString("Make_ID")
                        carRVModalArrayList.add(CarRVModal(Make_Name,Make_ID))

                    }
                    carRVAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(
                        this@GetAllMakersActivity,
                        "Failed to extract JSON data...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            Response.ErrorListener { error: VolleyError? ->
                Toast.makeText(this@GetAllMakersActivity, "Failed to get the data...", Toast.LENGTH_SHORT)
                    .show()
            })

        {


        }
        requestQueue.add(jsonObjectRequest)
    }

}