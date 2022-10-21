package com.example.carloshernandez_oscarmiralles_mapd711_lab_assignment2

import android.util.Log
import java.time.Year

open class PaymentInfo : java.io.Serializable {

    lateinit var cardType: CardType
    lateinit var cardNumber: String
    lateinit var cardExpireMonth: String
    lateinit var cardExpireYear: String
    lateinit var cardCVV: String

    // Valid all the payment info
    // Uses all other validation functions
    fun validPaymentInfo(): Boolean {
        var result: Boolean = false

        if (!validCardType())
            Log.e("PAYMENT TYPE",cardType.toString())
        if (!validCardCVV())
            Log.e("PAYMENT CVV",cardCVV)
        if (validExpireMonth() != 1)
            Log.e("PAYMENT MM",cardExpireMonth+":"+validExpireMonth())
        if (validExpireYear() != 1)
            Log.e("PAYMENT YY",cardExpireYear+":"+validExpireYear())
        if (!validCardNumber())
            Log.e("PAYMENT CARD", cardNumber)

        if (validCardType() && validCardCVV() && validCardNumber()
            && validExpireMonth() == 1 && validExpireYear() == 1)
            result = true
        return result
    }

    // Valid that credit card number is 16 digits and it' a number
    fun validCardNumber(): Boolean {
        var result: Boolean = false
        try {
            if (cardNumber.length == 16){
                result = true
            }
        }catch (ex: java.lang.NumberFormatException) {
            result = false
        }
        return result
    }

    // Valid that credit card is VISA or MASTERCARD
    fun validCardType(): Boolean {
        var result: Boolean = false
        if (cardType == CardType.VISA || cardType == CardType.MASTERCARD)
            result = true
        return result
    }

    // Valid is the CVV length is 3 (number validation is by keyboard)
    fun validCardCVV(): Boolean {
        var result: Boolean = false
        if (cardCVV.length == 3)
            result = true
        return result
    }

    // Valid if the expire month is empty, is valid (from 01 -> 12)
    // and it's length is 2 digits
    fun validExpireMonth(): Int {
        var result: Int = 1
        try{
            if (cardExpireMonth.isEmpty())
                result = 0
            if (cardExpireMonth.toInt() > 12 || cardExpireMonth.toInt() < 1)
                result = -1
            if (cardExpireMonth.length != 2)
                result = -2
        }catch (ex: java.lang.NumberFormatException){
            result = -4
        }

        return result
    }

    // Valid if the expire year is empty, is expired (is previous year than actual)
    // or the length is different than 4 digits
    fun validExpireYear(): Int {
        var result: Int = 1
        try{
            if (cardExpireYear.isEmpty())
                result = 0
            if (cardExpireYear.toInt() < Year.now().value )
                result = -1
            if (cardExpireYear.length != 4)
                result = -2
        }catch (ex: java.lang.NumberFormatException){
            result = -4
        }
        return result
    }

}

// ENUM Class for the type of credit cards
enum class CardType {
    VISA,
    MASTERCARD,
}