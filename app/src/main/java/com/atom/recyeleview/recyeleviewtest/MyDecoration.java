package com.atom.recyeleview.recyeleviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by User on 2016/4/11.
 */
public class MyDecoration extends RecyclerView.ItemDecoration {

    // 定义画笔
    private Paint mPaint;
    private Drawable mDiv;
    private int mDivHeight = 2; // 分割线高度，默认为1
    private int mOrientation ; // 列表方向。LinearLayoutManager.VERTICAL或LinearLayoutManager.HORIZONTAL
    private static final int[] ARR = new int[]{android.R.attr.listDivider};

    public MyDecoration() {
        super();
    }

    /**
     *  默认分割线：高度为2px,颜色为灰色
     * @param context
     * @param orientation
     */
    public MyDecoration(Context context, int orientation) {

        if(orientation != LinearLayoutManager.HORIZONTAL && orientation != LinearLayoutManager.VERTICAL){
            throw  new IllegalArgumentException("请输入正确的参数");
        }
        mOrientation = orientation;
        final TypedArray array = context.obtainStyledAttributes(ARR);
        mDiv = array.getDrawable(0);
        array.recycle();
    }

    /**
     * 自定义分割线
     * @param context
     * @param orientation      列表方向
     * @param dividerHeight    分割线高度
     * @param dividerColor     分割线颜色
     */
    public MyDecoration(Context context, int orientation,int dividerHeight, int dividerColor) {
        this(context,orientation);
        mDivHeight = dividerHeight;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(dividerColor);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if(mOrientation == LinearLayoutManager.VERTICAL){
            drawVertical(c,parent);
        }else{
            drawHorzontal(c,parent);
        }
    }

    /**
     * 绘制横向分割线
     * @param c
     * @param parent
     */
    private void drawHorzontal(Canvas c, RecyclerView parent) {

        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++){
            final View child= parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bootom = top + mDivHeight;
            if(mDiv != null){
                mDiv.setBounds(left,top,right,bootom);
                mDiv.draw(c);
            }
            if(mPaint != null){
                c.drawRect(left,top,right,bootom,mPaint);
            }
        }
    }

    /**
     * 绘制纵向分割线
     * @param c
     * @param parent
     */
    private void drawVertical(Canvas c, RecyclerView parent) {

        final int top = parent.getPaddingTop();
        final int bootom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++){
            final View child= parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + mDivHeight;
            if(mDiv != null){
                mDiv.setBounds(left,top,right,bootom);
                mDiv.draw(c);
            }
            if(mPaint != null){
                c.drawRect(left,top,right,bootom,mPaint);
            }
        }
    }

    /**
     * 获取分割线尺寸
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0,0,0,mDivHeight);
    }
}
