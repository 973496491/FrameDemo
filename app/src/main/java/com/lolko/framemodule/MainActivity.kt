package com.lolko.framemodule

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lolko.frame_module.dialog.BaseDialogBuild
import com.lolko.frame_module.redis.CacheService
import com.lolko.frame_module.redis.DatabaseInitialize
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListener()
        cacheTest()
    }

    private fun initListener() {
        show_dialog_tv.setOnClickListener { showDialog() }  //弹出Dialog
    }

    private fun showDialog() {
        BaseDialogBuild.getInstance(supportFragmentManager)
            .setDialogTitle("提示")
            .setDialogContent("测试Dialog内容")
            .setDialogLeftStr("取消")
            .setDialogRightStr("确认")
            .setDialogTag("MainActivity_showDialog")
            //.setLeftClick { finish() }
            //.setRightClick { finish() }
            .show()
    }

    private fun cacheTest() {
        DatabaseInitialize.init(this)

        var user = CacheService.get(User::class.java)
        Log.e(" TAG", user.usetName)
    }

}