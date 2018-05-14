package com.journaler.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import com.journaler.MODE
import com.journaler.R
import com.journaler.activities.NoteActivity
import com.journaler.activities.TodoActivity
import java.text.SimpleDateFormat
import java.util.*

class ItemsFragment: BaseFragment(){
    override val logTag: String
        get() = "Items Fragment"

    override fun getLayout(): Int {
        return R.layout.fragment_items
    }

    companion object {
        const val NOTE_REQUEST = 20000
        const val TODO_REQUEST = 20001
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayout(), container, false)
        val button = view.findViewById(R.id.new_item) as FloatingActionButton
        button.setOnClickListener {
            animate(button)
            val items = arrayOf(
                    getString(R.string.todos),
                    getString(R.string.notes)
            )
            context?.let {
                val builder = AlertDialog.Builder(it)
                        .setTitle(R.string.choose_a_type)
                        .setItems(items, {_, which ->
                            when(which){
                                0 -> {
                                    openCreateToDo()
                                }
                                1 -> {
                                    openCreateNote()
                                }
                                else -> Log.e(logTag, "Unknown option + [ $which ]")
                            }
                        })
                        .setOnCancelListener { animate(button, false) }
                builder.show()
            }

        }

        return view
    }
    private fun animate(button: FloatingActionButton, expand: Boolean = true){
        button.animate()
                .setInterpolator(BounceInterpolator())
                .scaleX(if(expand){1.5f} else{1.0f})
                .scaleY(if(expand){1.5f}else {1.0f})
                .setDuration(2000)
                .start()
    }
    private fun openCreateNote() {
        val intent = Intent(context, NoteActivity::class.java)
        val data = Bundle()
        data.putInt(MODE.EXTRAS_KEY, MODE.CREATE.mode)

        intent.putExtras(data)
        startActivityForResult(intent, NOTE_REQUEST)
    }

    private fun openCreateToDo() {
        val date = Date(System.currentTimeMillis())
        val dateFormat = SimpleDateFormat("MMM dd YYYY", Locale.ENGLISH)
        val timeFormat = SimpleDateFormat("MM:HH", Locale.ENGLISH)

        val intent = Intent(context, TodoActivity::class.java)
        val data = Bundle()
        data.putInt(MODE.EXTRAS_KEY, MODE.CREATE.mode)

        data.putString(TodoActivity.EXTRA_DATE, dateFormat.format(date))
        data.putString(TodoActivity.EXTRA_TIME, timeFormat.format(date))

        intent.putExtras(data)
        startActivityForResult(intent, TODO_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            TODO_REQUEST -> {
                if(resultCode == Activity.RESULT_OK)
                    Log.e(logTag, "We created new TODO.")
                else
                    Log.e(logTag, "We didn't created new TODO.")
            }
            NOTE_REQUEST -> {
                if(resultCode == Activity.RESULT_OK)
                    Log.e(logTag, "We created new NOTE.")
                else
                    Log.e(logTag, "We didn't created new NOTE.")
            }
        }
    }

}