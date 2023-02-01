package com.example.createimaefromview

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream


class MainActivity : AppCompatActivity() {

    lateinit var linear_layout:LinearLayout
    lateinit var takess_btn: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        takess_btn = findViewById(R.id.takess_btn)

        linear_layout = findViewById(R.id.linear_layout)

    }

    @SuppressLint("ResourceAsColor")
    private fun getbitmapfromView(view: View): Bitmap {

        var bitmap = Bitmap.createBitmap(view.width,view.height,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bitmap)

        var bg: Drawable = view.background

        if(bg != null) {
            bg.draw(canvas)
        }
        else{
            canvas.drawColor(android.R.color.white)
        }
        view.draw(canvas)

        return bitmap
    }

    fun Imageuri(): Uri? {

        var bitmapreturn =  getbitmapfromView(linear_layout)

        var img = File(applicationContext.filesDir,"create_image_name.png")
        var fout = FileOutputStream(img)

        bitmapreturn.compress(Bitmap.CompressFormat.PNG,100,fout)
        fout.flush()
        fout.close()

        img.setReadable(true,false)

        return FileProvider.getUriForFile(applicationContext,
            "com.example.createimaefromview.filesProvider",img)

    }

    fun Share(imageuri: Uri?) {

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_STREAM, imageuri)

        intent.putExtra(Intent.EXTRA_TEXT, "Work Properly")
        startActivity(Intent.createChooser(intent, "Share with"))
    }

    fun takescreenshot(view: View) {

        takess_btn.visibility = View.GONE

        var imageuri =Imageuri()

        Share(imageuri)

    }
}