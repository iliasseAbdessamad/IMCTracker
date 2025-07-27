package net.iliasse.imctracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {

    private EditText editWeight, editHeight;
    private Button btnCalculate;
    private TextView resultText, resultText2;
    private ImageView imcStatusImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editWeight = findViewById(R.id.editWeight);
        editHeight = findViewById(R.id.editHeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        resultText = findViewById(R.id.resultText);
        imcStatusImage = findViewById(R.id.imcStatusImage);
        resultText2 = findViewById(R.id.resultText2);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateIMC();
            }
        });
    }

    private void calculateIMC() {
        String weightStr = editWeight.getText().toString();
        String heightStr = editHeight.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        float weight = Float.parseFloat(weightStr);
        float height = Float.parseFloat(heightStr) / 100;

        if (height == 0) {
            Toast.makeText(this, "La taille ne peut pas être zéro", Toast.LENGTH_SHORT).show();
            return;
        }

        float imc = weight / (height * height);
        resultText.setText(String.format("Votre IMC : %.2f", imc));

        if (imc < 18.5) {
            imcStatusImage.setImageResource(R.drawable.maigre);
            resultText2.setText("Maigreur");
        } else if (imc < 25) {
            imcStatusImage.setImageResource(R.drawable.normal);
            resultText2.setText("Normal");
        } else if (imc < 30) {
            imcStatusImage.setImageResource(R.drawable.surpoids);
            resultText2.setText("Surpoids");
        } else if(imc < 40){
            imcStatusImage.setImageResource(R.drawable.obese);
            resultText2.setText("Obésité modérée");
        }
        else {
            imcStatusImage.setImageResource(R.drawable.t_obese);
            resultText2.setText("Obésité sévère");
        }
    }
}
