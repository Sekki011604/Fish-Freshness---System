🐟 Fish Freshness App

An Android application that analyzes the freshness of Mackerel Scad (Galunggong) fish using real-time camera scanning or uploaded images.
Developed as part of a Computer Science research project.

📌 Features

📷 Real-time Camera Scanning – Detects fish freshness instantly using live camera preview.

🖼 Image Upload – Analyze fish freshness from stored images.

ℹ️ About Section – Provides details about the app’s purpose and features.

🎨 Simple & Clean UI – Designed for usability and research use.

🛠 Tech Stack

Language: Kotlin

Framework: Android SDK

UI: XML Layouts, Material Design

Camera: AndroidX CameraX API

Dialog: AlertDialog for About/Info

Fish-Freshness-App/
│── app/src/main/java/com/example/fishfreshness/
│   │── MainActivity.kt       # Main screen with About + Start Scan
│   │── CamActivity.kt        # Camera activity for scanning
│
│── app/src/main/res/layout/
│   │── activity_main.xml     # Main UI
│   │── activity_cam.xml      # Camera preview UI
│
│── app/src/main/res/drawable/
│   │── ic_info.png           # About icon
│   │── sample_fish.png       # Sample fish images
│
│── README.md                 # Project documentation


git clone https://github.com/yourusername/Fish-Freshness-App.git
cd Fish-Freshness-App
