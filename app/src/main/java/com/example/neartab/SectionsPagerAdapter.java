package com.example.neartab;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

//
    public SNSView mSNSView = null;
    public TripTalkView mTripTalkView = null;
    public NearFacilityView mNearFacilityView = null;
    public UserInfoView mUserInfoView = null;
    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch(position) {
            case 0:
                return mSNSView = new SNSView();
            case 1:
                return mTripTalkView = new TripTalkView();
            case 2:
                return mNearFacilityView = new NearFacilityView();
            case 3:
                return mUserInfoView = new UserInfoView();
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        // Show 2 total pages.
        return 4;
    }
}