package com.example.drocchat.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.drocchat.Fragments.CallsFragment;
import com.example.drocchat.Fragments.ChatsFragment;
import com.example.drocchat.Fragments.StatusFragment;

public class FragmentAdapters extends FragmentPagerAdapter {
    public FragmentAdapters(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0 : return new ChatsFragment();
            case 1 : return new StatusFragment();
            case 2 : return new CallsFragment();
            default : return new CallsFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null ;
        if(position==0){
            title = "CHATS";
        }
        if(position==1){
            title = "STATUS";
        }
        if(position==2){
            title = "CALLS";
        }
        return title;
    }
}
