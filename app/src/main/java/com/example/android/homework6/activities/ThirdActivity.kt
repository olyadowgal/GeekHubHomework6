package com.example.android.homework6.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.homework6.R
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE = 123
        const val EXTRA_UNITS = "UNITS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        btn_save_units.setOnClickListener {
            val checkedId = radio_group_units.checkedRadioButtonId
            when (checkedId) {
                R.id.rbtn_metric -> {
                    val newIntent = Intent()
                    newIntent.putExtra(EXTRA_UNITS, "metric")
                    setResult(Activity.RESULT_OK, newIntent)
                    finish()
                }
                R.id.rbtn_imperial -> {
                    val newIntent = Intent()
                    newIntent.putExtra(EXTRA_UNITS, "imperial")
                    setResult(Activity.RESULT_OK, newIntent)
                    finish()
                }
            }
        }
    }

}
