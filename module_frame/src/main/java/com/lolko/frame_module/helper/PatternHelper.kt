package com.lolko.frame_module.helper

import java.util.regex.Pattern

/**
 * <h2> 正则校验 </h2>
 *
 * @author Lolko.yao luolikongyao@gmail.com
 * @version 1.0
 * @since 2020年08月06日 21:01
 */
@Suppress("unused")
object PatternHelper {

    //邮箱正则
    private const val PATTERN_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"

    //手机号正则
    private const val PATTERN_MOBILE_PHONE = "^1[2-9][0-9]{9}$"

    /**
     * 判断是字符串是否是合法邮箱
     *
     * @param str 需要判断的字符串
     * @return {true : 是合法邮箱} {false : 不是合法邮箱}
     */
    fun checkEmail(str: String?): Boolean {
        str?.let {
            val mailPattern = Pattern.compile(PATTERN_EMAIL)
            return mailPattern.matcher(it).matches()
        }
        return false
    }

    /**
     * 判断当前是否是手机号
     *
     * @param str 需要判断的字符串
     * @return {true : 是手机号字符串} {false : 不是手机号字符串}
     */
    fun checkPhone(str: String?): Boolean {
        str?.let {
            val mailPattern = Pattern.compile(PATTERN_MOBILE_PHONE)
            return mailPattern.matcher(it).matches()
        }
        return false
    }

}