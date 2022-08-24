package com.martial.garageapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.martial.garageapp.model.DataModel
import com.martial.garageapp.utils.SharedHelper
import org.json.JSONException


class MainActivity : AppCompatActivity() {
    private lateinit var shared: SharedHelper
    private lateinit var bt_logout: Button
    private lateinit var mContext: Context
    var doubleBackToExitPressedOnce = false
    private lateinit var mRequestQueue: RequestQueue
    var queue: RequestQueue? = null
    private lateinit var carRV: RecyclerView

    //private lateinit var carRVAdapter: CarRVAdapter

    private lateinit var carRVAdapterPlus: CarRVAdapterPlus

    //private lateinit var carRVModalArrayList: ArrayList<CarRVModal>

    private lateinit var carRVModalArrayListPlus: ArrayList<CarRVModalPlus>

    //private lateinit var  textInputLayout : TextInputLayout
    private lateinit var textInputLayout: AutoCompleteTextView

    private lateinit var autoCompleteTextViewMake: AutoCompleteTextView
    private lateinit var autoCompleteTextViewModel: AutoCompleteTextView
    private lateinit var addCar: Button
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        initialize()
        onclick()
        //volley()
//        mRequestQueue = Volley.newRequestQueue(this)
//        queue = Volley.newRequestQueue(mContext)
//        jsonVolley()
        //carRVModalArrayList = ArrayList()
        carRVModalArrayListPlus = ArrayList()

//        carRVAdapter = CarRVAdapter(carRVModalArrayList, this)
//        carRV.layoutManager = LinearLayoutManager(this)
//        //carRV.adapter = carRVAdapter
//        carRV.setAdapter(carRVAdapter)
//        //getCurrencyData()


        carRVAdapterPlus = CarRVAdapterPlus(carRVModalArrayListPlus, this)
        carRV.layoutManager = LinearLayoutManager(this)
        //carRV.adapter = carRVAdapter
        carRV.setAdapter(carRVAdapterPlus)
        //getCurrencyData()
    }


    private fun initialize() {
        autoCompleteTextViewMake = findViewById(R.id.autoCompleteTextViewMake)
        bt_logout = findViewById(R.id.bt_logout)
        mContext = this
        shared = SharedHelper(mContext)
        carRV = findViewById(R.id.rv_car)
//        textInputLayout = findViewById(R.id.model)
        addCar = findViewById(R.id.bt_addcar)
        autoCompleteTextViewModel = findViewById(R.id.autoCompleteTextViewModel)

    }

    private fun onclick() {
        bt_logout.setOnClickListener {
            shared.clear()
            Toast.makeText(mContext, "Logout Success", Toast.LENGTH_SHORT).show()
            val backToLogin = Intent(mContext, LoginActivity::class.java)
            backToLogin.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(backToLogin)
            finish()
        }
        autoCompleteTextViewMake.setOnClickListener {
            val intent = Intent(this, GetAllMakersActivity::class.java)
            startActivity(intent)
        }
        addCar.setOnClickListener {
            val makeID: String = autoCompleteTextViewModel.getText().toString()
            Toast.makeText(this, makeID, Toast.LENGTH_SHORT).show();
            getModeCarData()
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            val closeApp = Intent(Intent.ACTION_MAIN)
            closeApp.addCategory(Intent.CATEGORY_HOME)
            closeApp.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(closeApp)
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(mContext, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
    }

    private fun volley() {
        val textView = findViewById<TextView>(R.id.tv_yourcar)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://vpic.nhtsa.dot.gov/api/vehicles/getallmakes?format=json"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                // Display the first 500 characters of the response string.
                textView.text = "Response is: ${response.substring(0, 500)}"
            },
            { textView.text = "That didn't work!" })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    private fun jsonVolley() {

        val url = "https://vpic.nhtsa.dot.gov/api/vehicles/getallmakes?format=json"
        val requestQueue = Volley.newRequestQueue(mContext)
        // Initialize a new JsonObjectRequest instance
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                // Process the JSON
                try {
                    // Get the JSON array
                    val array = response.getJSONArray("Results")

                    // Loop through the array elements
                    for (i in 0 until array.length()) {
                        // Get current json object
                        val results = array.getJSONObject(i)
                        // Get the current student (json object) data
                        val makeID = results.getString("Make_ID")
                        val makeName = results.getString("Make_Name")

                        Toast.makeText(applicationContext, "makeid $makeID", Toast.LENGTH_SHORT)
                            .show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { error ->
            Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
        }

        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }

    private fun fetch() {
        val url = "https://vpic.nhtsa.dot.gov/api/vehicles/getallmakes?format=json"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, {
            val newsJsonArray = it.getJSONArray("Results")
            val newsArray = ArrayList<DataModel>()
            for (i in 0 until newsJsonArray.length()) {
                val resultJsonObject = newsJsonArray.getJSONObject(i)
                val carmodel = DataModel(
                    resultJsonObject.getString("Make_ID"),
                    resultJsonObject.getString("Make_Name")
                )
                newsArray.add(carmodel)
            }

        }) {

        }
    }

//    private fun getCarData() {
//
//        val url = "https://vpic.nhtsa.dot.gov/api/vehicles/getallmakes?format=json"
//        val requestQueue = Volley.newRequestQueue(this)
//        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(
//            Method.GET, url, null,
//            Response.Listener { response ->
//
//                try {
//                    val dataArray = response.getJSONArray("Results")
//                    for (i in 0 until dataArray.length()) {
//                        val dataObj = dataArray.getJSONObject(i)
//                        val Make_Name = dataObj.getString("Make_Name")
//                        val Make_ID = dataObj.getString("Make_ID")
//                        carRVModalArrayList.add(CarRVModal(Make_Name, Make_ID))
//
//                    }
//                    carRVAdapter.notifyDataSetChanged()
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                    Toast.makeText(
//                        this@MainActivity,
//                        "Failed to extract JSON data...",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            },
//            Response.ErrorListener { error: VolleyError? ->
//                Toast.makeText(this@MainActivity, "Failed to get the data...", Toast.LENGTH_SHORT)
//                    .show()
//            }) {
//
//
//        }
//        requestQueue.add(jsonObjectRequest)
//    }

    private fun getModeCarData() {

        val inputValueMakeID: String = autoCompleteTextViewModel.text.toString()


        val url = "https://vpic.nhtsa.dot.gov/api/vehicles/GetModelsForMakeId/" + inputValueMakeID + "?format=json"

//        Toast.makeText(this,"$MakeId , This is makeId", Toast.LENGTH_SHORT).show()
        val requestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(
            Method.GET, url, null,
            Response.Listener { response ->

                try {
                    val dataArray = response.getJSONArray("Results")
                    for (i in 0 until dataArray.length()) {
                        val dataObj = dataArray.getJSONObject(i)
                        //val makeName = dataObj.getString("Make_ID")
                        val makeName = dataObj.getString("Make_Name")
                        //val modelID = dataObj.getString("Model_ID")
                        val modelName = dataObj.getString("Model_Name")
                        //carRVModalArrayList.add(CarRVModal(Make_Name, Make_ID))
                        carRVModalArrayListPlus.add(
                            CarRVModalPlus(
                                makeName,
                                modelName
                            )
                        )

                    }
                    carRVAdapterPlus.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(
                        this@MainActivity,
                        "Failed to extract JSON data...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            Response.ErrorListener { error: VolleyError? ->
                Toast.makeText(this@MainActivity, "Failed to get the data...", Toast.LENGTH_SHORT)
                    .show()
            }) {


        }
        requestQueue.add(jsonObjectRequest)
    }

}