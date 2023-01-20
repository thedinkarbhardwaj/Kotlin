package com.example.upi_implement_using_easyupipayment

import android.os.Bundle
import android.text.util.Linkify.ALL
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import dev.shreyaspatil.easyupipayment.EasyUpiPayment
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener
import dev.shreyaspatil.easyupipayment.model.PaymentApp
import dev.shreyaspatil.easyupipayment.model.TransactionDetails
import java.util.logging.Level.ALL


class MainActivity : AppCompatActivity(), PaymentStatusListener {

    var upi_id = "" // upi id
    var receiver_name = "Name of receiver"
    var amountvalue = "1.0"
    var desc = "Just for testing"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun pay_with_any_upi(view: View) {

        try {
            PAY_Function()
        }
        catch (e:Exception){
            Toast.makeText(this@MainActivity,"Error " + e.message.toString(),Toast.LENGTH_SHORT).show()
        }

    }

    fun PAY_Function(){
        val easyUpiPayment: EasyUpiPayment = EasyUpiPayment(this@MainActivity){
            this.paymentApp = PaymentApp.ALL // you can also speify any app
            this.payeeVpa = upi_id
            this.payeeName = receiver_name
            this.amount = amountvalue
            this.transactionId = "234453" // enter randomly any number
            this.transactionRefId = "234453"
            this.payeeMerchantCode = "9311" // merchant code only 4 digit and it is different according to business
            this.description = desc
            this.build()
        }

        easyUpiPayment.setPaymentStatusListener(this) // Need to implement interface check above and override function which implement below (onTransactionCancelled,onTransactionCompleted)
        easyUpiPayment.startPayment()
    }

    override fun onTransactionCancelled() {
        Toast.makeText(this@MainActivity,"Transaction Cancelled ",Toast.LENGTH_SHORT).show()
    }

    override fun onTransactionCompleted(transactionDetails: TransactionDetails) {
        Toast.makeText(this@MainActivity,"Success " + transactionDetails.transactionStatus.toString(),Toast.LENGTH_SHORT).show()
    }



}