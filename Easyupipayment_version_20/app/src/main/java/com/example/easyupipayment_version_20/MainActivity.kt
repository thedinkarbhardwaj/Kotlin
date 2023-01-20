package com.example.easyupipayment_version_20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.shreyaspatil.EasyUpiPayment.EasyUpiPayment
import com.shreyaspatil.EasyUpiPayment.listener.PaymentStatusListener
import com.shreyaspatil.EasyUpiPayment.model.PaymentApp
import com.shreyaspatil.EasyUpiPayment.model.TransactionDetails

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
            pay()
        }
        catch (e:Exception){
            Toast.makeText(this@MainActivity,"Error " + e.message.toString(), Toast.LENGTH_SHORT).show()
        }

    }

    fun pay(){
        val easyUpiPayment  = EasyUpiPayment.Builder()
            .with(this@MainActivity) // on below line we are adding upi id.
            .setPayeeVpa(upi_id) // on below line we are setting name to which we are making payment.
            .setPayeeName(receiver_name) // on below line we are passing transaction id.
            .setTransactionId("3267") // on below line we are passing transaction ref id.
            .setTransactionRefId("3267") // on below line we are adding description to payment.
            .setDescription(desc) // on below line we are passing amount which is being paid.
            .setAmount(amountvalue) // on below line we are calling a build method to build this ui.
            .build()

        //easyUpiPayment.setDefaultPaymentApp(PaymentApp.GOOGLE_PAY) // you can send also set default payment app

        // on below line we are calling a start
        // payment method to start a payment.
        // on below line we are calling a start
        // payment method to start a payment.
        easyUpiPayment.startPayment()

        // on below line we are calling a set payment
        // status listener method to call other payment methods.
        // on below line we are calling a set payment
        // status listener method to call other payment methods.
        easyUpiPayment.setPaymentStatusListener(this)
    }

    override fun onTransactionCompleted(transactionDetails: TransactionDetails?) {
        Toast.makeText(this@MainActivity,"onTransactionCompleted " + transactionDetails?.status.toString(),Toast.LENGTH_LONG).show()
    }

    override fun onTransactionSuccess() {
        Toast.makeText(this@MainActivity,"onTransactionSuccess ",Toast.LENGTH_LONG).show()
    }

    override fun onTransactionSubmitted() {
        Toast.makeText(this@MainActivity,"onTransactionSubmitted ",Toast.LENGTH_LONG).show()
    }

    override fun onTransactionFailed() {
        Toast.makeText(this@MainActivity,"onTransactionFailed ",Toast.LENGTH_LONG).show()
    }

    override fun onTransactionCancelled() {
        Toast.makeText(this@MainActivity,"onTransactionCancelled ",Toast.LENGTH_LONG).show()
    }

    override fun onAppNotFound() {
        Toast.makeText(this@MainActivity,"onAppNotFound ",Toast.LENGTH_LONG).show()
    }
}