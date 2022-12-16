package com.example.websocket

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okio.ByteString
import okio.ByteString.Companion.decodeHex

class MainActivity : AppCompatActivity() {
    private var start: Button? = null
    private var output: TextView? = null
    private var client: OkHttpClient? = null
    private var entermsg: EditText? = null

    var msgsend = "";

    companion object {
        private const val NORMAL_CLOSURE_STATUS = 1000
    }

    private inner class EchoWebSocketListener : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {

            webSocket.send(msgsend)

            webSocket.send("Hello, it's Dinkar !")
//            webSocket.send("What's up ?")
//            webSocket.send("deadbeef".decodeHex())
            //webSocket.close(Companion.NORMAL_CLOSURE_STATUS, "Goodbye !")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            output("Receiving : $text")
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            output("Receiving bytes : " + bytes.hex())
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
           // webSocket.close(Companion.NORMAL_CLOSURE_STATUS, null)
            output("Closing : $code / $reason")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            output("Error : " + t.message)
        }

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start = findViewById<View>(R.id.start) as Button
        output = findViewById<View>(R.id.output) as TextView
        entermsg = findViewById(R.id.entermsg) as EditText
        client = OkHttpClient()

        // click on listner
        start!!.setOnClickListener {

       //     msgsend = entermsg?.text.toString();
            start()

          }

    }

    private fun start() {
       //  3 ways to do this

        val request: Request = Request.Builder().url("https://demo.piesocket.com/v3/channel_123?api_key=VCXCEuvhGcBDP7XhiJJUDvR1e1D3eiVjgZ9VRiaV&notify_self,wsPort:443").build()
        //val request: Request = Request.Builder().url("https://demo.piesocket.com/v3/channel_123?api_key=VCXCEuvhGcBDP7XhiJJUDvR1e1D3eiVjgZ9VRiaV&notify_self,wsPort:6001").build()
        //val request: Request = Request.Builder().url("wss://demo.piesocket.com/v3/channel_123?api_key=VCXCEuvhGcBDP7XhiJJUDvR1e1D3eiVjgZ9VRiaV&notify_self").build()

        val listener: EchoWebSocketListener = EchoWebSocketListener()
        val ws = client!!.newWebSocket(request, listener)
        //client!!.dispatcher.executorService.shutdown()
    }

    private fun output(txt: String) {
        runOnUiThread {
            output!!.text = """
     ${output!!.text}
     
     $txt
     """.trimIndent()
        }
    }
}