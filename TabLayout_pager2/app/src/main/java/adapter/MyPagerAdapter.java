package adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tablayout_pager.FragmentOne;
import com.example.tablayout_pager.FragmentThree;
import com.example.tablayout_pager.FragmentTwo;
import com.example.tablayout_pager.MainActivity;

public class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentOne();
                break;
            case 1:
                fragment = new FragmentTwo();
                break;
            case 2:
                fragment = new FragmentThree();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return MainActivity.TAB_COUNT;
    }}