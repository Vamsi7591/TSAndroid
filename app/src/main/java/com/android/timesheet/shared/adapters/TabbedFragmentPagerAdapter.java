package com.android.timesheet.shared.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.shared.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vamsikonanki on 1/10/2017.
 */

public class TabbedFragmentPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();

    private final List<TabInfo> mTabInfo = new ArrayList<>();

    public TabbedFragmentPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    public void clear() {
        mFragmentList.clear();

        notifyDataSetChanged();
    }

    public void addFragment(BaseFragment fragment, int titleResId, int iconResId, int activeIconResId) {
        mFragmentList.add(fragment);

        mTabInfo.add(new TabInfo(titleResId, iconResId, activeIconResId));

        notifyDataSetChanged();
    }

    @Override
    public BaseFragment getItem(int position) {
        return (BaseFragment) mFragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getItem(position).getPageTitle();
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public List<TabInfo> getAllTabs() {
        return mTabInfo;
    }

    public View getTabView(Context context, int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.base_tabs_customtab, null);

        ImageView img = (ImageView) v.findViewById(R.id.icon);
        img.setImageResource(mTabInfo.get(position).icon);

        TextView tv = (TextView) v.findViewById(R.id.title);
        tv.setText(mTabInfo.get(position).title);
        tv.setVisibility(View.GONE);

        return v;
    }


    public static class TabInfo {

        public int title;

        public int icon;

        public int activeIcon;

        public TabInfo(int title, int icon, int activeIcon) {
            this.title = title;
            this.icon = icon;
            this.activeIcon = activeIcon;
        }
    }
}

