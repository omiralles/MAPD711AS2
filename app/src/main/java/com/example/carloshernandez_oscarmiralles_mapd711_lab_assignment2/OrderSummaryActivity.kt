package com.example.carloshernandez_oscarmiralles_mapd711_lab_assignment2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Class Summary show the order info summary to the user
 */
class OrderSummaryActivity: AppCompatActivity() {

    lateinit var customerOrder: CustomerOrder

    // Override OnCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        // Retrieve customer order data from previous activity
        customerOrder = intent.getSerializableExtra(COFFEE_ORDER) as CustomerOrder

        // Get all the edit texts and set the text values from the customer order
        val etFullName = findViewById<TextView>(R.id.summaryFullName)
        val etNumber = findViewById<TextView>(R.id.summaryPhoneNumber)
        val etTime = findViewById<TextView>(R.id.summaryPickupTime)
        val etDetail = findViewById<TextView>(R.id.summaryOrderDetail)
        etFullName.text = customerOrder.customerName
        etNumber.text = customerOrder.customerNumber
        etTime.text = customerOrder.orderTime
        etDetail.text = customerOrder.getExtrasSummary()

    }
}