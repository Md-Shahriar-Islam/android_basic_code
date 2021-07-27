/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int quantity=0;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String message=createOrderSummary(quantity);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "THIS IS YOUR ORDER SUMMARY");
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public String myName()
    {
        EditText simpleEditText = (EditText) findViewById(R.id.simpleEditText);
        String editTextValue = simpleEditText.getText().toString();
        return editTextValue;
    }
    public boolean check_box()
    {
        CheckBox whippedCream=(CheckBox)findViewById(R.id.whipped_cream_added);
        boolean selection=whippedCream.isChecked();
        return selection;
    }
    public boolean check_box_two()
    {
        CheckBox choclate=(CheckBox)findViewById(R.id.choclate_added);
        boolean selection=choclate.isChecked();
        return selection;
    }
    public String createOrderSummary(int quantity)
    {
       int money= calculatePrice();
       boolean whipped_cream=check_box();
       boolean choclate = check_box_two();
       String name = myName();
       String finalOut=getString(R.string.Name,name)+"\n"+getString(R.string.Quantity,quantity)
               + "\n"+getString(R.string.whippedcream,whipped_cream)+
               "\n"+getString(R.string.Choclate,choclate)+
               "\n"+getString(R.string.Price,money)
               +"\n"+getString(R.string.thankyou);
       return finalOut;

    }
    public void increment(View view)
    {
        quantity=quantity+1;
        display(quantity);
        if(quantity==100)
        {
            return;
        }
    }
    public void decrement(View view)
    {
        display(quantity);
        if(quantity==0)
        {
            return;
        }
        quantity=quantity-1;
    }
    private int calculatePrice()
    {
        int price=0;
        if(check_box() && check_box_two())
        {
            price=(10+5+5)*quantity;
        }
        else if(check_box())
        {
            price=(10+5)*quantity;
        }
        else if (check_box_two())
        {
            price=(5+5)*quantity;
        }
        else
        {
            price=5*quantity;
        }
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}