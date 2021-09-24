package com.luck.gaia.framelibrary.entity;

/**
 * RecyclerView的列表的实体基类
 * Created by fa on 2018/9/17.
 */

public class BaseListEntity {
    protected int itemType;

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_ITEM_ONE = 1;
    public static final int TYPE_ITEM_TWO = 2;
    public static final int TYPE_ITEM_THREE = 3;
    public static final int TYPE_ITEM_NO_DATA = 4;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
