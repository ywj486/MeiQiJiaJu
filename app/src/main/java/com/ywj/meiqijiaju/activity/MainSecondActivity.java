package com.ywj.meiqijiaju.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.ywj.meiqijiaju.R;
import com.ywj.meiqijiaju.entity.Tab;
import com.ywj.meiqijiaju.fragment.FragmenHome;
import com.ywj.meiqijiaju.fragment.FragmenMy;
import com.ywj.meiqijiaju.fragment.FragmenSearch;
import com.ywj.meiqijiaju.fragment.FragmenShop;
import com.ywj.meiqijiaju.wight.FragmentTabHost;
import com.ywj.meiqijiaju.wight.ShopToolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/11 0011.
 */

public class MainSecondActivity extends AppCompatActivity {
    ShopToolbar shopToolbar;
    FragmentTabHost mTabhost;
    private LayoutInflater mInflater;
    private List<Tab> mTabs = new ArrayList<>(4);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInflater = LayoutInflater.from(this);
        initTab();
    }

    private void initTab() {
        Tab tab_home = new Tab(FragmenHome.class, R.drawable.main_botom_icon_select, R.string.home);
        Tab tab_shop = new Tab(FragmenShop.class, R.drawable.tuan_botom_icon_select, R.string.shop);
        Tab tab_search = new Tab(FragmenSearch.class, R.drawable.search_botom_icon_select, R.string.search);
        Tab tab_my = new Tab(FragmenMy.class, R.drawable.my_botom_icon_select, R.string.my);

        mTabs.add(tab_home);
        mTabs.add(tab_shop);
        mTabs.add(tab_search);
        mTabs.add(tab_my);

         mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        for (Tab tab : mTabs) {
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec, tab.getFragment(), null);
        }
        mTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                                             @Override
                                             public void onTabChanged(String tabId) {
                                                 //如果是购物车  刷新数据，
                                                 // 否则导航条显示搜索框隐藏头部信息
//                                                 if (tabId.equals(getString(R.string.cart))) {
//                                                     refData();
//                                                 } else {
//
//                                                     shopToolbar.hideTitleView();
//                                                 }
                                                 shopToolbar.showTitleView();
                                             }
                                         }

        );
        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        //设置当前页为第一页
        mTabhost.setCurrentTab(0);
    }

    //构建选项卡
    private View buildIndicator(Tab tab) {
        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);
        img.setImageResource(tab.getIcon());
        text.setText(tab.getTitle());
        return view;
    }
}
