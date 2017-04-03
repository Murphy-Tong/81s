package com.example.tong.a81s;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import util.PageNav;

/**
 * Created by TONG on 2016/12/30.
 */
public class PlaceHolderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private final String ARG_SECTION_NUMBER = "section_number";

    private String loadUrl;

    public PlaceHolderFragment() {

    }

    private TabLayout tabs;
    private ViewPager viewpager;
    private int sectionNumber;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public PlaceHolderFragment(int sectionNumber) {
        PlaceHolderFragment fragment = new PlaceHolderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        this.sectionNumber = sectionNumber;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        tabs = (TabLayout) getActivity().findViewById(R.id.navTabs);
        viewpager = (ViewPager) rootView.findViewById(R.id.viewpager);

        initContent();
        return rootView;
    }


    private PageViewAdapter adapter;

    public void initContent() {

        adapter = new PageViewAdapter(getChildFragmentManager(), viewpager, tabs);

 /*       tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);*/
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        viewpager.setOffscreenPageLimit(3);
        tabs.setupWithViewPager(viewpager);


    }

    private int currentItem = 0;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        Log.i("hid:",hidden+"");
        if (hidden)
            currentItem = viewpager.getCurrentItem();

        if (!hidden) {

            tabs.setupWithViewPager(viewpager);
            viewpager.setCurrentItem(currentItem);
        }

    }

    private class PageViewAdapter extends FragmentPagerAdapter implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {
        private TabLayout tableLayout;
        private ViewPager viewPager;

        public PageViewAdapter(FragmentManager fm, ViewPager viewpager, TabLayout tabLayout) {
            super(fm);
            fragments = new ArrayList<>();
            this.viewPager = viewpager;

            this.tableLayout = tabLayout;
//            tabLayout.setOnTabSelectedListener(this);
            viewpager.setAdapter(this);
//            viewpager.setOnPageChangeListener(this);
//            tabLayout.setupWithViewPager(viewpager);
        }

        private ArrayList<Fragment> fragments;

        @Override
        public Fragment getItem(int position) {
            Fragment f = null;
            if (fragments.size() > position) {
                f = fragments.get(position);
                if (f != null) {
                    return f;
                }
            }
            while (position >= fragments.size()) {
                fragments.add(null);
            }

            Bundle bundle = new Bundle();
            bundle.putString("loadUrl", PageNav.getUrls(sectionNumber)[position]);
            f = PageFragment.instantiate(viewPager.getContext(), PageFragment.class.getName(), bundle);
            if (!fragments.contains(f))
                fragments.set(position, f);
            return f;
        }


        @Override
        public int getCount() {
            return PageNav.getTitles(sectionNumber).length;
        }

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition(), true);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            tableLayout.scrollBy((int) positionOffset, 0);
        }

        @Override
        public void onPageSelected(int position) {
//            for(int i=0;i<tableLayout.getTabCount();i++)
            tableLayout.getTabAt(position).select();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position < PageNav.getTitles(sectionNumber).length)
                return PageNav.getTitles(sectionNumber)[position];
            return "";
        }
    }


}

