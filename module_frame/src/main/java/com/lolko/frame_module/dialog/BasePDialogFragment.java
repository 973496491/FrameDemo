package com.lolko.frame_module.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.lolko.frame_module.R;

import org.jetbrains.annotations.NotNull;

public abstract class BasePDialogFragment extends DialogFragment {

    protected View mRootView;

    protected Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configStyle();
    }

    protected void configStyle() {
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayout(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
    }

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }

    protected abstract int getAnim();

    private void initDialog() {
        if (getDialog() == null)
            return;
        Window window = getDialog().getWindow();
        assert window != null;
        window.setWindowAnimations(getAnim());
        //DialogSearch的宽
        window.setLayout(getWidth(), getHeight());
        window.setGravity(getGravity());

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            try {
//                @SuppressLint("PrivateApi")
//                Field field = Class.forName("com.android.internal.policy.DecorView").getDeclaredField("mSemiTransparentStatusBarColor");
//                field.setAccessible(true);
//                field.setInt(window.getDecorView(), Color.TRANSPARENT);  //去掉高版本蒙层改为透明
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    protected abstract int getWidth();

    protected abstract int getHeight();

    protected abstract int getGravity();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void show(@NotNull FragmentManager manager, String tag) {
        try {
            //防止连续点击add多个fragment
            manager.beginTransaction().remove(this).commit();
            super.show(manager, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract int getLayout();

    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

}
