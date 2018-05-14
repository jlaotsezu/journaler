package com.journaler.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment: Fragment(){
    protected abstract val logTag: String
    protected abstract fun getLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e(logTag, "[ON CREATE VIEW]")
        val view = inflater.inflate(getLayout(), container, false)

        return view
    }
    override fun onPause() {
        super.onPause()
        Log.e(logTag, "[ON PAUSE]")
    }
    override fun onResume() {
        super.onResume()
        Log.v(logTag, "[ ON RESUME ]")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(logTag, "[ ON DESTROY ]")
    }
}