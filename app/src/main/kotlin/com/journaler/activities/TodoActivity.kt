package com.journaler.activities

import android.app.Activity
import android.os.Bundle
import com.journaler.R
import kotlinx.android.synthetic.main.activity_todo.*

class TodoActivity : ItemActivity() {
    override val tag: String
        get() = "TodoActivity"
    companion object {
        val EXTRA_DATE = "EXTRA_DATE"
        val EXTRA_TIME = "EXTRA_TIME"
    }
    override fun getLayout(): Int {
        return R.layout.activity_todo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.extras
        data?.let{
            val date = data.getString(EXTRA_DATE, "")
            val time = data.getString(EXTRA_TIME, "")
            pick_date.text = date
            pick_time.text = time
        }
    }

}
