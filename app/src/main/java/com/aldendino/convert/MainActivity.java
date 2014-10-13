package com.aldendino.convert;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends Activity {

    private EditText inputEditText;
    private EditText outputEditText;
    private TextView inputLabel;
    private TextView outputLabel;
    private Button switchButton;
    private Button convertButton;

    private Boolean isCtoF;
    private DecimalFormat decimalFormat;

    private String celsius;
    private String fahrenheit;
    private String celsiusLabel;
    private String fahrenheitLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celsius = getResources().getString(R.string.celsius);
        fahrenheit = getResources().getString(R.string.fahrenheit);
        celsiusLabel = getResources().getString(R.string.celsius_label);
        fahrenheitLabel = getResources().getString(R.string.fahrenheit_label);

        inputEditText = (EditText) findViewById(R.id.inputEditText);
        outputEditText = (EditText) findViewById(R.id.outputEditText);
        inputLabel = (TextView) findViewById(R.id.inputLabel);
        outputLabel = (TextView) findViewById(R.id.outputLabel);
        switchButton = (Button) findViewById(R.id.switchButton);
        convertButton = (Button) findViewById(R.id.convertButton);

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchDirection();
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display();
            }
        });

        decimalFormat = new DecimalFormat("0.0");
        isCtoF = true;
        update();
    }

    private double convert(double input) {
        double converted;
        if(isCtoF) converted = input * 9 / 5 + 32;
        else converted = (input - 32) * 5 / 9;
        return converted;
    }

    private void display() {
        String input = inputEditText.getText().toString();
        if(!input.equals("")) {
            try {
                double inputTemp = Double.parseDouble(input);
                double outputTemp = convert(inputTemp);
                outputEditText.setText(decimalFormat.format(outputTemp));
            } catch(NullPointerException npe) {
                showToast("Input String is Null");
            } catch(NumberFormatException nfe) {
                showToast("Input String is not a Double");
            }
        } else {
            outputEditText.setText("");
        }
    }

    private void switchDirection() {
        isCtoF = !isCtoF;
        update();
        display();
    }

    private void update() {
        setHints();
        setLabels();
    }

    private void setHints() {
        if(isCtoF) {
            inputEditText.setHint(celsius);
            outputEditText.setHint(fahrenheit);
        } else {
            inputEditText.setHint(fahrenheit);
            outputEditText.setHint(celsius);
        }
    }

    private void setLabels() {
        if(isCtoF) {
            inputLabel.setText(celsiusLabel);
            outputLabel.setText(fahrenheitLabel);
        } else {
            inputLabel.setText(fahrenheitLabel);
            outputLabel.setText(celsiusLabel);
        }
    }

    private void showToast(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show();
    }
}
