package com.makeit.baselib.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeit.baselib.R;


/**
 * 自定义Title
 *
 * @author chenfeng
 * @date 2015/11/18
 */
public class TitleBar extends RelativeLayout {
    //左边TextView是否可见
    private boolean leftTextViewVisible;
    //左边TextView文字
    private String leftTextViewText;
    //左边图片是否可见
    private boolean leftImageViewVisible;
    //标题文字
    private String titleTextViewText;
    //是否显示主标题右边图片
    private boolean titleTextViewRightDrawableVisible;
    //副标题文字
    private String viceTitleTextViewText;
    //副标题是否可见
    private boolean viceTitleTextViewVisible;
    //右边TextView文字
    private String rightTextViewText;
    //右边TextView是否可见
    private boolean rightTextViewVisible;
    //右边image是否可见
    private boolean rightImageViewVisible;

    private View rootView;
    private TextView leftTextView;
    private ImageView leftImageView;
    private TextView titleTextView;
    private TextView viceTitleTextView;
    private TextView rightTextView;

    private ClickListener listener;

    private boolean viceTitleTextViewRightDrawableVisible;
    private boolean isFinish = true;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(context, attrs, defStyleAttr);
        setView(context);
        setListener(context);
    }

    private void getAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        leftTextViewVisible = array.getBoolean(R.styleable.TitleBar_leftTextViewVisible, false);
        leftTextViewText = array.getString(R.styleable.TitleBar_leftTextViewText);
        leftImageViewVisible = array.getBoolean(R.styleable.TitleBar_leftImageViewVisible, false);
        titleTextViewText = array.getString(R.styleable.TitleBar_titleTextViewText);
        titleTextViewRightDrawableVisible = array.getBoolean(R.styleable.TitleBar_titleTextViewRightDrawableVisible, false);
        viceTitleTextViewRightDrawableVisible = array.getBoolean(R.styleable.TitleBar_vicetitleTextViewRightDrawableVisible, false);
        viceTitleTextViewText = array.getString(R.styleable.TitleBar_viceTitleTextViewText);
        viceTitleTextViewVisible = array.getBoolean(R.styleable.TitleBar_viceTitleTextViewVisible, false);
        rightTextViewText = array.getString(R.styleable.TitleBar_rightTextViewText);
        rightTextViewVisible = array.getBoolean(R.styleable.TitleBar_rightTextViewVisible, false);
        rightImageViewVisible = array.getBoolean(R.styleable.TitleBar_rightImageViewVisible, false);

        array.recycle();
    }

    private void setView(Context context) {
        rootView = View.inflate(context, R.layout.titlebar_layout, null);
        leftTextView = (TextView) rootView.findViewById(R.id.tv_leftTextView);
        leftImageView = (ImageView) rootView.findViewById(R.id.iv_back);
        titleTextView = (TextView) rootView.findViewById(R.id.tv_title);
        viceTitleTextView = (TextView) rootView.findViewById(R.id.tv_vice_title);
        rightTextView = (TextView) rootView.findViewById(R.id.tv_rightTextView);

        leftTextView.setVisibility(leftTextViewVisible ? VISIBLE : GONE);
        leftTextView.setText(leftTextViewText);

        leftImageView.setVisibility(leftImageViewVisible ? VISIBLE : GONE);

        titleTextView.setText(titleTextViewText);
        if (titleTextViewRightDrawableVisible) {
            titleTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.arrow_down), null);
        }
        if (!titleTextViewRightDrawableVisible) {
            titleTextView.setTextColor(Color.WHITE);
        }
        if (viceTitleTextViewRightDrawableVisible) {
            viceTitleTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.arrow_down), null);
            viceTitleTextView.setCompoundDrawablePadding(5);
        }
        if (!viceTitleTextViewRightDrawableVisible) {
            viceTitleTextView.setTextColor(Color.WHITE);
        }

        viceTitleTextView.setVisibility(viceTitleTextViewVisible ? VISIBLE : GONE);
        viceTitleTextView.setText(viceTitleTextViewText);

        rightTextView.setVisibility(rightTextViewVisible ? VISIBLE : GONE);
        rightTextView.setText(rightTextViewText);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(rootView, params);
    }

    /**
     * 给控件设置监听
     */
    private void setListener(final Context context) {
        leftTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.leftTextViewClick();
                }
            }
        });

        leftImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFinish) {
                    ((Activity) context).finish();//这里默认把activity关闭掉，如果不符合可以打开下面的注释在回调方法里面处理
                }
                if (null != listener) {
                    listener.leftImageViewClick();
                }
            }
        });

        titleTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.titleTextViewClick();
                }
            }
        });

        rightTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.rightTextViewClick();
                }
            }
        });
        viceTitleTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.titleTextViewClick();
                }
            }
        });
    }

    /**
     * 设置监听，外部使用
     *
     * @param listener
     */
    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }


    /**
     * 设置标题内容
     *
     * @param titleText
     */
    public void setTitleText(CharSequence titleText) {
        titleTextView.setText(titleText);
        requestLayout();
    }

    /**
     * 设置副标题内容
     *
     * @param titleText
     */
    public void setViceTitleText(CharSequence titleText) {
        viceTitleTextView.setText(titleText);
        requestLayout();
    }

    /**
     * 设置副标题文字是否可见
     *
     * @param visible true为可见
     */
    public void setViceTextViewVisible(boolean visible) {
        viceTitleTextView.setVisibility(visible ? VISIBLE : GONE);
        requestLayout();
    }

    /**
     * 设置左边文字
     *
     * @param leftText
     */
    public void setLeftText(CharSequence leftText) {
        leftTextView.setText(leftText);
        requestLayout();
    }

    /**
     * 设置右边文字
     *
     * @param rightText
     */
    public void setRightText(CharSequence rightText) {
        rightTextView.setText(rightText);
        requestLayout();
    }

    /**
     * 获取右边文字
     */
    public String getRightTextViewText() {
        return rightTextView.getText().toString();

    }

    /**
     * 设置右边文字是否可见
     *
     * @param visible true为可见
     */
    public void setRightTextViewVisible(boolean visible) {
        rightTextView.setVisibility(visible ? VISIBLE : GONE);
        requestLayout();
    }

    public void setRightTextViewSize(int size) {
        rightTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    /**
     * 设置左边图片是否可见
     *
     * @param visible true为可见
     */
    public void setLeftImageViewVisible(boolean visible) {
        leftImageView.setVisibility(visible ? VISIBLE : GONE);
        requestLayout();
    }

    /**
     * 设置标题文字右边的图标是否可见
     *
     * @param visible true为可见
     */
    public void setTitleTextViewRightDrawableVisible(boolean visible) {
        if (!visible) {
            titleTextView.setEnabled(false);
            titleTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    public void setViceTitleTextViewRightDrawableVisible(boolean visible) {
        if (!visible) {
            viceTitleTextView.setEnabled(false);
            viceTitleTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    /**
     * 设置左边文字是否可见
     *
     * @param visible true为可见
     */
    public void setLeftTextViewVisible(boolean visible) {
        leftTextView.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setLeftImageViewFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }


    public interface ClickListener {
        void leftTextViewClick();

        void leftImageViewClick();

        void titleTextViewClick();

        void rightTextViewClick();

    }

    /**
     * 空实现，减少代码书写
     */
    public static class SimpleClickListener implements ClickListener {

        @Override
        public void leftTextViewClick() {

        }

        @Override
        public void leftImageViewClick() {

        }

        @Override
        public void titleTextViewClick() {

        }

        @Override
        public void rightTextViewClick() {

        }
    }


}
