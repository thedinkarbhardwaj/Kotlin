package com.example.payu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.webkit.WebView
import android.widget.Button
import com.payu.base.listeners.HashGenerationListener
import com.payu.base.models.ErrorResponse
import com.payu.base.models.PayUPaymentParams
import com.payu.checkoutpro.PayUCheckoutPro
import com.payu.checkoutpro.utils.PayUCheckoutProConstants
import com.payu.checkoutpro.utils.PayUCheckoutProConstants.CP_HASH_NAME
import com.payu.checkoutpro.utils.PayUCheckoutProConstants.CP_HASH_STRING
import com.payu.ui.model.listeners.PayUCheckoutProListener
import com.payu.ui.model.listeners.PayUHashGenerationListener

class MainActivity : AppCompatActivity() {

//    var prodKey = "YKpJOB"
//    var prodSalt = "cY6RTDxRncCfO2g4G4KqJzr0MZQLzyco"
//    var prodSalt222 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDBsXZnsoWezxzh8mIC5h3EVw8ry04lKKKVOupCB4ibVfxbuL9xq00N+Tl+rN5Mgbp2yIS01Jl2W9ab0fEwZLzJIhkO8OZaX2V0DL04K/TmafAbz9CQpS5OgrxgRbSXAImvy3H3swxA+oLDRyVE/qYx2WuvTry0kSs+6xRHoVD/xRMlnh10yxwp9rBg5olprN7pdKTYijKsQpJhQ1qcU3IVBFuIjLhdUK/ndgoN0jTnlFszM6OWk0ABgzDsucWgaLX971eSZ48yOSFc7EneyOhuKWCZdNCxytE4rhWik/ZsNQQxZcn+sUA1PG9XQiBKS0aX1VUFfRL27rNWyVKqiEmlAgMBAAECggEBALFa9qljDPjqem7lyxsObIlAklKueSKLP7vXU3MchNh/dnDiDOJfUVIPN7cnQPHbA8sKNoGB/fcjhrHtHaULSNX+0+UbbkGoAjSuCYDqtvdYX5SHyFj59xnKmgSpLv/pbSm75uJBX2IMQDng5l5zsegJRpIiTaYlGu0kwdAJBSr8R/BLycJr+77rIbCkxLM2c8FQrQmmWI0jAZpDWuhoJqxIdBMeQDeaNDBp4InTsx4rB44u10HWnHRqGfDJhbair2lYU9vv70fb8ACyPSPIO2SNv51vFsmFoAAFe3TfvxcZ17BOHI0Q8tNxdEWFn5xYQIjdsPQCVWNUS5o3mlxWja0CgYEA6zfnNHdW5wfZACq8X+6cs68h6EN2dhd3ZyStcafZPQC5gJgugi2Hohu6VSsrf5I5rtEmOtgo0zEx6FMhaC8+MDBtbHBRXuwaterHs8ZFv6uZT+pO1LuEwImO44D+nJezN0fJWDs5neKqMHM4OfiUm5QMbfYKFaIGTDrsoXDRFvsCgYEA0s5bKRLLL9XU1sx6/1zAnKMKHVSFngwWH8S4DN4PJHyhXmTkpUHvfd7I9zGHbhh5HNa+W5jntV7GW+IFlOmbbVhy5rVJbDDttN/RMdkqHjLACBq69QtOxjAdrNIShCqzz90vXfFdBecZr0ibPzXlNxSrMvIaGdfMpfZYg/jAv98CgYAxPgrRGQrAYwwy1L+K9dEobMmUbksmfdMpbKxC1vjLpJMxMKrL5ew3K8befaMlmd8pke+G8inZzLq+AGlF/l3uiLTftsPSM2uEGhqksCV58tqtuEe4kWlBRYr8Zczny3phYE3ugreVLhai/fwPZtL1nxDbFF8YGEtpOgy/33ZmcQKBgBNMsSvhfJi0ivffYKNgaoHsq1BkHJeLo7rk17TV360He/6YXhaDItnzO5LmBfAebXgmcOcR6KTCOIbQ+pHXkVNBdcOJdV9iW2Dj3NMjfqelHc+gIt2sW1nprc3Urb1L64psRZnkLxALSF6z1wLCfGm+iq3vXFRhXWsUUqZCb+MhAoGAc5I264FAJ/6tfb9xJrlijZWDzTA3hCxxtKTx9MbPLnrnfO4bGqhyf9XM83B42R93QOC9q48RpcmHfN4JOSALK/jsdhgJ/ii2f2b5Zizzt0J5/kgI0ImK8+8B9HxVUaSlIs7w0I1RTp/QbfNmwf84spY3VoUpu9AbiciRECTVHwg="
//
    var prodKey = "GEuhn3"
    var prodSalt = "f1B9Xg9oyuJdVpPB3Q32U8Myja5rp3px"
    var prodSalt222 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCcGDnXQYc424shH4OuepFPNxgeZlgPExgFKs7+LWFZMg0xWwC23NjUeLFE9MO9MIban5fwfnd6MEPHEtW64AyvWeJSxVQldx+/GsBI7HInuTz8zT+vuURxOVNr2uWRY3bp1jeKowqfHAdVnbE2PZ2USlPZztD7/fa+3PzSCQB8sEfdMAR7QJQ40bJWLf9iwvqYMRlix/IVTiUD7VRDPVE0z+KqMtx2LqK/gSNrweyLZibDxHq/TAWTI9WPZDL1ZurkQ+d5dAU8l5ynZRnFU4SMeY88/V4YeRGRwfEhGl79MCvFQCCcFBFHnicea/B+joBxoXHeU83VGCxXMP8xuBT/AgMBAAECggEAaBuISFb+Fx/mNVtNektpKwo13eCojrVPafLZR5GAwfqt7eMiINQ/EcbFhJYzDrWU269hqRbQHQCf0+BuCEtyamlql4Xgz3QaCl2PLZP1tyTG1l0WQt8wFMwFJYRDZKqQHvmu5ZpEdUY+Zg3cqa6CsOq57iUQIWGK9lVxkaxLVN061hDQotM8mZtWoiyBy14ooq0l52TQo3rotXZmnFPGX4dKH5ezakoIroumar9LQtvAzAxT9fNdyBnMNjzPz6prTCiSVr7XZ3ReMQYayxWXNnvQRizjYXeoJmDTuT4a5YM9wagiqSYRnzJu70PsoXVBbNJ7CfNRdKQThZp0UEPCYQKBgQDP25wluRR7MEsahFWTskR9W5W2NbCbrWEei2JMYMtALqfNOj764iTB1Bnyu4SeGaXJuTZzN3PjQlFrOZ3Il6Fkh+0+Lv3n9lAbQ1MDuxBvORWmDR8vXP/0a4FMb9vIN3w3abUq11taEu4+d/fQt9wqgZOk+WPtTLA7Ctx2ySDndwKBgQDAP3NiYs0XqGqSoioce6eLlLq6wVt2o9Tq6xtja/voW87jEFX59ErNG8c2XgAOXsf9sadppE/rJd7pc1KIa6abaxYv2T4rYzyRKTJPqfNq55WVbF+VvUValj195YjsJJr/R/VXPcDpHyJEf4A/jwXQKBeX/kh4Y0PaJG8WhmmwuQKBgGik5U3QidEjFQx6hyLeYsDBtPOQwCL//J8GCSzupiTf2DboFgD0jRDQGWgso618uAyApDsJNdy57IeQ8DCmtegIzpW5zrZTOS0gjdCZAsMb+BwrCwIrTejdnrC+t+VqmazEGSNCXIlScoMcnrSDe+IibhRmQbqcGzT+WNOsmlYtAoGAGklSvJQn2OFFWTFlMqnRuesnMRv5Jpq2JTzqbMi6FAZhnniAWQIHLE5tCckGlvBsxgqBipdV+iAef6suEMsvZ7bXvMkmkAYspOiOIYhqaSarNFgyJaZQkldSpyE4fJlwvBEZwckG1JlWNSl93rLup4yj1TuGqzdfcrdinSDZ2LkCgYEAqHlTqaNZmqVGUGtYALq9Gqm9eCsWLnRa/yJY+yb2t/KWNs7eF44Wpz7Oyp6H6wpYPLR2UmdR1srXNv2I57uR/DhcnswPOcyHLyKUV2sfsp123Tv4k/ztW7Fu3OwVIc3JzE6Xi0Mi3Q356ilReWrb1nBz12V44J3J82wpmhZ0wQ8="

