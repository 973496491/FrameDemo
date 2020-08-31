package com.lolko.frame_module.helper;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * <h2> 倒计时处理帮助类 </h2>
 *
 * @author Lolko.yao
 * @version 1.1
 * @since 2020/04/03/星期五 16:58:18
 */
@SuppressWarnings({"unused", "RedundantSuppression"})
public class TimerCountHelper extends CountDownTimer {

    private TextView v; //发送验证码显示文字TextView
    private String lastTimeStr;  //剩余时间文字
    private String resendStr; //重新发送文字
    private int sendInColorRes; //发送验证码处理中文字颜色
    private int sendOutColorRes; //发送验证码结束中文字颜色

    /**
     * @param v           发送验证码显示文字TextView
     * @param time        距离再次发送间隔时长(毫秒)
     * @param lastTimeStr 剩余时间文字
     * @param resendStr   重新发送文字
     */
    public TimerCountHelper(TextView v, int time, String lastTimeStr, String resendStr) {
        super(time * 1000, 1000);
        this.v = v;
        this.lastTimeStr = lastTimeStr;
        this.resendStr = resendStr;
    }

    /**
     * @param v           发送验证码显示文字TextView
     * @param time        距离再次发送间隔时长(毫秒)
     * @param lastTimeStr 剩余时间文字
     * @param resendStr   重新发送文字
     */
    public TimerCountHelper(TextView v, int time, String lastTimeStr, String resendStr, int sendInColorRes, int sendOutColorRes) {
        super(time * 1000, 1000);
        this.v = v;
        this.lastTimeStr = lastTimeStr;
        this.resendStr = resendStr;
        this.sendInColorRes = sendInColorRes;
        this.sendOutColorRes = sendOutColorRes;
    }

    @Override
    public void onFinish() {
        if (v != null) {
            v.setText(resendStr);
            v.setClickable(true);
            v.setEnabled(true);
            if (sendOutColorRes > 0)
                v.setTextColor(sendOutColorRes);
        }
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (v != null) {
            v.setClickable(false);
            v.setEnabled(false);
            v.setText(String.format(lastTimeStr, millisUntilFinished / 1000));
            if (sendInColorRes > 0)
                v.setTextColor(sendInColorRes);
        }
    }

    /**
     * 当前是否可点击
     */
    public boolean isEnabled() {
        return v.isEnabled();
    }

}
