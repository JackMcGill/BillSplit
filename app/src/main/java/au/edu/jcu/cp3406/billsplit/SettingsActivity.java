package au.edu.jcu.cp3406.billsplit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    public static int SETTINGS_REQUEST = 7;
    private int tipAmount;
    private Switch tipSwitch;
    private RadioButton[] tipButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        tipButtons = new RadioButton[4];
        tipButtons[0] = findViewById(R.id.radioButton10perCent);
        tipButtons[1] = findViewById(R.id.radioButton20perCent);
        tipButtons[2] = findViewById(R.id.radioButton25perCent);
        tipButtons[3] = findViewById(R.id.radioButton0perCent);

    }

    public void radioButtonClicked(View view) {
        for (RadioButton button : tipButtons) {
            if (button.isChecked()) {
                String buttonAsString = button.getText().toString();

                // Remove '%' from string to extract the tip amount as an integer
                buttonAsString = buttonAsString.substring(0, buttonAsString.length() - 1);
                tipAmount = Integer.parseInt(buttonAsString);
            }
        }
    }


}