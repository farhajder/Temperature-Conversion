package com.example.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView resHistoryBox;
    private EditText Ftext;
    private EditText Ctext;
    private RadioButton toCRadio;
    private RadioButton toFRadio;
    private TextView Ftextview;
    private TextView Ctextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resHistoryBox = findViewById(R.id.resultText);
        resHistoryBox.setMovementMethod(new ScrollingMovementMethod());
        Ftext = findViewById(R.id.FahrenheitNumBox);
        Ctext = findViewById(R.id.CelsiusNumBox);
        toCRadio = findViewById(R.id.FToCradioButton);
        toFRadio = findViewById(R.id.CToFradioButton2);
        Ftextview = findViewById(R.id.FahrenheitDegtextView);
        Ctextview = findViewById(R.id.CelsiusDegtextView);

        if(savedInstanceState == null)
            Toast.makeText(this, "Bundle Null", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Bundle Not Null", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        outState.putString("History", resHistoryBox.getText().toString());
        outState.putString("Fahrenheit Degree Text", Ftextview.getText().toString());
        outState.putString("Celsius Degree Text", Ctextview.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        resHistoryBox.setText(savedInstanceState.getString("History"));
        Ftextview.setText(savedInstanceState.getString("Fahrenheit Degree Text"));
        Ctextview.setText(savedInstanceState.getString("Celsius Degree Text"));
    }

    public double toCconversion(double value){
        return ((value - 32.0) / 1.8);
    }

    public double toFconversion(double value){
        return ((value * 1.8) + 32);
    }

    public void radioClick(View v){
        boolean checked = ((RadioButton) v).isChecked();
        switch (v.getId()) {
            case R.id.FToCradioButton:
                Log.d(TAG, "radioClick: FtoCradioButton selected");
                if (checked) {
                    Toast.makeText(this, "Fahrenheit to Celsius selected", Toast.LENGTH_SHORT).show();
                    Ftextview.setText("Fahrenheit Degree: ");
                    Ctextview.setText("Celsius Degree: ");
                    Ftext.setText("");
                    Ctext.setText("");
                }
                    break;
            case R.id.CToFradioButton2:
                Log.d(TAG, "radioClick: CtoFradioButton selected");
                if (checked) {
                    Toast.makeText(this, "Celsius to Fahrenheit selected", Toast.LENGTH_SHORT).show();
                    Ftextview.setText("Celsius Degree: ");
                    Ctextview.setText("Fahrenheit Degree: ");
                    Ftext.setText("");
                    Ctext.setText("");
                }
                    break;
        }
    }

    public void doConvert (View v) {
        String name = ((Button) v).getText().toString();
        Log.d(TAG, "doConvert: " + name);
        String FtextStr = Ftext.getText().toString();
        String CtextStr = Ctext.getText().toString();
        if (toCRadio.isChecked()) {
            if (FtextStr.trim().isEmpty())
                return;
            else {
                double fValue = Double.parseDouble(Ftext.getText().toString());
                Log.d(TAG, "doConvert: Fahrenheit Degree Input: " + fValue);
                double FtoCconversion = toCconversion(fValue);
                Log.d(TAG, "doConvert: FtoC Conversion :" + FtoCconversion);
                String resFtoCbox = String.format(" %.1f ", FtoCconversion);
                String outputFtoCres = String.format("%.1f F => %.1f C%n", fValue, FtoCconversion);
                String orgtoC = resHistoryBox.getText().toString();
                resHistoryBox.setText(outputFtoCres + orgtoC);
                Ctext.setText(resFtoCbox);
                Ftext.setText("");
            }
        }
        else {
            if (toFRadio.isChecked()) {
                double cValue = Double.parseDouble(Ftext.getText().toString());
                Log.d(TAG, "doConvert: Celsius Degree Input: " + cValue);
                double CtoFconversion = toFconversion(cValue);
                Log.d(TAG, "doConvert: FtoC Conversion :" + CtoFconversion);
                String resCtoFbox = String.format(" %.1f ", CtoFconversion);
                String outputCtoFres = String.format("%.2f C => %.2f F%n", cValue, CtoFconversion);
                String orgtoF = resHistoryBox.getText().toString();
                resHistoryBox.setText(outputCtoFres + orgtoF);
                Ctext.setText((resCtoFbox));
                Ftext.setText("");
            }
        }
    }

    public void clearButton(View v){
        String name = ((Button) v).getText().toString();
        Log.d(TAG, "Clear Button: " + name);
        resHistoryBox.setText("");
        Ctext.setText("");
    }
}
