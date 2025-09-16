package com.example.fishfreshness

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * CamActivity
 * ----------------------
 * This activity handles two main functions:
 * 1. Real-time camera preview (using CameraX API)
 * 2. Uploading images from the gallery (using Activity Result API)
 *
 * - CameraX is used for live preview and scanning fish freshness via camera.
 * - Gallery picker lets the user upload an image for analysis.
 *
 * NOTE:
 * - `startActivityForResult()` is deprecated, so this uses
 *   `registerForActivityResult()` for cleaner and modern handling of results.
 * - Extend this activity to include ML/AI-based analysis on the captured
 *   or uploaded image.
 */
class CamActivity : AppCompatActivity() {

    private lateinit var previewView: PreviewView
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var btnUpload: Button
    private lateinit var btnScan: Button

    // Activity Result Launcher for image picker
    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val selectedImageUri: Uri? = data?.data
                if (selectedImageUri != null) {
                    Toast.makeText(this, "Image selected: $selectedImageUri", Toast.LENGTH_SHORT).show()
                    // TODO: Call your ML model / analysis function here
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cam_activity)

        previewView = findViewById(R.id.previewView)
        btnUpload = findViewById(R.id.btnUpload)
        btnScan = findViewById(R.id.btnScan)

        cameraExecutor = Executors.newSingleThreadExecutor()

        // Start camera preview
        startCamera()

        // Upload button → Open gallery
        btnUpload.setOnClickListener {
            openGallery()
        }

        // Scan button → Camera analysis
        btnScan.setOnClickListener {
            Toast.makeText(this, "Scanning with camera...", Toast.LENGTH_SHORT).show()
            // TODO: Add scanning logic for camera input
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = androidx.camera.core.Preview.Builder().build()
            preview.setSurfaceProvider(previewView.surfaceProvider)

            val cameraSelector = androidx.camera.core.CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview)
            } catch (exc: Exception) {
                exc.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        pickImageLauncher.launch(intent) // Modern way (no deprecation)
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}
