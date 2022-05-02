# Machine Learning sample

Camera demo is a sample android camera recognition app that uses Machine Learning to recognize the objects built in an image captured from the phone's camera.

Machine Learning is about detecting and recognizing patterns.  Patterns can be found in images, text, financial data, sounds, language translation and everything in our day to life.

The Camera demo app is built using Google's ML kit software development kit for object recognition.


The goal of the sample is to showcase the current UI capabilities of Camera Demo App.

To try out this sample app, you need to use 
[Android Studio Arctic Fox](https://developer.android.com/studio).
You can clone this repository or import the
project from Android Studio following the steps
[here](https://developer.android.com/jetpack/compose/setup#sample).

Screenshots
-----------
<img align="right" src="https://github.com/kayabliss/CameraDemo/blob/master/CameraMLApp.gif" alt="A demo illustraating the UI of the app" width="288" height="512" style="display: inline; float: right"/>

## Features

This sample contains there parts: 
1) The ability to capture a photo image from the Android camera app and return that image to our main app by using a camera itents. [here](https://developer.android.com/training/camera/photobasics).

2) Using ML Kit to detect the objects in a photo and then display the associated accuracy for those objects.  The user interface(UI) shows the text labes of those objects.

3) Using ML kits's imagelabeling performs object recognition on that Bitmap image.  

### Main Languages used:
 - Kotlin
 - ML Kit Image Labeling
 - ML Kit Face Detection
 - Android Material Design
