package com.lianhechuangxin.jetpackmvvm.ui.adapter;

import androidx.core.view.GravityCompat;
import androidx.databinding.BindingAdapter;
import androidx.drawerlayout.widget.DrawerLayout;

public class DrawerBindingAdapter {

    @BindingAdapter(value = {"isOpenDrawer"},requireAll = false)
    public static void openDrawer(DrawerLayout drawerLayout,boolean isOpen) {
        if (isOpen && !drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.openDrawer(GravityCompat.START);
        }else {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}
