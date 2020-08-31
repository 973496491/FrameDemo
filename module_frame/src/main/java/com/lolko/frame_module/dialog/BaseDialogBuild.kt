package com.lolko.frame_module.dialog

import androidx.fragment.app.FragmentManager


/**
 * <h2>  </h2>
 *
 * @author Lolko.yao luolikongyao@gmail.com
 * @version 1.0
 * @since 2020年08月28日 15:24
 */
@Suppress("unused")
class BaseDialogBuild {

    companion object {

        private var build: BaseDialogBuild? = null

        private var fmManager: FragmentManager? = null
        private var dialogTitle: String = "提示"     //标题文字
        private var dialogLeftStr: String = "取消"    //左边按钮文字
        private var dialogRightStr: String = "确定"    //右边按钮文字
        private var dialogContentStr: String = ""    //中间内容文字
        private var dialogTagStr: String = ""     //Tag

        @Synchronized
        fun getInstance(fmManager: FragmentManager): BaseDialogBuild {
            this.fmManager = fmManager
            if (build == null) {
                build = BaseDialogBuild()
            }
            return build as BaseDialogBuild
        }
    }

    private lateinit var onLeftClickListener: () -> Unit //左边按钮点击事件
    private lateinit var onRightClickListener: () -> Unit  //右边按钮点击事件

    /**
     * 设置Dialog标题
     */
    fun setDialogTitle(titleStr: String): BaseDialogBuild {
        dialogTitle = titleStr

        return build as BaseDialogBuild
    }

    /**
     * 设置Dialog左边按钮文字
     */
    fun setDialogLeftStr(leftStr: String): BaseDialogBuild {
        dialogLeftStr = leftStr

        return build as BaseDialogBuild
    }

    /**
     * 设置Dialog左边按钮文字
     */
    fun setDialogRightStr(rightStr: String): BaseDialogBuild {
        dialogRightStr = rightStr

        return build as BaseDialogBuild
    }

    /**
     * 设置Dialog内容
     */
    fun setDialogContent(contentStr: String): BaseDialogBuild {
        dialogContentStr = contentStr

        return build as BaseDialogBuild
    }

    /**
     * 设置Dialog标签
     */
    fun setDialogTag(tagStr: String): BaseDialogBuild {
        dialogTagStr = tagStr

        return build as BaseDialogBuild
    }

    /**
     * 设置左边按钮点击事件
     */
    fun setLeftClick(leftClick: () -> Unit): BaseDialogBuild {
        onLeftClickListener = leftClick

        return build as BaseDialogBuild
    }

    /**
     * 设置左边按钮点击事件
     */
    fun setRightClick(rightClick: () -> Unit): BaseDialogBuild {
        onRightClickListener = rightClick

        return build as BaseDialogBuild
    }

    fun show() {
        val dialogFm =
            DialogWindowHelp(dialogTitle, dialogLeftStr, dialogRightStr, dialogContentStr)
        dialogFm.show(fmManager!!, dialogTagStr)

        if (!::onLeftClickListener.isInitialized)
            onLeftClickListener = { dialogFm.dismiss() }

        if (!::onRightClickListener.isInitialized)
            onRightClickListener = { dialogFm.dismiss() }

        dialogFm.addListener(onLeftClickListener, onRightClickListener)
    }

}