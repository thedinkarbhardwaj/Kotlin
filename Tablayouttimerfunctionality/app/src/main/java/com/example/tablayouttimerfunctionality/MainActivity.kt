 package com.example.tablayouttimerfunctionality

import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import java.util.Timer
import java.util.TimerTask


 class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    var frameLayout: FrameLayout? = null
    lateinit var fragment: Fragment
    var fragmentManager: FragmentManager? = null
    var fragmentTransaction: FragmentTransaction? = null
     lateinit var mainViewModel: MainViewModel


     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
         mainViewModel.factslivedata.observe(this, {

             Log.d("fact",it.toString())
         })

         //Sir timer code needed in fragment // ok mam // after implement code i will msg you


//         Timer().schedule(object : TimerTask() {
//             override fun run() {
//                 TODO("Do something")
//             }
//         }, 2000)

        tabLayout=findViewById(R.id.tabLayout);
        frameLayout=findViewById(R.id.frameLayout);

        fragment = HomeFragment();
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager?.beginTransaction()

        fragmentTransaction?.replace(R.id.frameLayout,fragment)
        fragmentTransaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction?.commit()

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                when(tab.position){

                    0 -> fragment = HomeFragment()
                    1 -> fragment = JavaFragment()
                    2 -> fragment = KotlinFragment()

                }

                fragmentManager = supportFragmentManager
                fragmentTransaction = fragmentManager?.beginTransaction()

                fragmentTransaction?.replace(R.id.frameLayout,fragment)
                fragmentTransaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                fragmentTransaction?.commit()

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                //FadeTab(tab.position)
               // Toast.makeText(this@MainActivity,"unselected "+tab.position,Toast.LENGTH_LONG).show()
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        }
     var timer: CountDownTimer? = null
     fun clearTimer(){
         if(timer != null)
           timer?.cancel()
     }

     fun startTimer(){
         timer = object: CountDownTimer(20000,1000){
             override fun onTick(millitime: Long) {
                 var time = millitime/1000

//                Toast.makeText(this@MainActivity,""+time.toString(),Toast.LENGTH_SHORT).show()
                 mainViewModel.update_value(time.toString());

             }

             override fun onFinish() {

                 mainViewModel.update_value("finish");
             }
         }
         timer?.start();
     }
}