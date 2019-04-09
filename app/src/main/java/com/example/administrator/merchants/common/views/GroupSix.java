package com.example.administrator.merchants.common.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.merchants.R;

import java.util.ArrayList;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：规格
 */
public class GroupSix<X extends TextView> extends ViewGroup {
    public static final String BTN_MODE = "BTNMODE"; //按钮模式
    public static final String TEV_MODE = "TEVMODE"; //文本模式
    private static final String TAG = "IViewGroup";
    private final int HorInterval = 20;    //水平间隔
    private final int VerInterval = 10;    //垂直间隔
    private int viewWidth;   //控件的宽度
    private int viewHeight;  //控件的高度
    private ArrayList<String> mTexts = new ArrayList<>();
    private Context mContext;
    private int textModePadding = 15;
    //正常样式
    private float itemTextSize = 15;
    private int itemBGResNor = R.drawable.dialog_white;
    private int itemTextColorNor = Color.parseColor("#000000");
    //选中的样式
    private int itemBGResPre = R.drawable.dialog_yellow;
    private int itemTextColorPre = Color.parseColor("#ffffff");

    public GroupSix(Context context) {
        this(context, null);
    }

    public GroupSix(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    /**
     * 计算控件的大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = measureWidth(widthMeasureSpec);
        viewHeight = measureHeight(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(viewWidth, getViewHeight());
    }

    private int measureWidth(int pWidthMeasureSpec) {
        int result = 0;
        int widthMode = MeasureSpec.getMode(pWidthMeasureSpec);
        int widthSize = MeasureSpec.getSize(pWidthMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = widthSize;
                break;
        }
        return result;
    }

    private int measureHeight(int pHeightMeasureSpec) {
        int result = 0;
        int heightMode = MeasureSpec.getMode(pHeightMeasureSpec);
        int heightSize = MeasureSpec.getSize(pHeightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED:
                result = getSuggestedMinimumHeight();
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = heightSize;
                break;
        }
        return result;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 遍历所有子视图
        int posLeft = HorInterval;
        int posTop = VerInterval;
        int posRight;
        int posBottom;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            int measureHeight = childView.getMeasuredHeight();
            int measuredWidth = childView.getMeasuredWidth();
            if (posLeft + getNextHorLastPos(i) > viewWidth) {
                posLeft = HorInterval;
                posTop += (measureHeight + VerInterval);
            }
            posRight = posLeft + measuredWidth;
            posBottom = posTop + measureHeight;
            childView.layout(posLeft, posTop, posRight, posBottom);
            posLeft += (measuredWidth + HorInterval);
        }
    }

    private int getViewHeight() {
        int viewwidth = HorInterval;
        int viewheight = VerInterval;
        if (getChildCount() > 0) {
            viewheight = getChildAt(0).getMeasuredHeight() + VerInterval;
        }
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            int measureHeight = childView.getMeasuredHeight();
            int measuredWidth = childView.getMeasuredWidth();
            if (viewwidth + getNextHorLastPos(i) > viewWidth) {
                viewwidth = HorInterval;
                viewheight += (measureHeight + VerInterval);
            } else {
                viewwidth += (measuredWidth + HorInterval);
            }
        }
        return viewheight;
    }

    private int getNextHorLastPos(int i) {
        return getChildAt(i).getMeasuredWidth() + HorInterval;
    }

    private OnGroupItemClickListenerSix onGroupItemClickListenerSix;
    private OnGroupItemClickListenerFive onGroupItemClickListenerFive;
    private OnGroupItemClickListenerFour onGroupItemClickListenerFour;
    private OnGroupItemClickListenerOne onGroupItemClickListenerOne;
    private OnGroupItemClickListenerTwo onGroupItemClickListenerTwo;
    private OnGroupItemClickListenerThree onGroupItemClickListenerThree;

    public void setOnGroupItemClickListenerSix(OnGroupItemClickListenerSix listener) {
        onGroupItemClickListenerSix = listener;
        for (int i = 0; i < getChildCount(); i++) {
            final X childView = (X) getChildAt(i);
            final int itemPos = i;
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onGroupItemClickListenerSix.onGroupItemClickSix(itemPos);
                    chooseItemStyle(itemPos);
                }
            });
        }
    }

    public void setGroupClickListenerFive(OnGroupItemClickListenerFive listener) {
        onGroupItemClickListenerFive = listener;
        for (int i = 0; i < getChildCount(); i++) {
            final X childView = (X) getChildAt(i);
            final int itemPos = i;
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onGroupItemClickListenerFive.onGroupItemClickFive(itemPos);
                    chooseItemStyle(itemPos);
                }
            });
        }
    }

    public void setGroupClickListenerFour(OnGroupItemClickListenerFour listener) {
        onGroupItemClickListenerFour = listener;
        for (int i = 0; i < getChildCount(); i++) {
            final X childView = (X) getChildAt(i);
            final int itemPos = i;
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onGroupItemClickListenerFour.onGroupItemClickFour(itemPos);
                    chooseItemStyle(itemPos);
                }
            });
        }
    }

    public void setGroupClickListenerThree(OnGroupItemClickListenerThree listener) {
        onGroupItemClickListenerThree = listener;
        for (int i = 0; i < getChildCount(); i++) {
            final X childView = (X) getChildAt(i);
            final int itemPos = i;
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onGroupItemClickListenerThree.onGroupItemClickThree(itemPos);
                    chooseItemStyle(itemPos);
                }
            });
        }
    }

    public void setGroupClickListenerTwo(OnGroupItemClickListenerTwo listener) {
        onGroupItemClickListenerTwo = listener;
        for (int i = 0; i < getChildCount(); i++) {
            final X childView = (X) getChildAt(i);
            final int itemPos = i;
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onGroupItemClickListenerTwo.onGroupItemClickTwo(itemPos);
                    chooseItemStyle(itemPos);
                }
            });
        }
    }

    public void setGroupClickListenerOne(OnGroupItemClickListenerOne listener) {
        onGroupItemClickListenerOne = listener;
        for (int i = 0; i < getChildCount(); i++) {
            final X childView = (X) getChildAt(i);
            final int itemPos = i;
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onGroupItemClickListenerOne.onGroupItemClickOne(itemPos);
                    chooseItemStyle(itemPos);
                }
            });
        }
    }

    //选中那个的样式
    public void chooseItemStyle(int pos) {
        clearItemsStyle();
        if (pos < getChildCount()) {
            X childView = (X) getChildAt(pos);
            childView.setBackgroundResource(itemBGResPre);
            childView.setTextColor(itemTextColorPre);
            setItemPadding(childView);
        }
    }

    private void setItemPadding(X view) {
        if (view instanceof Button) {
            view.setPadding(textModePadding, 0, textModePadding, 0);
        } else {
            view.setPadding(textModePadding, textModePadding, textModePadding, textModePadding);
        }
    }

    //清除Group所有的样式
    private void clearItemsStyle() {
        for (int i = 0; i < getChildCount(); i++) {
            X childView = (X) getChildAt(i);
            childView.setBackgroundResource(itemBGResNor);
            childView.setTextColor(itemTextColorNor);
            setItemPadding(childView);
        }
    }

    public void addItemViews(ArrayList<String> texts, String mode) {
        mTexts = texts;
        removeAllViews();
        for (String text : texts) {
            addItemView(text, mode);
        }
    }

    private void addItemView(String text, String mode) {
        X childView = null;
        switch (mode) {
            case BTN_MODE:
                childView = (X) new Button(mContext);
                break;
            case TEV_MODE:
                childView = (X) new TextView(mContext);
                break;
        }
        childView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        childView.setTextSize(itemTextSize);
        childView.setBackgroundResource(itemBGResNor);
        setItemPadding(childView);
        childView.setTextColor(itemTextColorNor);
        childView.setText(text);
        this.addView(childView);
    }

    public String getChooseText(int itemID) {
        if (itemID >= 0) {
            return mTexts.get(itemID);
        }
        return null;
    }

    public interface OnGroupItemClickListenerOne {
        void onGroupItemClickOne(int item);
    }

    public interface OnGroupItemClickListenerTwo {
        void onGroupItemClickTwo(int item);
    }

    public interface OnGroupItemClickListenerThree {
        void onGroupItemClickThree(int item);
    }

    public interface OnGroupItemClickListenerSix {
        void onGroupItemClickSix(int item);
    }


    public interface OnGroupItemClickListenerFive {
        void onGroupItemClickFive(int item);
    }

    public interface OnGroupItemClickListenerFour {
        void onGroupItemClickFour(int item);
    }
}

