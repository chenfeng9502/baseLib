package com.makeit.baselib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeit.baselib.R;

import java.lang.reflect.Field;



/**
 *自定义相对布局
 * @author chenfeng
 * @date 2015/11/13
 */
public class InfoRelativeLayoutView extends RelativeLayout {
    //左边CheckBox
    private CheckBox leftCheckBox;
    //左边图片
    private ImageView leftImageView;
    //左边信息TextView
    private TextView infoTextView;
    // 左边信息提示TextView
    private TextView leftTipTextView;
    //右边副信息TextView
    private TextView rightTextView;
    //右边图片，默认为右箭头
    private ImageView rightImageView;
    //右边可编辑EditText,当CheckBox选中时才可编辑
    private EditText rightEditText;
    //底部分割线
    private View lineView;

    //左边图片自定义属性
    private int leftImgSize;
    private Drawable leftImgBg;
    private int leftImgLeftMargin;
    private boolean leftImgVisible;

    //左边CheckBox自定义属性
    private int leftCheckBoxSize;
    private Drawable leftCheckBoxBg;
    private int leftCheckBoxLeftMargin;
    private boolean leftCheckBoxVisible;
    private int leftCheckBoxTextSize;
    private int leftCheckBoxTextColor;
    private String leftCheckBoxText;

    //左边信息TextView自定义属性
    private String tipText;
    private int tipTextSize;
    private int tipTextColor;
    private boolean tipTextVisible;

    private String infoText;
    private int infoTextSize;
    private int infoTextColor;
    private int infoTextLeftMargin;
    private Drawable infoTextRightDrawable;

    //右边副信息TextView自定义属性
    private String rightText;
    private int rightTextSize;
    private int rightTextColor;
    private int rightTextRightMargin;
    private int leftRidhtMargin;

    //右边图片自定义属性
    private int rightImgSize;
    private Drawable rightImgBg;
    private int rightImgRightMargin;
    private boolean rightImgVisible;

    //右边可编辑文本EditText自定义属性
    private int rightEdtRightMargin;
    private String rightEdtHint;
    private int rightEdtHintColor;
    private int rightEdtTextSize;
    private int rightEdtTextColor;
    private boolean rightEdtVisible;
    private boolean rightEdtEnable;

    //底部分割线自定义属性
    private boolean bottomLineMarginLeft;
    private boolean bottomLineVisible;

    public TextView getInfoTextView() {
        return infoTextView;
    }

    public InfoRelativeLayoutView(Context context) {
        this(context, null);
    }

