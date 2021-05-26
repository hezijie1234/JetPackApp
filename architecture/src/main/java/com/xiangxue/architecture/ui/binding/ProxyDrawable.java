package com.xiangxue.architecture.ui.binding;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

// 同学们，此类只给 Drawables 服务哦
public class ProxyDrawable extends StateListDrawable {

    private Drawable originDrawable;

    @Override
    public void addState(int[] stateSet, Drawable drawable) {
        if (stateSet != null && stateSet.length == 1 && stateSet[0] == 0) {
            originDrawable = drawable;
        }
        super.addState(stateSet, drawable);
    }

    Drawable getOriginDrawable() {
        return originDrawable;
    }
}
