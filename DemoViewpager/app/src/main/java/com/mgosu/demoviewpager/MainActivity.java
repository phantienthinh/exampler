package com.mgosu.demoviewpager;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Model> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentPagerItems pages = new FragmentPagerItems(this);
        for (int i = 0; i < 5; i++) {
            list.add(new Model("aaa",BlankFragment.newInstance(i)));
        }
        SmartTabLayout layout = findViewById(R.id.tabLayout);

        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), FragmentPagerItems.with(this).create(), list);
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        layout.setViewPager(viewPager);

    }

    class MyAdapter extends FragmentPagerItemAdapter {
        private List<Model> list;

        public MyAdapter(FragmentManager fm, FragmentPagerItems pages, List<Model> list) {
            super(fm, pages);
            this.list = list;
            Log.d("///////////","siz 1  "+list.size());
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position).getFragment();
        }

        @Override
        public int getCount() {
            Log.d("///////////","siz "+list.size());
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position).getTitle();
        }
    }
}