    public InfoRelativeLayoutView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public InfoRelativeLayoutView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.InfoRelativeLayoutView);
        leftImgSize = (int) array.getDimension(R.styleable.InfoRelativeLayoutView_leftImgSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()));
        leftImgBg = array.getDrawable(R.styleable.InfoRelativeLayoutView_leftImgBg);
        leftImgLeftMargin = (int) array.getDimension(R.styleable.InfoRelativeLayoutView_leftImgLeftMargin, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
        leftImgVisible = array.getBoolean(R.styleable.InfoRelativeLayoutView_leftImgVisible, true);

        leftCheckBoxSize = (int) array.getDimension(R.styleable.InfoRelativeLayoutView_leftCheckBoxSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()));
        leftCheckBoxBg = array.getDrawable(R.styleable.InfoRelativeLayoutView_leftCheckBoxBg);
        leftCheckBoxLeftMargin = (int) array.getDimension(R.styleable.InfoRelativeLayoutView_leftCheckBoxLeftMargin, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
        leftCheckBoxVisible = array.getBoolean(R.styleable.InfoRelativeLayoutView_leftCheckBoxVisible, true);
        leftCheckBoxTextSize = (int) array.getDimension(R.styleable.InfoRelativeLayoutView_leftCheckBoxTextSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, getResources().getDisplayMetrics()));
        leftCheckBoxTextColor = array.getColor(R.styleable.InfoRelativeLayoutView_leftCheckBoxTextColor, Color.BLACK);
        leftCheckBoxText = array.getString(R.styleable.InfoRelativeLayoutView_leftCheckBoxText);

        tipText = array.getString(R.styleable.InfoRelativeLayoutView_tipText);
        tipTextSize = (int) array.getDimension(R.styleable.InfoRelativeLayoutView_tipTextSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics()));
        tipTextColor = array.getColor(R.styleable.InfoRelativeLayoutView_tipTextColor,Color.BLACK);
        tipTextVisible = array.getBoolean(R.styleable.InfoRelativeLayoutView_tipTextVisible, false);

        infoText = array.getString(R.styleable.InfoRelativeLayoutView_infoText);
        infoTextSize = (int) array.getDimension(R.styleable.InfoRelativeLayoutView_infoTextSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        infoTextColor = array.getColor(R.styleable.InfoRelativeLayoutView_infoTextColor, Color.BLACK);
        infoTextLeftMargin = (int) array.getDimension(R.styleable.InfoRelativeLayoutView_infoTextLeftMargin, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
        infoTextRightDrawable = array.getDrawable(R.styleable.InfoRelativeLayoutView_infoTextRightDrawable);

        rightText = array.getString(R.styleable.InfoRelativeLayoutView_rightText);
        rightTextSize = (int) array.getDimension(R.styleable.InfoRelativeLayoutView_rightTextSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        rightTextColor = array.getColor(R.styleable.InfoRelativeLayoutView_rightTextColor, Color.BLACK);
        rightTextRightMargin = (int) array.getDimension(R.styleable.InfoRelativeLayoutView_rightTextRightMargin, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
        leftRidhtMargin=(int) array.getDimension(R.styleable.InfoRelativeLayoutView_leftRidhtMargin, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, getResources().getDisplayMetrics()));
        rightImgVisible = array.getBoolean(R.styleable.InfoRelativeLayoutView_rightImgVisible, true);

        rightImgSize = (int) array.getDimension(R.styleable.InfoRelativeLayoutView_rightImgSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics()));
        rightImgBg = array.getDrawable(R.styleable.InfoRelativeLayoutView_rightImgBg);
        rightImgRightMargin = (int) array.getDimension(R.styleable.InfoRelativeLayoutView_rightImgRightMargin, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));

        rightEdtRightMargin = (int) array.getDimension(R.styleable.InfoRelativeLayoutView_rightEdtRightMargin, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
        rightEdtHint = array.getString(R.styleable.InfoRelativeLayoutView_rightEdtHint);
        rightEdtHintColor = array.getColor(R.styleable.InfoRelativeLayoutView_rightEdtHintColor, Color.GRAY);
        rightEdtTextSize = (int) array.getDimension(R.styleable.InfoRelativeLayoutView_rightEdtTextSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        rightEdtTextColor = array.getColor(R.styleable.InfoRelativeLayoutView_rightEdtTextColor, Color.BLACK);
        rightEdtVisible = array.getBoolean(R.styleable.InfoRelativeLayoutView_rightEdtVisible, false);
        rightEdtEnable = array.getBoolean(R.styleable.InfoRelativeLayoutView_rightEdtEnable, false);

        bottomLineMarginLeft = array.getBoolean(R.styleable.InfoRelativeLayoutView_bottomLineMarginLeft, false);
        bottomLineVisible = array.getBoolean(R.styleable.InfoRelativeLayoutView_bottomLineVisible, true);


        leftImageView = new ImageView(context);
        leftImageView.setId(R.id.leftImageViewId);
        LayoutParams leftImgParams = new LayoutParams(leftImgSize, leftImgSize);
        leftImgParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftImgParams.addRule(RelativeLayout.CENTER_VERTICAL);
        leftImgParams.leftMargin = leftImgLeftMargin;
        leftImageView.setBackgroundDrawable(leftImgBg);
        leftImageView.setVisibility(leftImgVisible ? View.VISIBLE : View.GONE);
        addView(leftImageView, leftImgParams);

        leftCheckBox = new CheckBox(context);
        leftCheckBox.setId(R.id.leftCheckBoxViewId);
        LayoutParams leftCheckBoxParams = new LayoutParams(leftCheckBoxSize, leftCheckBoxSize);
        leftCheckBoxParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftCheckBoxParams.addRule(RelativeLayout.CENTER_VERTICAL);
        leftCheckBoxParams.leftMargin = leftCheckBoxLeftMargin;
        leftCheckBox.setButtonDrawable(leftCheckBoxBg);
        leftCheckBox.setPadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()), 0, 0, 0);
        leftCheckBox.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftCheckBoxTextSize);
        leftCheckBox.setTextColor(leftCheckBoxTextColor);
        leftCheckBox.setText(leftCheckBoxText);
        leftCheckBox.setVisibility(leftCheckBoxVisible ? View.VISIBLE : View.GONE);
        addView(leftCheckBox, leftCheckBoxParams);


        infoTextView = new TextView(context);
        infoTextView.setId(R.id.infoView_infoText);
        LayoutParams infoTextViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        infoTextViewParams.addRule(RelativeLayout.RIGHT_OF, leftCheckBoxVisible ? leftCheckBox.getId() : leftImageView.getId());
        if (!tipTextVisible) {
            infoTextViewParams.addRule(RelativeLayout.CENTER_VERTICAL);
        }
        infoTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, infoTextRightDrawable, null);
        infoTextView.setGravity(Gravity.CENTER_VERTICAL);
        infoTextViewParams.leftMargin = infoTextLeftMargin;
        infoTextViewParams.rightMargin = infoTextLeftMargin;
        infoTextViewParams.topMargin=infoTextLeftMargin;
        if (!tipTextVisible) {
            infoTextViewParams.bottomMargin = infoTextLeftMargin;
        }
        infoTextView.setText(infoText);
        infoTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, infoTextSize);
        infoTextView.setTextColor(infoTextColor);
        addView(infoTextView, infoTextViewParams);


        leftTipTextView = new TextView(context);
        leftTipTextView.setId(R.id.infoView_tipText);
        LayoutParams tipTextViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        infoTextViewParams.addRule(RelativeLayout.ALIGN_LEFT,infoTextView.getId());
        tipTextViewParams.addRule(RelativeLayout.BELOW, infoTextView.getId());
        tipTextViewParams.leftMargin = infoTextLeftMargin;
        tipTextViewParams.rightMargin = infoTextLeftMargin;
        tipTextViewParams.topMargin = infoTextLeftMargin / 2;
        tipTextViewParams.bottomMargin = infoTextLeftMargin;
        leftTipTextView.setText(tipText);
        leftTipTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, tipTextSize);
        leftTipTextView.setTextColor(tipTextColor);
        leftTipTextView.setVisibility(tipTextVisible ? VISIBLE : GONE);
        addView(leftTipTextView, tipTextViewParams);


        rightImageView = new ImageView(context);
        rightImageView.setId(R.id.rightImageViewId);
        LayoutParams rightImgParams = new LayoutParams(rightImgSize, rightImgSize);
        rightImgParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rightImgParams.addRule(RelativeLayout.CENTER_VERTICAL);
        rightImgParams.rightMargin = rightImgRightMargin;
        rightImageView.setBackgroundDrawable(rightImgBg);
        rightImageView.setVisibility(rightImgVisible ? VISIBLE : GONE);
        addView(rightImageView, rightImgParams);

        rightTextView = new TextView(context);
