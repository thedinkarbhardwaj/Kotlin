package com.example.tablayouttimerfunctionality

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var home_textview:TextView
    lateinit var mainViewModel: MainViewModel
    lateinit var image:ImageView

    lateinit var act:MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity: Activity? = activity // Mam eh acivity getactivity nu get krdi aa shyad so appa may be eda v kr sakde aa
        if (activity is MainActivity) {
          act = activity
        }
        var view = inflater.inflate(R.layout.fragment_home, container, false)

        home_textview = view.findViewById(R.id.home_textview)
        image = view.findViewById(R.id.image);

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        mainViewModel.factslivedata.observe(viewLifecycleOwner, {

            if(it.equals("finish")){

                image.visibility = View.GONE

            }else {
                home_textview.setText("change " + it.toString())
            }

        })

        view.stop_timer.setOnClickListener {
            act.clearTimer()
        }
        view.start_timer.setOnClickListener {
            act.startTimer()
        }
        return view
    }

}