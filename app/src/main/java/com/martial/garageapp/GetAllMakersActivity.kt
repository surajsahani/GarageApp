package com.martial.garageapp

import android.content.Intent
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

class GetAllMakersActivity : AppCompatActivity(), NewsItemClicked {
    private lateinit var carRVModalArrayList: ArrayList<CarRVModal>
    private lateinit var carRVModalPlusArrayList: ArrayList<CarRVModelPlus>
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
        getSingleCar()
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
                        carRVModalArrayList.add(CarRVModal(Make_Name, Make_ID))

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
                Toast.makeText(
                    this@GetAllMakersActivity,
                    "Failed to get the data...",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }) {


        }
        requestQueue.add(jsonObjectRequest)
    }

    private fun getSingleCar() {

        val url = "https://vpic.nhtsa.dot.gov/api/vehicles/GetModelsForMakeId/" + "11362" + "?format=json"

        val requestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(Method.PUT, url, null,
            Response.Listener { response ->

                try {
                    val dataArray = response.getJSONArray("Results")
                    for (i in 0 until dataArray.length()) {
                        val dataObj = dataArray.getJSONObject(i)
                        val Make_ID = dataObj.getString("Make_ID")
                        val Make_Name = dataObj.getString("Make_Name")
                        val Model_ID = dataObj.getString("Model_ID")
                        val Model_Name = dataObj.getString("Model_Name")
                        carRVModalPlusArrayList.add(CarRVModelPlus(Make_ID, Make_Name, Model_ID, Model_Name))

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
                Toast.makeText(
                    this@GetAllMakersActivity,
                    "Failed to get the data...",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }) {


        }
        requestQueue.add(jsonObjectRequest)
    }

    override fun onItemClicked(item: CarRVModal) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        // PASS MAKE_ID
        //Volley Request to put
    }
}