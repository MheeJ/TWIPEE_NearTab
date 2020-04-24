package com.example.neartab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    public String[] mUserInfos;
    public ArrayList<String> mKeywordStrArray;
    SectionsPagerAdapter sectionsPagerAdapter = null;
    public TripTalkBoard mTripTalkBoard = null;

    private static final String TAG = "MainActivity";
    private static final Boolean CHECK_IF_VERIFIED = false;
    private int[] tabIcons={
            R.drawable.ic_web_black_24dp,
            R.drawable.ic_chat_bubble_outline_black_24dp,
            R.drawable.ic_thumb_up_black_24dp,
            R.drawable.ic_account_box_black_24dp
    };

    private Context mContext = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        for (int i = 0; i < sectionsPagerAdapter.getCount(); i++)
        {
            tabs.getTabAt(i).setIcon(tabIcons[i]);
        }

        if(getIntent().getExtras() != null){
            intent = getIntent();
            mUserInfos = new String[4];
            mUserInfos = intent.getExtras().getStringArray("LoginInfo");
            mKeywordStrArray = intent.getExtras().getStringArrayList("KeywordStrArray");
            intent = null;
        }

    }


/*    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }*/

    public void hideLayout(){
        Log.d(TAG, "hideLayout: hiding layout");
//        mRelativeLayout.setVisibility(View.GONE);
//        mFrameLayout.setVisibility(View.VISIBLE);
    }

    public void onCommentThreadSelected(ContactsContract.Contacts.Photo photo, String callingActivity){
        Log.d(TAG, "onCommentThreadSelected: selected a coemment thread");

     /*   ViewCommentsFragment fragment  = new ViewCommentsFragment();
        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.photo), photo);
        args.putString(getString(R.string.home_activity), getString(R.string.home_activity));
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(getString(R.string.view_comments_fragment));
        transaction.commit();*/

    }
}
