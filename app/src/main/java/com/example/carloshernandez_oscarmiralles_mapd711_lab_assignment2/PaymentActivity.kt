package com.example.carloshernandez_oscarmiralles_mapd711_lab_assignment2

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

/**
 * PaymentActivity class to collect and check all the information
 * necessary for a pay the order
 */
class PaymentActivity: AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var customerOrder: CustomerOrder

    // Override onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        // Retrieve customer order data from previous activity
        customerOrder = intent.getSerializableExtra(COFFEE_ORDER) as CustomerOrder

        // Set the spinner values
        val cardTypeSpinner : Spinner = findViewById(R.id.paymentspinner)
        ArrayAdapter.createFromResource(
            this, R.array.en_creditcards, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cardTypeSpinner.adapter = adapter
        }
        cardTypeSpinner.onItemSelectedListener = this

        // Get all the views to init
        val customerNameInput = findViewById<EditText>(R.id.pickupinputname)
        val customerNumberInput = findViewById<EditText>(R.id.pickupinputnumber)
        val cardNumber = findViewById<EditText>(R.id.cardnumberinput)
        val cardMonth = findViewById<EditText>(R.id.cardexpirymonth)
        val cardYear = findViewById<EditText>(R.id.cardexpiryyear)
        val cardVVC = findViewById<EditText>(R.id.paymentcvv)

        // Listener for CVV text input
        cardVVC.addTextChangedListener(object: TextWatcher {
            // After text valid the info to show error info
            override fun afterTextChanged(p0: Editable?) {
                if (customerOrder.customerCard.validCardCVV()){
                    cardVVC.error = null
                }else{
                    cardVVC.error = "Input valid 3 digits CVV"
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            // On text change check if customer order is complete to enable the button
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null){
                    customerOrder.customerCard.cardCVV = p0.toString()
                    if (customerOrder.isCustomerOrderComplete()){
                        val completeButton = findViewById<Button>(R.id.buttonplaceorder)
                        completeButton.isEnabled = true
                    }
                }
            }
        })

        cardYear.addTextChangedListener(object: TextWatcher {
            // After text valid the info to show error info
            override fun afterTextChanged(p0: Editable?) {
                if (customerOrder.customerCard.validExpireYear() == 1){
                    cardYear.error = null
                }else if (customerOrder.customerCard.validExpireYear() == 0){
                    cardYear.error = "A expire date is required"
                }else if (customerOrder.customerCard.validExpireYear() == -1){
                    cardYear.error = "The card is expired"
                }else if (customerOrder.customerCard.validExpireYear() == -2){
                    cardYear.error = "Input a valid 4 digit year"
                }else{
                    cardYear.error = "Input a valid year"
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // On text change check if customer order is complete to enable the button
                if (p0 != null){
                    customerOrder.customerCard.cardExpireYear = p0.toString()
                    if (customerOrder.isCustomerOrderComplete()){
                        val completeButton = findViewById<Button>(R.id.buttonplaceorder)
                        completeButton.isEnabled = true
                    }
                }
            }
        })

        cardMonth.addTextChangedListener(object: TextWatcher {
            // After text valid the info to show error info
            override fun afterTextChanged(p0: Editable?) {
                if (customerOrder.customerCard.validExpireMonth() == 1){
                    cardMonth.error = null
                }else if (customerOrder.customerCard.validExpireMonth() == 0){
                    cardMonth.error = "A expire date is required"
                }else if (customerOrder.customerCard.validExpireMonth() == -1){
                    cardMonth.error = "Input a valid month"
                }else if (customerOrder.customerCard.validExpireMonth() == -2){
                    cardMonth.error = "Input 2 digit month"
                }else{
                    cardMonth.error = "Input a valid expire month"
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // On text change check if customer order is complete to enable the button
                if (p0 != null){
                    customerOrder.customerCard.cardExpireMonth = p0.toString()
                    if (customerOrder.isCustomerOrderComplete()){
                        val completeButton = findViewById<Button>(R.id.buttonplaceorder)
                        completeButton.isEnabled = true
                    }
                }
            }
        })

        cardNumber.addTextChangedListener(object: TextWatcher {
            // After text valid the info to show error info
            override fun afterTextChanged(p0: Editable?) {
                if (customerOrder.customerCard.validCardNumber()){
                    cardNumber.error = null
                }else{
                    cardNumber.error = "Input valid 16 digits card number"
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // On text change check if customer order is complete to enable the button
                if (p0 != null){
                    customerOrder.customerCard.cardNumber = p0.toString()
                    if (customerOrder.isCustomerOrderComplete()){
                        val completeButton = findViewById<Button>(R.id.buttonplaceorder)
                        completeButton.isEnabled = true
                    }
                }
            }
        })

        customerNameInput.addTextChangedListener(object: TextWatcher {
            // After text valid the info to show error info
            override fun afterTextChanged(p0: Editable?) {
                if (customerOrder.validCustomerName()){
                    customerNameInput.error = null
                }else{
                    customerNameInput.error = "Input valid name"
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // On text change check if customer order is complete to enable the button
                if (p0 != null) {
                    customerOrder.customerName = p0.toString()
                    if (customerOrder.validCustomerName() && customerOrder.validCustomerNumber()){
                        val payInfo1 = findViewById<LinearLayout>(R.id.paymentinfo1)
                        payInfo1.visibility = View.VISIBLE
                    }
                    if (customerOrder.isCustomerOrderComplete()){
                        val completeButton = findViewById<Button>(R.id.buttonplaceorder)
                        completeButton.isEnabled = true
                    }
                }
            }
        })

        customerNumberInput.addTextChangedListener(object: TextWatcher {
            // After text valid the info to show error info
            override fun afterTextChanged(p0: Editable?) {
                if (customerOrder.validCustomerNumber()){
                    customerNumberInput.error = null
                }else{
                    customerNumberInput.error = "Input valid telephone number"
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // On text change check if customer order is complete to enable the button
                if (p0 != null) {
                    customerOrder.customerNumber = p0.toString()
                    if (customerOrder.validCustomerName() && customerOrder.validCustomerNumber()){
                        val payInfo1 = findViewById<LinearLayout>(R.id.paymentinfo1)
                        payInfo1.visibility = View.VISIBLE
                    }
                    if (customerOrder.isCustomerOrderComplete()){
                        val completeButton = findViewById<Button>(R.id.buttonplaceorder)
                        completeButton.isEnabled = true
                    }
                }
            }
        })


    }

    /**
     * function continueToPayment to load payment
     * info activity
     */
    fun placeOrder(view: View) {
        // Declare intend and send the values to next activity
        val orderTime = findViewById<TimePicker>(R.id.orderPickupTime)
        customerOrder.orderTime = buildPickupTime(orderTime.hour, orderTime.minute, orderTime.is24HourView)
        val intent = Intent(this, OrderSummaryActivity::class.java).apply {
            putExtra(COFFEE_ORDER, customerOrder)
        }
        startActivity(intent)
    }

    /**
     * function to build the pickup time to save it in the place order
     */
    private fun buildPickupTime(hour: Int, minutes: Int, pm: Boolean): String{
        var result: String = ""
        if (hour < 10) {
            result += "0" + hour + ":"
        }else{
            result += "" + hour + ":"
        }
        if (minutes < 10) {
            result += "0" + minutes
        }else{
            result += "" + minutes
        }
        return result
    }

    /**
     * Override onItemSelected for credit card select spinner
     * Performing action onItemSelected and onNothing selected
     */
    override fun onItemSelected(arg0: AdapterView<*>?, arg1: View?, position: Int, id: Long) {
        Log.d("STATUS"," SPINNER SELECTED")
        Log.d("CUSTOMER ORDER", "Name: "+customerOrder.customerName)
        Log.d("CUSTOMER ORDER", "Phone number: "+customerOrder.customerNumber)
        Log.d("CUSTOMER ORDER", "Order: "+customerOrder.coffeeType)
        Log.d("CUSTOMER ORDER", "Time: "+customerOrder.orderTime)
        Log.d("CUSTOMER ORDER", "Size: "+customerOrder.coffeeSize)
        Log.d("CUSTOMER ORDER", "Extras: "+customerOrder.getExtrasSummary())

        val payInfo2 = findViewById<LinearLayout>(R.id.paymentinfo2)
        if (position != 0){
            payInfo2.visibility = View.VISIBLE
        }else{
            payInfo2.visibility = View.GONE
        }
    }

    /**
     * Override onNothingSelected for credit card select spinner
     */
    override fun onNothingSelected(arg0: AdapterView<*>?) {
        Toast.makeText(applicationContext, "Nothing selected", Toast.LENGTH_LONG).show()
    }


}