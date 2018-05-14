package com.journaler.activities

import android.app.Activity
import android.os.Bundle
import com.journaler.R
import com.journaler.R.layout.activity_note

class NoteActivity : ItemActivity() {
    override val tag: String
        get() = "NoteActivity"

    override fun getLayout(): Int {
        return activity_note
    }

}
