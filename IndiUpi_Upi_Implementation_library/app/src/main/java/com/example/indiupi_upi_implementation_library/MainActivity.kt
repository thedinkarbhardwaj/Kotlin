package com.example.indiupi_upi_implementation_library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.gpfreetech.IndiUpi.IndiUpi
import com.gpfreetech.IndiUpi.entity.TransactionResponse
import com.gpfreetech.IndiUpi.listener.PaymentStatusListener

class MainActivity : AppCompatActivity(),PaymentStatusListener {

    var upi_id = " "  // paytmqr2011duits4eu6bg@paytm Must Enter upi id otherwise it will crash
    var receiver_name = "Name of user"
    var amountvalue = "1.0"
    var desc = "Just for testing"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun indiupi_payment_fun(){

        var indiUpi = IndiUpi.Builder()
            .with(this)
            .setPayeeVpa(upi_id)
            .setAmount(amountvalue)
            .setPayeeName(receiver_name)
            .setDescription(desc)
            .setTransactionId("2333")
            .setTransactionRefId("2333")
//            .setUrl("HTTP_OR_HTTPS", "WWW.EXAMPLE.COM", "API.php")
            //internal parameter automatically add in URL same as above UPI request
            .build();


        indiUpi.pay("Payment Using"); // here your choice dialog title

        indiUpi.setPaymentStatusListener(this)

    }

    fun indiupi(view: View) {
        indiupi_payment_fun()
    }

    override fun onTransactionCompleted(transactionDetails: TransactionResponse?) {
        Toast.makeText(this@MainActivity,"onTransactionCompleted " + transactionDetails?.status.toString(),
            Toast.LENGTH_SHORT).show()
    }

    override fun onTransactionSuccess(transactionDetails: TransactionResponse?) {
        Toast.makeText(this@MainActivity,"onTransactionSuccess " + transactionDetails?.status.toString(),
            Toast.LENGTH_SHORT).show()
    }

    override fun onTransactionSubmitted() {
        Toast.makeText(this@MainActivity,"onTransactionSubmitted",
            Toast.LENGTH_SHORT).show()
    }

    override fun onTransactionFailed() {
        Toast.makeText(this@MainActivity,"onTransactionFailed",
            Toast.LENGTH_SHORT).show()
    }

    override fun onTransactionCancelled() {
        Toast.makeText(this@MainActivity,"onTransactionCancelled",
            Toast.LENGTH_SHORT).show()
    }
}