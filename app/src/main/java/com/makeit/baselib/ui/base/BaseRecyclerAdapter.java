/*
 * Copyright ©  2017-present  美契.  All Rights Reserved.
 *
 * 美契信息公司 版权所有
 */

package com.makeit.baselib.ui.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;


/**
 * Created by chenfeng on 2017/6/12.
 * <p>
 * 用途：RecyclerView.Adapter的适配器基础封装
 */

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder> {

    protected OnClickItemListener     mItemListener;
    protected OnLongClickItemListener mLongClickItemListener;

    public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        public BaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (mItemListener != null) {
                mItemListener.onClickItem(view, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (mLongClickItemListener != null) {

                return mLongClickItemListener.onLongClickItem(view, getAdapterPosition());
            }
            return false;
        }
    }

     public abstract void setOnClickItemListener(OnClickItemListener listener);

    public abstract void setOnLongClickItemListener(OnLongClickItemListener longClickItemListener);


    public interface OnClickItemListener {
        /**
         * @param view
         * @param position 注：此处position 由于XRecyclerview 有头部刷新布局，因此数据的position要相对的减去头部刷新布局数和 头部Header布局数
         */
        void onClickItem(View view, int position);
    }

    public interface OnLongClickItemListener {
        /**
         * @param view
         * @param position 注：此处position 由于XRecyclerview 有头部刷新布局，因此数据的position要相对的减去头部刷新布局数和 头部Header布局数
         * @return
         */
        boolean onLongClickItem(View view, int position);
    }
}