    private val surl = "https://payu.herokuapp.com/success"
    private val furl = "https://payu.herokuapp.com/failure"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var pay = findViewById<Button>(R.id.pay)

        pay.setOnClickListener{
            paymentSetup()
        }
    }

    fun paymentSetup(){
        val additionalParamsMap: HashMap<String, Any?> = HashMap()
        additionalParamsMap[PayUCheckoutProConstants.CP_UDF1] = "udf1"
        additionalParamsMap[PayUCheckoutProConstants.CP_UDF2] = "udf2"
        additionalParamsMap[PayUCheckoutProConstants.CP_UDF3] = "udf3"
        additionalParamsMap[PayUCheckoutProConstants.CP_UDF4] = "udf4"
        additionalParamsMap[PayUCheckoutProConstants.CP_UDF5] = "udf5"
        additionalParamsMap[PayUCheckoutProConstants.SODEXO_SOURCE_ID] = "srcid123"

        val payUPaymentParams = PayUPaymentParams.Builder()
            .setAmount("1.0")
            .setIsProduction(false)
            .setKey(prodKey)
            .setProductInfo("Macbook Pro")
            .setPhone("9876543210")
            .setTransactionId(System.currentTimeMillis().toString())
            .setFirstName("John")
            .setEmail("john@yopmail.com")
            .setSurl(surl)
            .setFurl(furl)
            .build()

        PayUCheckoutPro.open(
            this, payUPaymentParams,
            object : PayUCheckoutProListener {


                override fun onPaymentSuccess(response: Any) {
                    response as HashMap<*, *>
                    val payUResponse = response[PayUCheckoutProConstants.CP_PAYU_RESPONSE]
                    val merchantResponse = response[PayUCheckoutProConstants.CP_MERCHANT_RESPONSE]
                }


                override fun onPaymentFailure(response: Any) {
                    response as HashMap<*, *>
                    val payUResponse = response[PayUCheckoutProConstants.CP_PAYU_RESPONSE]
                    val merchantResponse = response[PayUCheckoutProConstants.CP_MERCHANT_RESPONSE]
                }


                override fun onPaymentCancel(isTxnInitiated:Boolean) {
                }


                override fun onError(errorResponse: ErrorResponse) {
                    val errorMessage: String
                    if (errorResponse != null && errorResponse.errorMessage != null && errorResponse.errorMessage!!.isNotEmpty())
                        errorMessage = errorResponse.errorMessage!!

//                        errorMessage = resources.getString(R.string.some_error_occurred)
                }

                override fun setWebViewProperties(webView: WebView?, bank: Any?) {
                    //For setting webview properties, if any. Check Customized Integration section for more details on this
                }

                override fun generateHash(
                    valueMap: HashMap<String, String?>,
                    hashGenerationListener: PayUHashGenerationListener
                ) {
                    if ( valueMap.containsKey(CP_HASH_STRING)
                        && valueMap.containsKey(CP_HASH_STRING) != null
                        && valueMap.containsKey(CP_HASH_NAME)
                        && valueMap.containsKey(CP_HASH_NAME) != null) {

                        val hashData = valueMap[CP_HASH_STRING]
                        val hashName = valueMap[CP_HASH_NAME]

                        //Do not generate hash from local, it needs to be calculated from server side only. Here, hashString contains hash created from your server side.
                        val hash: String? = HashGenerationUtils.generateHashFromSDK(
                            hashData.toString(),
                            prodSalt222
                        )
                        if (!TextUtils.isEmpty(hash)) {
                            val dataMap: HashMap<String, String?> = HashMap()
                            dataMap[hashName!!] = hash!!
                            hashGenerationListener.onHashGenerated(dataMap)
                        }
                    }
                }
            })
    }

