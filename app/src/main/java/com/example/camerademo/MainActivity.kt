package com.example.camerademo

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceContour
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceLandmark
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import java.io.IOException

class MainActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //build a button that launches the camera app
        findViewById<Button>(R.id.button).setOnClickListener {

            //Create an Intent, that launches the camera and takes a picture. we want to capture  a specific picture
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            // we are using the Activity for result so we can access another app and get the picture back as a result
           // The try/catch block because because there might not a phone that can satisfiy Intent of the it capturing the image
            try {
                // In our specific app, we are starting another app in our photo, and we want the result to come which is the  picture so we can access it.
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)//This request code is needed because might be coming back from another app, and we only want to process the request code, from the app that is taking the picture
            } catch (e: ActivityNotFoundException) {
                // display error state to the user
            }

        }
    }
//We have to override the on ActivityResult, and handle the result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    // if we checking if we are coming back from the camera app via checking if it has the requestCode "Request_Image_Capture")

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // We want to capture the bitmap that is the image that we took from the camera
            //grab bitmap
                val imageBitmap = data?.extras?.get("data") as Bitmap// add question marks because they can potentially be null to handle when we don't get useful information back
            // we want to set that image in our imageView
            // Set bitmap as ImageView Image
            findViewById<ImageView>(R.id.imageView).setImageBitmap(imageBitmap)

            //Prepare a bitmap for ML Kit API's
            val image = InputImage.fromBitmap(imageBitmap, 0)

            // To use default options:
            val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)//Here we are sayig we want to use the image Labeling API

            // Or, to set the minimum confidence required:
            // val options = ImageLabelerOptions.Builder()
             //     .setConfidenceThreshold(0.7f)
            //     .build()
            // val labeler = ImageLabeling.getClient(options)

            var screenText = " "

            labeler.process(image)
                .addOnSuccessListener { labels ->
                    Log.i("ana","successfully processed image")
                    for (label in labels) {
                        //what was detected in the image
                        val text = label.text
                        //the confidence score of what was detected
                        val confidence = label.confidence

                        val index = label.index

                        Log.i("ana","detected: " + text + " with confidence" + confidence )
                        val textViewDisplay = findViewById<TextView>(R.id.textView)
                        screenText += "$text : $confidence %\n "
                        textViewDisplay.text = screenText
                    }
                }
                .addOnFailureListener { e ->
                Log.e("ana","Error processing")
                }



    }
}



}