package com.gameto.recyclerviewflow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by Administrator on 2016/11/20.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDrawable;

    public DividerItemDecoration(Context context) {
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{android.R.attr.listDivider});
        mDrawable = typedArray.getDrawable(0);
        typedArray.recycle();
    }

    public int getSapnCount(RecyclerView parent) {
        return ((StaggeredGridLayoutManager) parent.getLayoutManager()).getSpanCount();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin
                    + mDrawable.getIntrinsicWidth();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDrawable.getIntrinsicWidth();

            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        int orientation = ((StaggeredGridLayoutManager) layoutManager)
                .getOrientation();
        if (orientation == StaggeredGridLayoutManager.VERTICAL) {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        } else {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                return true;
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                              int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int orientation = ((StaggeredGridLayoutManager) layoutManager)
                .getOrientation();
        // StaggeredGridLayoutManager 且纵向滚动
        if (orientation == StaggeredGridLayoutManager.VERTICAL) {
            childCount = childCount - childCount % spanCount;
            // 如果是最后一行，则不需要绘制底部
            if (pos >= childCount)
                return true;
        } else
        // StaggeredGridLayoutManager 且横向滚动
        {
            // 如果是最后一行，则不需要绘制底部
            if ((pos + 1) % spanCount == 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition,
                               RecyclerView parent) {
        int spanCount = getSapnCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
        {
            outRect.set(0, 0, mDrawable.getIntrinsicWidth(), 0);
        } else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
        {
            outRect.set(0, 0, 0, mDrawable.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDrawable.getIntrinsicWidth(),
                    mDrawable.getIntrinsicHeight());
        }
    }

}
