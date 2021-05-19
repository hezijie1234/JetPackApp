package com.example.myapplication.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.example.myapplication.R;

/**
 * 参考文档：https://mp.weixin.qq.com/s?src=11&timestamp=1621404042&ver=3077&signature=QdkfW*oS1NyRAm2dEiSfvs7SAku3gt3mIfDAz2SStVYcLSZ7YVmSk1sH*IEkF-WDsShpFc8pVPX9okx-uFePQzj2Du9Wvb24ZVasceXgi4bEISYiI6I90LQvk-EKK3e6&new=1
 */
public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }

    /**
     * 根据官方文档说明，为了保证导航的正确使用，我们需要在目标的Activity中重写onSupportNavigateUp，以确保导航器能够正确的回退栈。
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);
        return NavHostFragment.findNavController(fragment).navigateUp();
    }


}