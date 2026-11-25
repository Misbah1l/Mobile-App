# ♻️ EcoSort AI

<p align="center">
  <strong>AI-Based Waste Classification Android Application</strong>
  <br>
  An intelligent mobile application that classifies waste images using Machine Learning and TensorFlow Lite.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android">
  <img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=java&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/ML-TensorFlow%20Lite-FF6F00?style=for-the-badge&logo=tensorflow&logoColor=white" alt="TensorFlow Lite">
  <img src="https://img.shields.io/badge/UI-XML-blue?style=for-the-badge&logo=xml&logoColor=white" alt="XML">
</p>

---

## 🌱 Project Overview

**EcoSort AI** is an AI-powered Android application developed to demonstrate the integration of Machine Learning into mobile applications.

The app uses a **TensorFlow Lite model** to analyze waste images and classify them into three categories: **Plastics, Metal, and Paper**.

By performing model inference directly on the Android device, EcoSort AI demonstrates how machine learning can be integrated into mobile applications without requiring a remote prediction server.

## 🎯 Project Objectives

- Integrate a TensorFlow Lite model into an Android application.
- Enable users to select waste images from their device gallery.
- Perform image classification directly on the device.
- Display the predicted waste category and confidence score.
- Understand the workflow of deploying Machine Learning models on Android.

## ✨ Key Features

- **Image Selection** — Select waste images using the Android image picker.
- **Image Preview** — View the selected image before classification.
- **AI-Based Classification** — Predict the waste category using a TensorFlow Lite model.
- **Three Supported Categories** — Classify images into Plastics, Metal, or Paper.
- **Confidence Score** — Display the model's prediction score.
- **On-Device Inference** — Run predictions locally without requiring an online prediction service.

## 🧠 Supported Waste Categories

<details>
<summary><strong>View classification categories</strong></summary>

| Category | Description |
|---|---|
| 🧴 Plastics | Plastic waste items |
| 🔩 Metal | Metal waste items |
| 📄 Paper | Paper-based waste items |

</details>

## ⚙️ Model Information

| Model Component | Details |
|---|---|
| Model File | `model_unquant.tflite` |
| Labels File | `labels.txt` |
| Framework | TensorFlow Lite |
| Input | Selected waste image |
| Output | Predicted category and confidence score |
| Inference | On-device |

The model files are stored in the Android app's assets directory:

```text
app/src/main/assets/
├── model_unquant.tflite
└── labels.txt
```

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Android Studio | Android application development |
| Java | Application logic |
| XML | User interface layouts |
| Gradle Groovy DSL | Build configuration |
| TensorFlow Lite | On-device machine learning inference |
| Android Image Picker | Selecting images from device storage |

## 📂 Project Structure

```text
EcoSortAI/
│
├── app/
│   └── src/
│       └── main/
│           ├── assets/
│           │   ├── model_unquant.tflite
│           │   └── labels.txt
│           │
│           ├── java/
│           │   └── com/example/ecosortai/
│           │
│           ├── res/
│           └── AndroidManifest.xml
│
├── gradle/
├── build.gradle
├── settings.gradle
└── README.md
```

## 🚀 Getting Started

### Prerequisites

- Android Studio
- Java development environment
- Android emulator or physical Android device

### Installation and Setup

1. Clone the repository:

   ```bash
   git clone <your-repository-url>
   ```

2. Open Android Studio.
3. Select **Open** and choose the `EcoSortAI` project folder.
4. Allow Gradle synchronization to complete.
5. Connect an Android device or start an emulator.
6. Click **Run** to install and launch the application.

### Using the Application

1. Launch EcoSort AI.
2. Tap **Select Waste Image**.
3. Choose an image from the device gallery.
4. Preview the selected image.
5. View the predicted waste category and confidence score.

## 🧪 Testing

The application was installed and tested on a **Vivo Y19s** Android device.

Testing included:

- Opening the application successfully.
- Selecting waste images from the device gallery.
- Previewing selected images.
- Testing image classification for the supported categories.
- Checking the displayed prediction and confidence score.

Screenshots of the application and test results are available in the assignment's `Screenshots/` folder.

## ⚠️ Limitations

- The model supports only three waste categories: Plastics, Metal, and Paper.
- Prediction quality depends on the model's training data and the input image.
- The confidence score represents the model's output and does not guarantee that a prediction is correct.
- Items outside the supported categories may be classified incorrectly.

## 📚 Acknowledgements & References

This project was developed with guidance from the following reference repositories:

1. **Android TensorFlow Lite Reference:** [NumberGuessingAppTflite](https://github.com/Uzair390-Del/NumberGuessingAppTflite) — Used to understand the Android project structure and TensorFlow Lite integration workflow.

2. **Waste Classification Model:** [Smart-Waste-Classifier](https://github.com/hmaq-44/Smart-Waste-Classifier) — Used as the source of the waste classification model integrated into EcoSort AI.

## 👩‍💻 Developer

**Misbah Saeed**

BS Computer Science Student

---

<p align="center">
  <strong>EcoSort AI</strong>
  <br>
  ♻️ Making waste classification smarter through Artificial Intelligence.
</p>
