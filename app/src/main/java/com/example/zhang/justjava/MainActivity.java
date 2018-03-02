package com.example.zhang.justjava;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;



/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int calculatePrice() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        int quantity = Integer.parseInt(quantityTextView.getText().toString());
        return quantity*5;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String[] maillist = {"1234@abc.com"};
        int price = calculatePrice();
        //TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        //int quantity = Integer.parseInt(quantityTextView.getText().toString());

        //displayQuantity(quantity);
        //displayPrice(price);
        CheckBox chkbox = (CheckBox) findViewById(R.id.wripped_cream_checkbox);
        Log.i("MainActivity", String.valueOf(chkbox.isChecked()));

        CheckBox cchkbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        Log.i("MainActivity", String.valueOf(cchkbox.isChecked()));

        EditText nameET = (EditText) findViewById(R.id.name_edit_text);
        Log.i("MainActivity", nameET.getText().toString());

        displayMessage(createOrderSummary(price, chkbox.isChecked(), cchkbox.isChecked(), nameET.getText().toString()));

        //composeEmail(maillist, "coffee order", createOrderSummary(price, chkbox.isChecked(), cchkbox.isChecked(), nameET.getText().toString()));
    }

    public void increment(View view) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        int quantity = Integer.parseInt(quantityTextView.getText().toString());
        quantity++;
        displayQuantity(quantity);
        //displayPrice(quantity*5);
    }

    public void decrement(View view) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        int quantity = Integer.parseInt(quantityTextView.getText().toString());
        quantity--;
        displayQuantity(quantity);
        //displayPrice(quantity*5);
    }

    private String createOrderSummary(int price, boolean wCream_checked, boolean chocolate_checked, String name) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        int quantity = Integer.parseInt(quantityTextView.getText().toString());

        String sum = getString(R.string.order_summary_name)+name+
                "\nAdd Wripped Cream? "+wCream_checked+
                "\nAdd Chocolate? "+chocolate_checked+
                "\nQuantity: "+quantity+"\nTotal: $"+price+"\nThank you!";
        return sum;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        String priceMessage="Total: $"+number+"\nThank you!";
        displayMessage(priceMessage);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    public void composeEmail(String[] addresses, String subject, String emailbody) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailbody);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
