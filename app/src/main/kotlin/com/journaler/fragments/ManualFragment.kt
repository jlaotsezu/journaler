package com.journaler.fragments

import com.journaler.R

class ManualFragment: BaseFragment(){
    override val logTag: String
        get() = "Manual Fragment"

    override fun getLayout(): Int {
        return R.layout.fragment_manual
    }


}