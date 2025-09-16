package com.example.fishfreshness

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // ✅ tama na R

        val btnStartScan: Button = findViewById(R.id.btnStartScan)
        val aboutBtn: ImageButton = findViewById(R.id.aboutBtn)

        // Start scanning button
        btnStartScan.setOnClickListener {
            val intent = Intent(this, CamActivity::class.java)
            startActivity(intent)
        }

        // About button
        aboutBtn.setOnClickListener {
            AlertDialog.Builder(this@MainActivity)
                .setTitle("About This App")
                .setMessage(
                    "This app analyzes the freshness of Mackerel Scad fish in real-time.\n\n" +
                            "Developed for research and educational purposes."
                )
                .setPositiveButton("OK", null)
                .show()
        }
    }
}
