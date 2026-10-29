# EcoSort AI — AI-Based Waste Classification App

## Project Overview

EcoSort AI is an Android application that uses a TensorFlow Lite machine learning model to classify waste images into three categories: Plastics, Metal, and Paper. The application performs inference on the device.

## Objectives

* Integrate a TensorFlow Lite model into an Android application.
* Select waste images from the device's gallery.
* Display the predicted waste category and model confidence score.
* Understand the process of running machine learning inference on Android.

## Features

* Select images using the Android image picker.
* Preview the selected image.
* Classify images into Plastics, Metal, or Paper.
* Display the predicted category and confidence score.
* Run model inference locally on the device.

## Technologies Used

* Android Studio
* Java
* XML
* Gradle Groovy DSL
* TensorFlow Lite
* Android Image Picker

## Model Information

The application uses `model_unquant.tflite` and `labels.txt`, stored in `app/src/main/assets/`.

The model's supported categories are:

1. Plastics
2. Metal
3. Paper

## Project Structure

```text
EcoSortAI/
├── app/
│   └── src/main/
│       ├── assets/
│       │   ├── model_unquant.tflite
│       │   └── labels.txt
│       ├── java/
│       └── res/
├── gradle/
├── build.gradle
└── settings.gradle
```

## How to Run

1. Open the project in Android Studio.
2. Allow Gradle Sync to finish.
3. Connect an Android device or start an emulator.
4. Run the application using the Run button.
5. Tap **Select Waste Image** and choose an image to view the prediction.

## Testing

The application was launched on a Vivo Y19s device. Image selection and prediction were tested using waste images. Screenshots of the test results are included in the assignment's Screenshots folder.

## Limitations

The model supports only three categories. Prediction quality depends on the model's training data and the selected image. The confidence score is the model's output score and should not be treated as a guarantee of correctness.