//    private fun payment() {
//        val payUPaymentParams = PayUPaymentParams.Builder()
//            .setAmount("10.0")
//            .setIsProduction(true)
//            .setKey(prodKey)
//            .setProductInfo("profuct info")
//            .setPhone("9465363629")
//            .setTransactionId("transca")
//            .setFirstName("Dinkar")
//            .setEmail("dinkar3636@gmail.com")
//            .setSurl("https://www.google.com/")
//            .setFurl("https://www.dictionary.com/browse/hack")
////            .setUserCredential("")
////            .setUserToken()
////            .setAdditionalParams(<HashMap>) //Optional, can contain any additional PG params
//        .build()
//
//
//
//
//        PayUCheckoutPro.open(
//            this, payUPaymentParams,
//            object : PayUCheckoutProListener {
//
//
//                override fun onPaymentSuccess(response: Any) {
//                    response as HashMap<*,*>
//                    val payUResponse = response[PayUCheckoutProConstants.CP_PAYU_RESPONSE]
//                    val merchantResponse = response[PayUCheckoutProConstants.CP_MERCHANT_RESPONSE]
//                }
//
//
//                override fun onPaymentFailure(response: Any) {
//                    response as HashMap<*,*>
//                    val payUResponse = response[PayUCheckoutProConstants.CP_PAYU_RESPONSE]
//                    val merchantResponse = response[PayUCheckoutProConstants.CP_MERCHANT_RESPONSE]
//                }
//
//
//                override fun onPaymentCancel(isTxnInitiated:Boolean) {
//                }
//
//                override fun generateHash(
//                    map: HashMap<String, String?>,
//                    hashGenerationListener: PayUHashGenerationListener
//                ) {
//                   //
//                }
//
//
//                override fun onError(errorResponse: ErrorResponse) {
//                    val errorMessage: String
//                    if (errorResponse != null && errorResponse.errorMessage != null && errorResponse.errorMessage!!.isNotEmpty())
//                        errorMessage = errorResponse.errorMessage!!
//                    else
//                        errorMessage = resources.getString("","erroror")
//                }
//
//                override fun setWebViewProperties(webView: WebView?, bank: Any?) {
//                    //For setting webview properties, if any. Check Customized Integration section for more details on this
//                }
//
//                override fun generateHash(
//                    valueMap: HashMap<String,String>,
//                    hashGenerationListener: PayUHashGenerationListener
//                ) {
//                    if ( valueMap.containsKey(CP_HASH_STRING)
//                        && valueMap.containsKey(CP_HASH_STRING) != null
//                        && valueMap.containsKey(CP_HASH_NAME)
//                        && valueMap.containsKey(CP_HASH_NAME) != null) {
//
//                        val hashData = valueMap[CP_HASH_STRING]
//                        val hashName = valueMap[CP_HASH_NAME]
//
//                        //Do not generate hash from local, it needs to be calculated from server side only. Here, hashString contains hash created from your server side.
//                        val hash: String? = HashGenerationUtils.generateHashFromSDK(
//                            hashData.toString(),prodSalt
//                        )
//                        if (!TextUtils.isEmpty(hash)) {
//                            val dataMap: HashMap<String,String?> = HashMap()
//                            dataMap[hashName!!] = hash!!
//                            hashGenerationListener.onHashGenerated(dataMap)
//                        }
//                    }
//                }
//            })
//
//
//
//    }
}