//        rightTextView.setId(3);
        LayoutParams rightTextViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (rightImgVisible) {
            rightTextViewParams.addRule(RelativeLayout.LEFT_OF, rightImageView.getId());
        } else {
            rightTextViewParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }
        rightTextViewParams.addRule(RelativeLayout.CENTER_VERTICAL);
        rightTextViewParams.rightMargin = rightTextRightMargin;
        rightTextViewParams.leftMargin=leftRidhtMargin;
        rightTextView.setText(rightText);
        rightTextView.setMaxLines(1);
        rightTextView.setEllipsize(TextUtils.TruncateAt.END);
        rightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);
        rightTextView.setTextColor(rightTextColor);
        addView(rightTextView, rightTextViewParams);


        rightEditText = new EditText(context);
        rightEditText.setId(R.id.info_right_edt);
        try {
            Field mCursorDrawableRes = rightEditText.getClass().getSuperclass().getDeclaredField("mCursorDrawableRes");
            mCursorDrawableRes.setAccessible(true);
            mCursorDrawableRes.set(rightEditText, R.mipmap.cursor);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        LayoutParams rightEditTextParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        rightEditTextParams.rightMargin = rightEdtRightMargin;

        if (leftCheckBoxVisible) {
            rightEditTextParams.addRule(RelativeLayout.RIGHT_OF, R.id.leftCheckBoxViewId);
        } else {
            rightEditTextParams.addRule(RelativeLayout.RIGHT_OF, R.id.infoView_infoText);
        }
        rightEditTextParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rightEditTextParams.addRule(RelativeLayout.CENTER_VERTICAL);
        this.rightEditText.setGravity(CENTER_IN_PARENT);
        this.rightEditText.setBackgroundDrawable(null);
        this.rightEditText.setHint(rightEdtHint);
        this.rightEditText.setHintTextColor(rightEdtHintColor);
        this.rightEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightEdtTextSize);
        this.rightEditText.setTextColor(rightEdtTextColor);
        this.rightEditText.setEnabled(leftCheckBox.isChecked());
        this.rightEditText.setEnabled(rightEdtEnable);
        this.rightEditText.setVisibility(rightEdtVisible ? VISIBLE : GONE);
        this.rightEditText.clearFocus();
        addView(this.rightEditText, rightEditTextParams);

        lineView = new View(context);
        LayoutParams lineViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.5f, getResources().getDisplayMetrics()));
        lineViewParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lineViewParams.leftMargin = (bottomLineMarginLeft ? infoTextLeftMargin + (leftImgVisible ? leftImgLeftMargin + leftImgSize : 0) + (leftCheckBoxVisible ? leftCheckBoxLeftMargin + (leftCheckBoxBg!=null?leftCheckBoxBg.getIntrinsicWidth():0) : 0) : 0);
        lineView.setBackgroundColor(Color.parseColor("#DDDDDD"));
        lineView.setVisibility(bottomLineVisible ? VISIBLE : GONE);
        addView(lineView, lineViewParams);
        setClickable(false);
        setListener();
        array.recycle();
    }

    private void setListener() {
        leftCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rightEditText.setEnabled(true);
                } else {
                    rightEditText.setEnabled(false);
                }
            }
        });
    }

    public String getRightText() {
        return (String) rightTextView.getText();
    }

    public String getRightEdtText() {
        return rightEditText.getText().toString().trim();
    }

    public void setRightEdtText(String edtText){
        rightEditText.setText(edtText);
    }


    public void setInfoTextViewMargin(){
        LayoutParams params = (LayoutParams) infoTextView.getLayoutParams();
        params.topMargin=0;
        params.bottomMargin=0;
        requestLayout();
    }

    /**
     * 设置右边文字属性
     */
    public void setRightEdittextInputType(int type){
        rightEditText.setInputType(type);
    }

    /**
     * 获取checkbox当前状态
     *
     * @return
     */
    public boolean getCheckBoxState() {
        return leftCheckBox.isChecked();
    }

    /**
     * 设置Checkbox是否可见
     *
     * @param visible
     */
    public void setLeftCheckBoxVisibility(boolean visible) {
        leftCheckBox.setVisibility(visible ? VISIBLE : GONE);
//        requestLayout();
    }

    public void setLeftCheckBoxText(String s) {
        leftCheckBox.setText(s);
//        requestLayout();
    }

    public void setLeftCheckBoxChecked(boolean isChecked) {
        leftCheckBox.setChecked(isChecked);
    }

    /**
     * 设置底线距左边是否有距离
     *
     * @param hasMargin true为距左边有距离，距离由左边文字和左边图片决定
     */
    public void setBottomLineMarginLeft(boolean hasMargin) {
        LayoutParams params = (LayoutParams) lineView.getLayoutParams();
        params.leftMargin = (hasMargin ? infoTextLeftMargin + (leftImgVisible ? leftImgLeftMargin + leftImgSize : 0) : 0);
    }

    public void setBottomLineVisibility(boolean visible) {
        lineView.setVisibility(visible ? VISIBLE : GONE);
    }

    /**
     * 设置左边图片是否可见
     *
     * @param visible true为可见
     */
    public void setLeftImageViewVisibility(boolean visible) {
        leftImageView.setVisibility(visible ? VISIBLE : GONE);
        infoTextView.setTextColor(Color.BLUE);
        requestLayout();
    }

    /**
     * 设置左边图片背景资源
     *
     * @param resId
     */
    public void setLeftImageViewBackgroundResource(int resId) {
        leftImageView.setBackgroundResource(resId);
        requestLayout();
    }

    /**
     * 设置左边图片大小
     *
     * @param imgSizeUnitDIP 宽高， 单位为dp
     */
    public void setLeftImgSize(int imgSizeUnitDIP) {
        LayoutParams params = (LayoutParams) leftImageView.getLayoutParams();
        params.width = params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, imgSizeUnitDIP, getResources().getDisplayMetrics());
        requestLayout();
    }

    /**
     * 设置左边图片左边距
     *
     * @param leftMarginUnitDIP 左边距
     */
    public void setLeftImgLeftMargin(int leftMarginUnitDIP) {
        LayoutParams params = (LayoutParams) leftImageView.getLayoutParams();
        params.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftMarginUnitDIP, getResources().getDisplayMetrics());
        requestLayout();
    }

    public void setTipText(String tipText) {
        this.tipText = tipText;
        leftTipTextView.setText(tipText);
        requestLayout();
    }

    /**
     * 设置标题信息
     *
     * @param infoTitle 主标题信息
     */
    public void setInfoText(String infoTitle) {
        infoText = infoTitle;
        infoTextView.setText(infoTitle);
        requestLayout();
    }

    /**
     * 获取标题信息
     *
     * @return 标题信息
     */
    public String getInfoText() {
        return infoText;
    }

    /**
     * 设置标题文字颜色
     *
     * @param color
     */
    public void setInfoTextColor(int color) {
        infoTextView.setTextColor(color);
        requestLayout();
    }

    /**
     * 设置标题文字大小
     *
     * @param textSizeUintSP 标题文字大小，单位为sp
     */
    public void setInfoTextSize(int textSizeUintSP) {
        infoTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeUintSP);
        requestLayout();
    }

    /**
     * 设置标题左边距
     *
     * @param infoTextLeftMarginUnitDIP 左边距，单位为dp
     */
    public void setInfoTextLeftMargin(int infoTextLeftMarginUnitDIP) {
        LayoutParams params = (LayoutParams) infoTextView.getLayoutParams();
        params.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, infoTextLeftMarginUnitDIP, getResources().getDisplayMetrics());
        requestLayout();
    }

    /**
     * 设置副标题文字
     *
     * @param rightText 副标题文字
     */
    public void setRightText(String rightText) {
        this.rightText = rightText;
        rightTextView.setText(rightText);
        requestLayout();
    }

    public void setSingleline(){
        rightTextView.setSingleLine(true);
        rightTextView.setEllipsize(TextUtils.TruncateAt.END);
    }

    /**
     * 设置副标题文字大小，单位为SP
     *
     * @param textSizeUnitSP
     */
    public void setRightTextSize(int textSizeUnitSP) {
        rightTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeUnitSP);
        requestLayout();
    }

    /**
     * 设置副标题文字颜色
     *
     * @param textColor
     */
    public void setRightTextColor(int textColor) {
        rightTextView.setTextColor(textColor);
        requestLayout();
    }

    /**
     * 设置副标题右边距
     *
     * @param rightTextRightMarginUnitDIP 右边距，单位为dp
     */
    public void setRightTextRightMargin(int rightTextRightMarginUnitDIP) {
        LayoutParams params = (LayoutParams) rightTextView.getLayoutParams();
        params.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightTextRightMarginUnitDIP, getResources().getDisplayMetrics());
        requestLayout();
    }

    /**
     * 设置右边图片大小
     *
     * @param imgSizeUnitDIP 宽高， 单位为dp
     */
    public void setRightImgSize(int imgSizeUnitDIP) {
        LayoutParams params = (LayoutParams) rightImageView.getLayoutParams();
        params.width = params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, imgSizeUnitDIP, getResources().getDisplayMetrics());
        requestLayout();
    }

    /**
     * 设置右边图片右边距
     *
     * @param rightImgRightMarginUnitDIP 右边距，单位为dp
     */
    public void setRightImgRightMargin(int rightImgRightMarginUnitDIP) {
        LayoutParams params = (LayoutParams) rightImageView.getLayoutParams();
        params.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightImgRightMarginUnitDIP, getResources().getDisplayMetrics());
        requestLayout();
    }

    /**
     * 设置右边图片背景资源
     *
     * @param resId
     */
    public void setRightImageViewBackgroundResource(int resId) {
        rightImageView.setBackgroundResource(resId);
        requestLayout();
    }

    /**
     * 设置右边图片是否可见
     *
     * @param visible
     */
    public void setRightImageViewVisibility(boolean visible) {
        rightImageView.setVisibility(visible ? VISIBLE : INVISIBLE);
    }

    /**
     * 设置右边图片是否可见
     *
     * @param visible
     */
    public void setRightImageViewVisibiliable(boolean visible) {
        rightImageView.setVisibility(visible ? VISIBLE : GONE);

    }

    /**
     * 设置右边可编辑文本EditText是否可见
     *
     * @param visible
     */
    public void setRightEditTextVisibility(boolean visible) {
        rightEditText.setVisibility(visible ? VISIBLE : INVISIBLE);
    }

    /**
     * 设置右边可编辑文本EditText提示内容
     *
     * @param s
     */
    public void setRightEditTextHintText(String s) {
        rightEditText.setHint(s);
    }


    public void setRightEditTextEnable(boolean enable) {
        rightEditText.setEnabled(enable);

    }

    /**
     * 设置标题提示文字的图片
     * @param left 左边图片
     * @param top 上面图片
     * @param right 右边图片
     * @param bottom 底下图片
     */
    public void setInfoTextViewDrawable(int left, int top, int right, int bottom) {
        infoTextView.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
    }


    public void setRightEditTextMaxLength(int maxLength) {
        rightEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
    }

}
