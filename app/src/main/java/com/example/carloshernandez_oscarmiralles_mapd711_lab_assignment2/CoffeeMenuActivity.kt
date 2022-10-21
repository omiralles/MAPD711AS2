package com.example.carloshernandez_oscarmiralles_mapd711_lab_assignment2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

/**
 * CoffeeMenuActivity class used to collect the order data
 * by checking that the user has selected the required
 * options
 */
class CoffeeMenuActivity: AppCompatActivity(){

    lateinit var customerOrder: CustomerOrder
    lateinit var tb1: ToggleButton
    lateinit var tb2: ToggleButton
    lateinit var tb3: ToggleButton
    lateinit var tb4: ToggleButton

    //Override onCreate
    //Retrieve the customer order and init the Toggle Buttons variables
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        customerOrder = intent.getSerializableExtra(COFFEE_ORDER) as CustomerOrder

        tb1 = findViewById(R.id.r1c1)
        tb2 = findViewById(R.id.r2c1)
        tb3 = findViewById(R.id.r1c2)
        tb4 = findViewById(R.id.r2c2)
    }

    /**
     * function coffeeSelector
     * Called when the user press any coffee option button
     */
    fun coffeeSelector(view: View) {
        if (view is ToggleButton){
            //Uncheck all the other toggle buttons
            if (view == tb1){
                tb2.isChecked = false
                tb3.isChecked = false
                tb4.isChecked = false
            }else if (view == tb2){
                tb1.isChecked = false
                tb3.isChecked = false
                tb4.isChecked = false
            }else if (view == tb3){
                tb1.isChecked = false
                tb2.isChecked = false
                tb4.isChecked = false
            }else if (view == tb4){
                tb1.isChecked = false
                tb2.isChecked = false
                tb3.isChecked = false
            }

            // Retrieve the view show new options when type of coffee is checked
            val sizeGroupTitle = findViewById<TextView>(R.id.sizetitle)
            val sizeGroupLayout = findViewById<LinearLayout>(R.id.sizeGroup)

            val on = (view as ToggleButton).isChecked
            Log.e("BUTTON",on.toString())
            if (on){
                customerOrder.coffeeType = view.text.toString()

                // Change to visible the views
                sizeGroupTitle.visibility = View.VISIBLE
                sizeGroupLayout.visibility = View.VISIBLE

            }else{
                customerOrder.coffeeType = ""
                sizeGroupTitle.visibility = View.GONE
                sizeGroupLayout.visibility = View.GONE
            }
        }
    }

    /**
     * function sizeSelectior detect when user select a size option
     * Save the data and show the next part
     */
    fun sizeSelector(view: View) {
        if (view is RadioButton){
            //Saving the data
            customerOrder.coffeeSize = view.text.toString()

            // Retrieve the view
            val extraGroup = findViewById<LinearLayout>(R.id.extraGroup)
            val continueButton = findViewById<Button>(R.id.continuebutton)
            // Change to visible the views
            extraGroup.visibility = View.VISIBLE
            continueButton.isEnabled = true
        }
    }

    /**
     * function extraSelectior save any extra option selected by the user
     */
    fun extraSelector(view: View) {
        if (view is CheckBox){
            if (view.isChecked){
                // Saving Data
                customerOrder.coffeeExtras.add(view.text.toString())
            }else{
                // Deleting data
                customerOrder.coffeeExtras.remove(view.text.toString())
            }
        }
    }

    /**
     * function continueToPayment to load payment info activity
     */
    fun continueToPayment(view: View) {
        // Declare intend and send the values to next activity
        val intent = Intent(this, PaymentActivity::class.java).apply {
            putExtra(COFFEE_ORDER, customerOrder)
        }
        startActivity(intent)
    }

}