package com.journaler.activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.journaler.MODE
import com.journaler.R

abstract class ItemActivity: BaseActivity() {
    protected var mode = MODE.VIEW
    protected var success  = Activity.RESULT_CANCELED
    override fun getActivityTitle(): Int {
        return R.string.app_name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.extras
        data?.let {
            val modeToSet = data.getInt(MODE.EXTRAS_KEY)
            mode = MODE.getByValue(modeToSet)
        }
        Log.e(tag, "MODE [ $mode ]")
    }

    override fun onDestroy() {
        super.onDestroy()
        setResult(success)
    }
}