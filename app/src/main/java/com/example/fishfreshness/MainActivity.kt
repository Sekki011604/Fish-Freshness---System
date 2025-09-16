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
                    "🐟 This application helps analyze the freshness of Mackerel Scad (\"Galunggong\") " +
                            "using real-time camera scanning or uploaded images.\n\n" +
                            "🔬 Purpose:\n" +
                            "• Assist consumers in identifying fish quality\n\n" +
                            "📱 Features:\n" +
                            "• Camera scanning with live preview\n" +
                            "• Upload and analyze stored images\n" +
                            "• Simple, research-focused design\n\n" +
                            "Developed as part of an academic project in Computer Science."
                )
                .setPositiveButton("OK", null)
                .show()
        }
    }
}
