package com.ddam40.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        btnSearch.setOnClickListener {
            saveData(txtWeight.text.toString().toInt(), txtHeight.text.toString().toInt())

            val intent = Intent(this, ResultActivity::class.java)
            with(intent) {
                putExtra("weight", txtWeight.text.toString())
                putExtra("height", txtHeight.text.toString())
            }

            startActivity(intent)
            //startActivity<ResultActivity>()
        }
    }

    private fun saveData(weight:Int, height:Int) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        editor.putInt("KEY_WEIGHT", weight)
            .putInt("KEY_HEIGHT", height)
            .apply()
    }

    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val weight = pref.getInt("KEY_WEIGHT", 0)
        val height = pref.getInt("KEY_HEIGHT", 0)

        if(weight != 0 && height != 0) {
            txtWeight.setText(weight.toString())
            txtHeight.setText(height.toString())
        }
    }
}
