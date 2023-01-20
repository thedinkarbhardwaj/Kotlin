package com.example.googlepayapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.shreyaspatil.easyupipayment.EasyUpiPayment
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener
import dev.shreyaspatil.easyupipayment.model.PaymentApp
import dev.shreyaspatil.easyupipayment.model.TransactionDetails


class MainActivity : AppCompatActivity(){


    var upiId = "" // upi id
    var name="Name of reciever" // Dinkar Kumar
    var transNote= "Just testing"
    var amount= "1.00"
    var gp_package_name="com.google.android.apps.nbu.paisa.user"
    var GooglePay_REQ_CODE=1234
    lateinit var uri:Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun getUPiPaymentUri(){
        uri = Uri.Builder().scheme("upi").authority("pay")
            .appendQueryParameter("pa",upiId)
            .appendQueryParameter("pn", upiId)
            .appendQueryParameter("tn", transNote)
            .appendQueryParameter("am", amount)
            .appendQueryParameter("cu", "INR")
            //.appendQueryParameter("mc", "") // mc means merchant code only 4 digit
            .build()
    }


    fun isAppInstalled():Boolean{
        try {
            packageManager.getApplicationInfo(gp_package_name, 0)

            return true
        }
        catch(e:Exception)
        {
            return  false
        }
    }

    fun googlePay() {
        getUPiPaymentUri()
        if(isAppInstalled())
        {
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(uri)
            i.setPackage(gp_package_name)
            startActivityForResult(i, GooglePay_REQ_CODE)
        }
        else
        {
            Toast.makeText(this@MainActivity, "Plz install google pay", Toast.LENGTH_SHORT).show()
        }
    }

    fun gpay(view: View) {

        googlePay()

    }


}
