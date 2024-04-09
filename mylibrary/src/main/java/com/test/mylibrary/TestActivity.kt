package com.test.mylibrary

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.drake.brv.utils.BRV
import com.drake.brv.utils.divider
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup
import com.test.mylibrary.databinding.ActivityTestBinding
import java.util.Random

@Route(path = "/test/activity")
class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        BRV.modelId = BR.m
        binding.rv.linear().divider {
            setDivider(1, false)
            setColor(Color.GRAY)
        }.setup {
            addType<TestModel>(R.layout.item_test)
            R.id.item.onClick {
                val model = getModel<TestModel>()
                val isChecked = model.isChecked
                setChecked(layoutPosition, !isChecked)
                Toast.makeText(this@TestActivity, "点击了 ${model.name}", Toast.LENGTH_SHORT).show()
            }
            onChecked { position, checked, allChecked ->
                val model = getModel<TestModel>(position)
                model.isChecked = checked
                model.notifyChange()
            }
        }.models = getModels()
    }

    private fun getModels(): List<TestModel> {
        val temps = mutableListOf<TestModel>()
        for (i in 0..30) {
            temps.add(TestModel("张三-${i}", Random().nextInt(30) + 1))
        }
        return temps
    }
}