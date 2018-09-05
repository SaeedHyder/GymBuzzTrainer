package com.app.gymbuztrainer.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.app.gymbuztrainer.R;
import com.app.gymbuztrainer.fragments.abstracts.BaseFragment;
import com.app.gymbuztrainer.global.AppConstants;
import com.app.gymbuztrainer.ui.views.TitleBar;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    //Unbinder unbinder;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;

    int current_position = 0;

    TitleBar titleBar;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(HomeMenuFragment.newInstance(), "");
        adapter.addFragment(SettingFragment.newInstance(), "");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(0);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(this);

        if (getArguments() != null) {
            current_position = getArguments().getInt(AppConstants.tabPosition);
        }

        viewPager.setCurrentItem(current_position);

        return view;
    }

    private void updateTabIcons(int current_position) {

        TextView tabOne = (TextView) tabLayout.getTabAt(0).getCustomView();

        if(tabOne != null) {
            if (current_position == 0) {
                tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home, 0, 0);
                titleBar.hideTitleBar();
            } else {
                tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home2, 0, 0);
            }

            TextView tabTwo = (TextView) tabLayout.getTabAt(1).getCustomView();

            if (current_position == 1) {

                titleBar.showTitleBar();
                titleBar.hideButtons();
                titleBar.setSubHeading(getString(R.string.settings));

                tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.settings2, 0, 0);
            } else {
                tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.settings, 0, 0);
            }
        }

        //updateTittleBar(current_position);
    }

    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(getDockActivity()).inflate(R.layout.custom_tab, null).findViewById(R.id.tab);
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabFive = (TextView) LayoutInflater.from(getDockActivity()).inflate(R.layout.custom_tab, null).findViewById(R.id.tab);
        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.settings, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabFive);
    }

    private void updateTittleBar(int current_position) {

        if (current_position == 0) {
            titleBar.hideButtons();
            titleBar.showNotificationButton(0);
            titleBar.setNotificationButtonListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        else if (current_position == 1) {
            titleBar.showTitleBar();
            titleBar.hideButtons();
            titleBar.showBackButton();
            titleBar.setSubHeading(getString(R.string.settings));
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupTabIcons();
    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        this.titleBar = titleBar;

        if(tabLayout!= null && tabLayout.getSelectedTabPosition() == 0) {
            titleBar.hideTitleBar();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        updateTabIcons(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
    public void onResume() {
        super.onResume();

    }
}

