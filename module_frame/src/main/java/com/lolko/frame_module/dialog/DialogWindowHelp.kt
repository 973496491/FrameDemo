package com.lolko.frame_module.dialog

/**
 * <h2>  </h2>
 *
 * @author Lolko.yao luolikongyao@gmail.com
 * @version 1.0
 * @since 2020年08月12日 18:09
 */
@Suppress("unused")
class DialogWindowHelp : BaseTipDialogFragment {

    private var dialogTitle: String    //标题文字
    private var dialogLeftStr: String  //左边按钮文字
    private var dialogRightStr: String  //右边按钮文字
    private var dialogContentStr: String  //中间内容文字

    constructor(contentStr: String) {
        this.dialogTitle = "提示"
        this.dialogLeftStr = "取消"
        this.dialogRightStr = "确定"
        this.dialogContentStr = contentStr
    }

    constructor(title: String, leftStr: String, rightStr: String, contentStr: String) {
        this.dialogTitle = title
        this.dialogLeftStr = leftStr
        this.dialogRightStr = rightStr
        this.dialogContentStr = contentStr
    }

    override fun getTitle(): CharSequence {
        return dialogTitle
    }

    override fun getLeft(): CharSequence {
        return dialogLeftStr
    }

    override fun getRight(): CharSequence {
        return dialogRightStr
    }

    override fun getContent(): CharSequence {
        return dialogContentStr
    }

}