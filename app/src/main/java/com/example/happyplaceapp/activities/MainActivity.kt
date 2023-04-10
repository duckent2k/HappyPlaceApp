package com.example.happyplaceapp.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.happyplaceapp.adapters.HappyPlacesAdapter
import com.example.happyplaceapp.database.DatabaseHandler
import com.example.happyplaceapp.databinding.ActivityMainBinding
import com.example.happyplaceapp.models.HappyPlaceModel
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private val boardLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//        result ->
//        if (result.resultCode == Activity.RESULT_OK){
//
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.fabAddHappyPlace.setOnClickListener {
            val intent = Intent(this, AddHappyPlaceActivity::class.java)
            startActivityForResult(intent, ADD_PLACE_ACTIVITY_REQUEST_CODE)
        }

        getHappyPlaceListFromLocalDB()

    }


    private fun setupHappyPlacesRecyclerView(happyPlaceList: ArrayList<HappyPlaceModel>) {
        binding.rvHappyPlacesList.layoutManager = LinearLayoutManager(this)

        binding.rvHappyPlacesList.setHasFixedSize(true)


        val placesAdapter = HappyPlacesAdapter(this, happyPlaceList)
        binding.rvHappyPlacesList.adapter = placesAdapter
    }

    private fun getHappyPlaceListFromLocalDB() {
        val dbHandler = DatabaseHandler(this)
        val getHappyPlaceList: ArrayList<HappyPlaceModel> = dbHandler.getHappyPlaceList()

        if (getHappyPlaceList.size > 0) {
            binding.rvHappyPlacesList.visibility = View.VISIBLE
            binding.tvNoRecordsAvailable.visibility = View.GONE
            setupHappyPlacesRecyclerView(getHappyPlaceList)
        } else {
            binding.rvHappyPlacesList.visibility = View.GONE
            binding.tvNoRecordsAvailable.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PLACE_ACTIVITY_REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                getHappyPlaceListFromLocalDB()
            } else{
                Log.e("Activity", "Cancelled or Back pressed")
            }
        }
    }

    companion object {
        var ADD_PLACE_ACTIVITY_REQUEST_CODE = 1
    }
}