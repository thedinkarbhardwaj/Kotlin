package com.example.tablayouttimerfunctionality

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var home_textview:TextView
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_home, container, false)

        home_textview = view.findViewById(R.id.home_textview)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        mainViewModel.factslivedata.observe(viewLifecycleOwner, {

            home_textview.setText("change " + it.toString())

        })


        return view
    }

}