package com.example.tablayouttimerfunctionality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

var value = ""
class MainViewModel: ViewModel() {
    var factslivedata = MutableLiveData<String>("hi");

    fun update_value(toString: String) {
        factslivedata.value = toString
        value = toString;
    }

    fun gettext(): LiveData<String> {
        return factslivedata;
    }

    fun get_value():String{
        return value;
    }


}