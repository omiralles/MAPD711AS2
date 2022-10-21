package com.example.carloshernandez_oscarmiralles_mapd711_lab_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

/**
* Online Coffee Ordering App
* @Authors
* Student Name: Carlos Hernandez Galvan
* Student ID: 301290263
* Student Name: Oscar Miralles Fernandez
* Student ID: 301250756
**/

const val COFFEE_ORDER = "centennialcollege.razielhernandez.customerordername"

/**
 * MainActivity application class
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Function startOrder
     * Called when the user press the "Start Ordering" button
     * The function initialize variables and create and Intent
     * to go to next activity, CoffeemenuActuvity
     */
    fun startOrder(view: View) {
        //Create a new CustomerOrder object, it contains all the order info
        //It will be send through all the shop process
        val newOrder = object :  CustomerOrder() {
            init {
                coffeeType = ""
                coffeeSize = ""
                customerName = ""
                customerNumber = ""
                orderTime = "ERROR"
                coffeeExtras = mutableListOf<String>()
                customerCard = object : PaymentInfo() {
                    init {
                        cardNumber = ""
                        cardExpireMonth = ""
                        cardExpireYear = ""
                        cardType = CardType.VISA
                        cardCVV = ""
                    }
                }
            }
        }
        //Declare intend and send the values to next activity CoffeeMenuActivity
        val intent = Intent(this, CoffeeMenuActivity::class.java).apply {
            putExtra(COFFEE_ORDER, newOrder)
        }
        startActivity(intent)
    }
}