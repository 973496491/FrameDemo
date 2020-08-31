package com.lolko.frame_module.dialog;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lolko.frame_module.R;

public abstract class BaseTipDialogFragment extends BasePDialogFragment {

    protected TextView title;
    protected TextView content;
    protected TextView confirm;
    protected TextView cancel;

    private OnLeftClickListener onLeftClickListener;
    private OnRightClickListener onRightClickListener;

    public void addListener(OnLeftClickListener onLeftClickListener, OnRightClickListener onRightClickListener) {
        this.onLeftClickListener = onLeftClickListener;
        this.onRightClickListener = onRightClickListener;
    }

    @Override
    protected int getAnim() {
        return R.style.WindowGrowCenterAnimation;
    }

    @Override
    protected int getWidth() {
        return mContext.getResources().getDisplayMetrics().widthPixels / 4 * 3;
    }

    @Override
    protected int getHeight() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    @Override
    protected int getGravity() {
        return Gravity.CENTER;
    }

    @Override
    protected int getLayout() {
        return R.layout.base_window_dialog;
    }

    @Override
    protected void initView() {
        if (getView() == null) return;

        title = getView().findViewById(R.id.title);
        content = getView().findViewById(R.id.content);
        confirm = getView().findViewById(R.id.confirm);
        cancel = getView().findViewById(R.id.cancel);
        title.setText(getTitle());
        if (getContent() == null || getContent().toString().isEmpty()) {
            content.setVisibility(View.GONE);
        } else {
            content.setText(getContent());
        }
        confirm.setText(getLeft());
        cancel.setText(getRight());
        confirm.setOnClickListener(view -> {
            if (onLeftClickListener != null) {
                onLeftClickListener.onClick();
            }
            dismiss();
        });

        cancel.setOnClickListener(view -> {
            if (onRightClickListener != null) {
                onRightClickListener.onClick();
            }
            dismiss();
        });
    }

    @Override
    protected void initData() {

    }

    protected abstract CharSequence getTitle();

    protected abstract CharSequence getContent();

    protected abstract CharSequence getRight();

    protected abstract CharSequence getLeft();

    public void setTitle(CharSequence title) {
        this.title.setText(title);
    }

    public interface OnLeftClickListener {
        void onClick();
    }

    public interface OnRightClickListener {
        void onClick();
    }

}
