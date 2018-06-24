
/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * This method is called when the +(plus) button is clicked
     */
    public void increment(View view) {
        Context context = getApplicationContext();
        CharSequence text = "You cannot have more than 100 cups of coffee";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);

        if (quantity < 100) {
            quantity = quantity + 1;
        } else {
            toast.show();
        }

        displayQuantity(quantity);
    }

    /**
     * This method is called when the -(minus) button is clicked
     */
    public void decrement(View view) {
        Context context = getApplicationContext();
        CharSequence text = "You cannot have less than 1 cup of coffee";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);

        if (quantity > 1) {
            quantity = quantity - 1;
        } else {
            toast.show();
        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();

        String summary = createOrderSummary(price);

        EditText nameBox = findViewById(R.id.name);
        Editable name = nameBox.getText();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    /**
     * Calculates the price of the order.
     *
     * @return number price of coffee ordered
     */
    private int calculatePrice() {
        int basePrice = 5;
        CheckBox whipped = findViewById(R.id.whippedCream);
        boolean stateWhipped = whipped.isChecked();
        CheckBox chocolate = findViewById(R.id.chocolate);
        boolean stateChocolate = chocolate.isChecked();

        if (stateWhipped) {
            basePrice = basePrice + 1;
        }
        if (stateChocolate) {
            basePrice = basePrice + 2;
        }


        return basePrice * quantity;

    }


    private String createOrderSummary(int price) {
        CheckBox whipped = findViewById(R.id.whippedCream);
        boolean state = whipped.isChecked();
        CheckBox chocolate = findViewById(R.id.chocolate);
        boolean stateChocolate = chocolate.isChecked();
        EditText nameBox = findViewById(R.id.name);
        Editable name = nameBox.getText();
        return getString(R.string.name) + " " + name + " \n" + getString(R.string.whippedCreamState) + " " + state + "\n" +
                getString(R.string.chocolateState) + " " + stateChocolate + "\n" + getString(R.string.quantity) + " " +
                quantity + "\n" + getString(R.string.total) + " " + price + "\n" + getString(R.string.thankYou);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


}