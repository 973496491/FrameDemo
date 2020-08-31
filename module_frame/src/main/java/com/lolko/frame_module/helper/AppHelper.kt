package com.lolko.frame_module.helper

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Build
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import java.util.*
import kotlin.concurrent.schedule


/**
 * <h2> App帮助类 </h2>
 *
 * @author Lolko.yao luolikongyao@gmail.com
 * @version 1.0
 * @since 2020年07月09日  周四 17时42分57秒
 */

@Suppress("unused")
object AppHelper {

    /**
     * 判断当前是否是Debug模式
     *
     * @return `false`false : 当前是Debug模式 - true : 当前不是Release模式
     */
    fun getAppIsDebugVersion(context: Context): Boolean {
        try {
            val info = context.applicationInfo
            return info.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    /**
     * 获取当前栈顶App所在包路径
     */
    @Suppress("DEPRECATION")
    fun getRunningActivityName(context: Context): String {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return activityManager.getRunningTasks(1)[0].topActivity!!.className
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    val phoneType: String
        get() = Build.MODEL

    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    val systemVersion: String
        get() = Build.VERSION.RELEASE

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 设置停止刷新和上拉加载
     */
    fun stopSmrefresh(sm: SmartRefreshLayout?) {
        sm?.let {
            it.finishRefresh()
            it.finishLoadMore()
        }
    }

    /**
     * 设置SmartRefreshLayout是否为无数据
     */
    fun setNoMoreData(sm: SmartRefreshLayout?, isNeedLoadMore: Boolean) {
        if (!isNeedLoadMore) {
            sm?.let {
                it.setNoMoreData(true)
                it.finishLoadMoreWithNoMoreData()
            }
        }
    }

    /**
     * 小键盘弹出事件处理
     *
     * @param activity          当先需要进行处理的Activity对象
     * @param et                需要获得/失去焦点的EditText对象
     * @param isShowSoftInput   是否显示小键盘
     * @param delay             防止UI引发问题, 延迟弹出小键盘的时间
     */
    fun openSoftInput(activity: Activity, et: EditText, isShowSoftInput: Boolean, delay: Long) {
        et.post {
            val inputManager: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            et.isFocusable = isShowSoftInput
            et.isFocusableInTouchMode = isShowSoftInput

            if (isShowSoftInput) {
                Timer("soft_input", false).schedule(delay) {
                    activity.runOnUiThread {
                        et.requestFocus()
                        inputManager.showSoftInput(et, 0)
                    }
                }
            } else {
                if (inputManager.isActive) {
                    if (activity.currentFocus?.windowToken != null) {
                        inputManager.hideSoftInputFromWindow(
                            activity.currentFocus?.windowToken,
                            InputMethodManager.HIDE_NOT_ALWAYS
                        )
                    }
                }
            }
        }
    }

}