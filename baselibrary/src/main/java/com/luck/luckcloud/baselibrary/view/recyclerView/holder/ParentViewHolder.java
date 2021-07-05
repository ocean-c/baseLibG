package com.luck.luckcloud.baselibrary.view.recyclerView.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.luck.luckcloud.baselibrary.view.recyclerView.ItemClickListener;


/**
 * author: fa
 * date: 2017/11/3 14:12.
 */

public class ParentViewHolder extends RecyclerView.ViewHolder {


    public ParentViewHolder(View itemView) {
        super(itemView);
    }


    public void setParentClickListener(ItemClickListener itemClickListener) {
        itemView.setOnClickListener(new MyClickListener(itemClickListener));
    }


    /**
     * 含有子项的父布局的点击事件
     */
    private class MyClickListener implements View.OnClickListener {
        private ItemClickListener itemClickListener;

        public MyClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
//                if (dataBean.isExpand()) {
//                    itemClickListener.onHideChildren(dataBean);
//                    parentDashedView.setVisibility(View.VISIBLE);
//                    dataBean.setExpand(false);
//                    rotationExpandIcon(90, 0);
//                } else {
//                    itemClickListener.onExpandChildren(dataBean);
//                    parentDashedView.setVisibility(View.INVISIBLE);
//                    dataBean.setExpand(true);
//                    rotationExpandIcon(0, 90);
//                }
            }
        }
    }

}
