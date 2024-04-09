package com.test.mylibrary

import android.graphics.Color
import androidx.databinding.BaseObservable
import com.drake.brv.item.ItemPosition

/**
 *  @author : Administrator
 *  @date : 2024/4/9 14:56
 *  @description :
 */
class TestModel(
    val name: String, val age: Int,
    var isChecked: Boolean = false,
    override var itemPosition: Int = 0
) : ItemPosition, BaseObservable(){
    val bgColor get() = if(isChecked) Color.RED else Color.WHITE
}
