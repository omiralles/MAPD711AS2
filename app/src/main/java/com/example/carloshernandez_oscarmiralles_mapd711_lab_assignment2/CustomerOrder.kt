package com.example.carloshernandez_oscarmiralles_mapd711_lab_assignment2

import android.util.Log

/**
 * class CustomerOrder to store data and validate them
 */
open class CustomerOrder : java.io.Serializable{

    lateinit var coffeeType: String
    lateinit var coffeeSize: String
    lateinit var coffeeExtras: MutableList<String>
    lateinit var customerName: String
    lateinit var customerNumber: String
    lateinit var orderTime: String
    lateinit var customerCard: PaymentInfo

    /**
     * Return the summary text
     * Includes the coffee size, type and every extra
     */
    fun getExtrasSummary(): String {
        var result: String = "A "+coffeeSize+" "+coffeeType+" "

        if (coffeeExtras.size == 0){
            result += "without extras"
        }else if (coffeeExtras.size == 1){
            result += "with "+coffeeExtras.get(0).toString()
        }else{
            result += "with "
            for (extra in coffeeExtras){
                if (coffeeExtras.indexOf(extra) == coffeeExtras.size-1 ){
                    result += " and "+extra
                }else{
                    result += extra + ", "
                }
            }
        }
        return result
    }

    /**
     * Valid if the customer order is complete
     * Includes validate coffee type, size, pickup time, costomer name, customer number
     * and the payment class info
     */
    fun isCustomerOrderComplete(): Boolean {
        var result: Boolean = false
        if (!validCoffeeSize())
            Log.e("ORDER SIZE",coffeeSize)
        if (!validCoffeeType())
            Log.e("ORDER TYPE",coffeeType)
        if (!validOrderTime())
            Log.e("ORDER TIME",orderTime)
        if (!validCustomerName())
            Log.e("ORDER NAME",customerName)
        if (!validCustomerNumber())
            Log.e("ORDER NUMBER",customerNumber)


        if (validCoffeeSize() && validCoffeeType() && validOrderTime()
            && validCustomerName() && validCustomerNumber() && customerCard.validPaymentInfo())
            result = true
        return result
    }

    /**
     * Validate coffee type is not empty
     */
    fun validCoffeeType(): Boolean {
        var result: Boolean = false
        if (coffeeType.isNotEmpty())
            result = true
        return  result
    }

    /**
     * Validate that coffee size is not empty
     */
    fun validCoffeeSize(): Boolean {
        var result: Boolean = false
        if (coffeeSize.isNotEmpty())
            result = true
        return  result
    }

    /**
     * Validate Customer name is not empty
     */
    fun validCustomerName(): Boolean {
        var result: Boolean = false
        if (customerName.isNotEmpty())
            result = true
        return  result
    }

    /**
     * Validate the customer phone number is not empty and has a length mora than 5
     */
    fun validCustomerNumber(): Boolean {
        var result: Boolean = false
        if (customerNumber.isNotEmpty() && customerNumber.length > 5)
            result = true
        return  result
    }

    /**
     * Validate the order time is not empty
     */
    fun validOrderTime(): Boolean {
        var result: Boolean = false
        if (orderTime.isNotEmpty() && orderTime.length > 4)
            result = true
        return  result
    }
}