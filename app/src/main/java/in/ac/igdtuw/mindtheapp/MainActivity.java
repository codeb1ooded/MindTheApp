package in.ac.igdtuw.mindtheapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final int COUNT=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViewPager();
    }

    public void setViewPager(){
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewPagerMain);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {


        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0){
                return getString(R.string.schedule);
            }else  {
                return getString(R.string.medicines);
            }
        }

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return new MainScheduleFragment();
            }

            else if(position==1){
                return new MedicinesFragment();
            }


            return null;
        }

        @Override
        public int getCount() {
            return COUNT;
        }
    }

}
