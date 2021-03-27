package au.edu.jcu.cp3406.billsplit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;

public class BillSplitMainActivity extends AppCompatActivity {

    private Bill bill;
    private EditText billAmount;
    private EditText numberOfPeople;
    private TextView totalPerPersonView;
    private int amount;
    private int people;
    private int tipAmount;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmount = findViewById(R.id.billAmount);
        numberOfPeople = findViewById(R.id.numberOfPeople);
        totalPerPersonView = findViewById(R.id.totalPerPerson);

        tipAmount = 0; // Calculating/including a tip is disabled by default

        if (savedInstanceState == null) {
            bill = new Bill();
        } else {
            amount = savedInstanceState.getInt("amount");
            people = savedInstanceState.getInt("people");
            tipAmount = savedInstanceState.getInt("tipAmount");
            bill = new Bill();

            billAmount.setText(Integer.toString(amount));
            numberOfPeople.setText(Integer.toString(people));
            totalPerPersonView.setText(savedInstanceState.getString("totalPerPerson"));
        }
    }

    // Update state with existing values
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("amount", amount);
        outState.putInt("people", people);
        outState.putInt("tipAmount", tipAmount);
        outState.putString("totalPerPerson", totalPerPersonView.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    boolean isTipping = data.getBooleanExtra("isTipping", false);
                    if (isTipping) {
                        tipAmount = data.getIntExtra("tipAmount", 0);
                        toast = Toast.makeText(this, "Tip amount has been set to " +
                                tipAmount + "%", Toast.LENGTH_LONG);
                    } else {
                        tipAmount = 0;
                        toast = Toast.makeText(this, "No tip was set", Toast.LENGTH_LONG);
                    }

                    toast.show();
                }
            }
        }
    }

    // Send tipAmount state and user to the settings page
    public void settingsClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("tipAmount", tipAmount);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    // Clear the textfields and update the Bill properties to 0
    public void resetButtonClicked(View view) {
        bill.resetBill();
        billAmount.setText("");
        numberOfPeople.setText("");
        totalPerPersonView.setText(bill.toString());
    }

    // Calculate amount and display to UI
    public void calculateButtonClicked(View view) {
        if (!billAmount.getText().toString().isEmpty()) {

            // Get user input from UI
            amount = Integer.parseInt(billAmount.getText().toString());
            people = Integer.parseInt(numberOfPeople.getText().toString());

            // Update the Bill object values
            bill.setAmount(amount);
            bill.setNumberOfPeople(people);

            // Output values to the UI
            bill.calculateTotalPerPerson(tipAmount);
            String perPersonString = bill.toString();
            String output;
            if (tipAmount > 0) {
                output = MessageFormat.format("{0}/ person (includes {1}% tip)",
                        perPersonString, tipAmount);
            } else {
                output = perPersonString + "/ person";
            }
            totalPerPersonView.setText(output);

        } else {
            toast = Toast.makeText(this, "Please enter an amount and number of " +
                    "people", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}