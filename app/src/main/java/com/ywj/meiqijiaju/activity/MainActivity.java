package com.ywj.meiqijiaju.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ywj.meiqijiaju.R;
import com.ywj.meiqijiaju.fragment.FragmenHome;
import com.ywj.meiqijiaju.fragment.FragmenMy;
import com.ywj.meiqijiaju.fragment.FragmenSearch;
import com.ywj.meiqijiaju.fragment.FragmenShop;
import com.ywj.meiqijiaju.wight.ShopToolbar;

/**
 * 用到了radiogroup及radioButton
 */
public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    ShopToolbar toolbar;
    private RadioGroup group;
    private RadioButton main_home_btn, main_search_btn, main_my_btn, main_shop_btn;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpager);
        initView();
        main_home_btn.setChecked(true);
        fm = getSupportFragmentManager();
        group.setOnCheckedChangeListener(this);
        //切换
        changeFragment(new FragmenHome(), false);
    }

    private void initView() {
        group = (RadioGroup) findViewById(R.id.main_bottom_tabs);
        main_home_btn = (RadioButton) findViewById(R.id.main_home);
        main_shop_btn = (RadioButton) findViewById(R.id.main_shop);
        main_search_btn = (RadioButton) findViewById(R.id.main_search);
        main_my_btn = (RadioButton) findViewById(R.id.main_my);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.main_home:
                changeFragment(new FragmenHome(), false);
                toolbar.setTitle(R.string.home);
                break;
            case R.id.main_shop:
                changeFragment(new FragmenShop(), false);
                //toolbar.setTitle(R.string.shop);

                break;
            case R.id.main_search:
                changeFragment(new FragmenSearch(), false);
                toolbar.setTitle(R.string.search);
                break;
            case R.id.main_my:
                changeFragment(new FragmenMy(), false);
                toolbar.setTitle(R.string.my);
                break;
        }

    }

    //切换不同的Fragment
    public void changeFragment(Fragment fragment, boolean isInit) {
        //开启事务
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.main_content, fragment);
        if (!isInit) {
            //添加到回退栈   避免多个碎片重叠的效果
            ft.addToBackStack(null);
        }
        ft.commit();
    }
}
