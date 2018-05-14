package com.journaler.activities

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.journaler.R
import kotlinx.android.synthetic.main.activity_header.*
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity : AppCompatActivity() {
    protected abstract val tag: String
    protected abstract fun getLayout(): Int

    companion object{
        private var fontExoBold: Typeface ?= null
        private var fontExoRegular: Typeface?= null

        fun applyFonts(view: View, context: Context){
            var vTag = ""
            if(view.tag is String){
                vTag = view.tag as String
            }
            when(view){
                is ViewGroup -> {
                    for(x in 0..view.childCount - 1)
                        applyFonts(view.getChildAt(x), context)
                }
                is Button -> {
                    when(vTag){
                        context.getString(R.string.tag_font_bold) -> {
                            view.typeface = fontExoBold
                        }
                        else -> {
                            view.typeface = fontExoRegular
                        }
                    }
                }
                is TextView -> {
                    when(vTag){
                        context.getString(R.string.tag_font_bold) -> {
                            view.typeface = fontExoBold
                        }
                        else -> {
                            view.typeface = fontExoRegular
                        }
                    }
                }
                is EditText -> {
                    when(vTag){
                        context.getString(R.string.tag_font_bold) -> {
                            view.typeface = fontExoBold
                        }
                        else -> {
                            view.typeface = fontExoRegular
                        }
                    }
                }
            }
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        Log.e(tag, "[ ON POST CREATE ]")
        applyFonts()
    }
    protected fun applyFonts(){

    }
    private fun initFonts(){
        if(fontExoBold == null){
            Log.e(tag, "Initializing font [ Exo2-Bold ]")
            fontExoBold = Typeface.createFromAsset(assets, "fonts/Exo2-Bold.ttf")
        }
        if(fontExoRegular == null){
            Log.e(tag, "Initializing font [ Exo2-Regular ]")
            fontExoRegular = Typeface.createFromAsset(assets, "fonts/Exo2-Regular.ttf")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        setContentView(getLayout())
        activity_title.setText(getActivityTitle())
        setSupportActionBar(toolbar)
        Log.e(tag, "[ON CREATE]")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.drawing_menu -> {
                Log.e(tag, "Main menu.")
                return true
            }
            R.id.options_menu -> {
                Log.e(tag, "Options menu")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    abstract fun getActivityTitle(): Int

    override fun onRestart() {
        super.onRestart()
        Log.v(tag, "[ ON RESTART ]")
    }

    override fun onStart() {
        super.onStart()
        Log.v(tag, "[ ON START ]")
    }

    override fun onResume() {
        super.onResume()
        val animation = getAnimation(R.anim.top_to_bottom)
        findViewById<View>(R.id.toolbar).startAnimation(animation)
        Log.v(tag, "[ ON RESUME ]")
    }

    fun Activity.getAnimation(animID: Int): Animation {
        return AnimationUtils.loadAnimation(this, animID)
    }

    override fun onPostResume() {
        super.onPostResume()
        Log.v(tag, "[ ON POST RESUME ]")
    }

    override fun onPause() {
        super.onPause()
        Log.v(tag, "[ ON PAUSE ]")
        val animation = getAnimation(R.anim.hide_to_top)
        findViewById<View>(R.id.toolbar).startAnimation(animation)
    }

    override fun onStop() {
        super.onStop()
        Log.v(tag, "[ ON STOP ]")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(tag, "[ ON DESTROY ]")
    }
}