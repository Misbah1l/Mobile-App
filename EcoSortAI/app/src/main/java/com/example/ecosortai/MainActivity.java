package com.example.ecosortai;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.Interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int IMAGE_SIZE = 224;
    private static final int NUM_CLASSES = 3;

    private ImageView imagePreview;
    private Button selectImageButton;
    private TextView predictionText;
    private TextView confidenceText;

    private Interpreter interpreter;
    private List<String> labels;

    private ActivityResultLauncher<String> imagePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagePreview = findViewById(R.id.imagePreview);
        selectImageButton = findViewById(R.id.selectImageButton);
        predictionText = findViewById(R.id.predictionText);
        confidenceText = findViewById(R.id.confidenceText);

        try {
            interpreter = new Interpreter(loadModelFile());
            labels = loadLabels();
        } catch (Exception e) {
            Toast.makeText(
                    this,
                    "Could not load AI model: " + e.getMessage(),
                    Toast.LENGTH_LONG
            ).show();

            selectImageButton.setEnabled(false);
            return;
        }

        // Open the image picker when the user taps the button.
        imagePicker = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        classifySelectedImage(uri);
                    }
                }
        );

        selectImageButton.setOnClickListener(v ->
                imagePicker.launch("image/*")
        );
    }

    // Load the TensorFlow Lite model from the assets folder.
    private ByteBuffer loadModelFile() throws IOException {

        try (InputStream inputStream =
                     getAssets().open("model_unquant.tflite")) {

            byte[] modelBytes = new byte[inputStream.available()];
            int totalBytes = 0;
            int count;

            while (totalBytes < modelBytes.length
                    && (count = inputStream.read(
                    modelBytes, totalBytes,
                    modelBytes.length - totalBytes)) != -1) {
                totalBytes += count;
            }

            ByteBuffer buffer = ByteBuffer.allocateDirect(totalBytes);
            buffer.order(ByteOrder.nativeOrder());
            buffer.put(modelBytes, 0, totalBytes);
            buffer.rewind();

            return buffer;
        }
    }

    // Read category names from labels.txt.
    private List<String> loadLabels() throws IOException {

        List<String> categoryLabels = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        getAssets().open("labels.txt")))) {

            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                // Remove the numeric index, e.g. "0 Plastics".
                int firstSpace = line.indexOf(' ');

                if (firstSpace >= 0) {
                    line = line.substring(firstSpace + 1).trim();
                }

                categoryLabels.add(line);
            }
        }

        if (categoryLabels.size() != NUM_CLASSES) {
            throw new IOException(
                    "Expected 3 labels, found "
                            + categoryLabels.size()
            );
        }

        return categoryLabels;
    }

    // Display the selected image and run AI classification.
    private void classifySelectedImage(Uri imageUri) {

        try (InputStream inputStream =
                     getContentResolver().openInputStream(imageUri)) {

            if (inputStream == null) {
                throw new IOException("Unable to open selected image.");
            }

            Bitmap originalBitmap =
                    BitmapFactory.decodeStream(inputStream);

            if (originalBitmap == null) {
                throw new IOException("Invalid image file.");
            }

            imagePreview.setImageBitmap(originalBitmap);

            Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                    originalBitmap,
                    IMAGE_SIZE,
                    IMAGE_SIZE,
                    true
            );

            ByteBuffer inputBuffer = ByteBuffer.allocateDirect(
                    1 * IMAGE_SIZE * IMAGE_SIZE * 3 * 4
            );

            inputBuffer.order(ByteOrder.nativeOrder());

            // Convert RGB pixel values to the range -1 to 1.
            for (int y = 0; y < IMAGE_SIZE; y++) {
                for (int x = 0; x < IMAGE_SIZE; x++) {

                    int pixel = resizedBitmap.getPixel(x, y);

                    float red =
                            (((pixel >> 16) & 0xFF) / 127.5f) - 1.0f;

                    float green =
                            (((pixel >> 8) & 0xFF) / 127.5f) - 1.0f;

                    float blue =
                            ((pixel & 0xFF) / 127.5f) - 1.0f;

                    inputBuffer.putFloat(red);
                    inputBuffer.putFloat(green);
                    inputBuffer.putFloat(blue);
                }
            }

            float[][] output = new float[1][NUM_CLASSES];

            inputBuffer.rewind();

            // Run the model to get prediction scores.
            interpreter.run(inputBuffer, output);

            // Find the category with the highest score.
            int bestIndex = 0;

            for (int i = 1; i < NUM_CLASSES; i++) {
                if (output[0][i] > output[0][bestIndex]) {
                    bestIndex = i;
                }
            }

            String predictedCategory = labels.get(bestIndex);
            float confidence = output[0][bestIndex] * 100.0f;

            predictionText.setText(
                    "Predicted Category: " + predictedCategory
            );

            confidenceText.setText(
                    String.format(
                            java.util.Locale.US,
                            "Confidence: %.2f%%",
                            confidence
                    )
            );

            resizedBitmap.recycle();

        } catch (Exception e) {
            predictionText.setText("Prediction failed.");
            confidenceText.setText(e.getMessage());

            Toast.makeText(
                    this,
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    @Override
    protected void onDestroy() {
        if (interpreter != null) {
            interpreter.close();
        }

        super.onDestroy();
    }
}