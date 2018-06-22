
/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

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
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the -(minus) button is clicked
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();

        String summary = createOrderSummary(price);
        displayMessage(summary);

    }

    /**
     * Calculates the price of the order.
     *
     * @return number price of coffee ordered
     */
    private int calculatePrice() {
        return quantity * 5;
    }


    private String createOrderSummary(int price) {
        CheckBox whipped = findViewById(R.id.whippedCream);
        boolean state = whipped.isChecked();
        CheckBox chocolate = findViewById(R.id.chocolate);
        boolean stateChocolate = chocolate.isChecked();
        String name = "Nicholas Otieno";
        String priceMessage = "Name: " + "\n" + "Add Whipped Cream? " + state + "\n" +
                "Add Chocolate? " + stateChocolate + "\n" + name + "\n" + "Quantity: " +
                quantity + "\n" + "Total: $ " + price + "\n" + "Thank You!";
        return priceMessage;

    }

    /**
     * @return totalPrice
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}