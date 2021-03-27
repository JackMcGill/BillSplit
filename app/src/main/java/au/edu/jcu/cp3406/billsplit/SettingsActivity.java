package au.edu.jcu.cp3406.billsplit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    public static int SETTINGS_REQUEST = 7;
    private int tipAmount;
    private boolean isTipping;
    private SwitchCompat tipSwitch;
    private LinearLayout tipLinearLayout;
    private RadioButton[] tipButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        tipSwitch = findViewById(R.id.tipSwitch);
        tipLinearLayout = findViewById(R.id.tipLinearLayout);

        // Get state value
        tipAmount = getIntent().getIntExtra("tipAmount", 0);

        tipButtons = new RadioButton[4];
        tipButtons[0] = findViewById(R.id.radioButton10perCent);
        tipButtons[1] = findViewById(R.id.radioButton15perCent);
        tipButtons[2] = findViewById(R.id.radioButton20perCent);
        tipButtons[3] = findViewById(R.id.radioButton25perCent);

        updateSwitches();
    }

    // Update switches with state (if there is any) from the main screen
    public void updateSwitches() {
        if (tipAmount > 0) {
            isTipping = true;
            tipSwitch.setChecked(true);
            tipLinearLayout.setAlpha(1);
            for (RadioButton button : tipButtons) {
                button.setEnabled(true);

                String buttonAsString = button.getText().toString();

                // Remove '%' from string to extract the tip amount as an integer
                buttonAsString = buttonAsString.substring(0, buttonAsString.length() - 1);

                if (Integer.parseInt(buttonAsString) == tipAmount) {
                    button.setChecked(true);
                }
            }
        }
    }

    // Enable/disable radio buttons
    public void tipSwitchClicked(View view) {
        if (tipSwitch.isChecked()) {
            tipLinearLayout.setAlpha(1);
            isTipping = true;
            toggleRadioButtons(true);
        } else {
            tipLinearLayout.setAlpha((float) 0.3);
            isTipping = false;
            toggleRadioButtons(false);
        }
    }

    public void toggleRadioButtons(boolean isSwitched) {
        for (RadioButton button : tipButtons) {
            button.setEnabled(isSwitched);
        }
    }

    // Set tipAmount value
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

    // Return user (and state values)  to main screen
    public void doneButtonClicked(View view) {
        Intent data = new Intent();
        data.putExtra("tipAmount", tipAmount);
        data.putExtra("isTipping", isTipping);
        setResult(RESULT_OK, data);
        finish();
    }
}