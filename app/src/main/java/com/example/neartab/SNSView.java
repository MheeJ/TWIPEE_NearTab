package com.example.neartab;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.eschao.android.widget.elasticlistview.ElasticListView;
import com.eschao.android.widget.elasticlistview.LoadFooter;
import com.eschao.android.widget.elasticlistview.OnLoadListener;
import com.eschao.android.widget.elasticlistview.OnUpdateListener;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SNSView extends Fragment implements OnUpdateListener, OnLoadListener,View.OnClickListener {

    private static final String TAG = "SNS View";

    public SNSView() {
        // Required empty public constructor
    }

    @Override
    public void onUpdate() {
        Log.d(TAG, "ElasticListView: updating list view...");

        getFollowing();
    }

    @Override
    public void onLoad() {
        Log.d(TAG, "ElasticListView: loading...");

        // load 완료 알림
        mListView.notifyLoaded();
    }

    //vars
    private ArrayList<Photo> mPhotos;
    private ArrayList<Photo> mPaginatedPhotos;
    private ArrayList<String> mFollowing;
    private int recursionIterator = 0;
    //private ListView mListView;
    private ElasticListView mListView;
    private MainFeedListAdapter adapter;
    private int resultsCount = 0;
    private ArrayList<UserAccountSettings> mUserAccountSettings;
    private Button mBtnFeedSearch;
    public double latitude;
    public double longitude;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_snsview, container, false);
//        mListView = (ListView) view.findViewById(R.id.SNSListView);

        mListView = (ElasticListView) view.findViewById(R.id.SNSListView);

        mPhotos = new ArrayList<>();
//        Photo pt = new Photo();
//        Comment cm = new Comment();
//        List<Comment> comments = new ArrayList<>();
//        cm.setComment("comment");
//        cm.setDate_created("20190930");
//        cm.setUser_id("Eunwha");
//        Like like = new Like();
//        like.setUser_id("jh");
//        List<Like> likes = new ArrayList<>();
//        likes.add(like);
//        cm.setLikes(likes);
//        comments.add(cm);
//        List<Comment> cms = new ArrayList<>();
//        pt.setCaption("123");
//        pt.setComments(comments);
//        pt.setDate_created("20190930");
//        pt.setImage_path("http://tong.visitkorea.or.kr/cms/resource/16/2373216_image2_1.jpg");
//        pt.setCaption("aa");
//        pt.setLikes(likes);
//        pt.setPhoto_id("photoid");
//        pt.setTags("dummy");
//        pt.setUser_id("UserID");
//        mPhotos.add(pt);
        initListViewRefresh();
        getFollowing();

        //        setData();
//        mListView.setAdapter(adapter);

        mBtnFeedSearch = (Button)view.findViewById(R.id.button_feedSearch);
        mBtnFeedSearch.setOnClickListener(this);

        return view;
    }

    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

    }


    private void displayPhotos(){
        mPaginatedPhotos = new ArrayList<>();
        if(mPhotos != null){

            try{

                //sort for newest to oldest
                Collections.sort(mPhotos, new Comparator<Photo>() {
                    public int compare(Photo o1, Photo o2) {
                        return o2.getDate_created().compareTo(o1.getDate_created());
                    }
                });

                //한번에 10 load. 10 이상이면  10 올려서 시작
                int iterations = mPhotos.size();
                if(iterations > 10){
                    iterations = 10;
                }
//
                resultsCount = 0;
                for(int i = 0; i < iterations; i++){
                    mPaginatedPhotos.add(mPhotos.get(i));
                    resultsCount++;
                    Log.d(TAG, "displayPhotos: adding a photo to paginated list: " + mPhotos.get(i).getPhoto_id());
                }

                adapter = new MainFeedListAdapter(getActivity(), R.layout.sns_view_post, mPaginatedPhotos);
                mListView.setAdapter(adapter);

                // 업데이트 완료
                mListView.notifyUpdated();

            }catch (IndexOutOfBoundsException e){
                Log.e(TAG, "displayPhotos: IndexOutOfBoundsException:" + e.getMessage() );
            }catch (NullPointerException e){
                Log.e(TAG, "displayPhotos: NullPointerException:" + e.getMessage() );
            }
        }
    }

    public void displayMorePhotos(){
        Log.d(TAG, "displayMorePhotos: displaying more photos");

        try{

            if(mPhotos.size() > resultsCount && mPhotos.size() > 0){

                int iterations;
                if(mPhotos.size() > (resultsCount + 10)){
                    Log.d(TAG, "displayMorePhotos: there are greater than 10 more photos");
                    iterations = 10;
                }else{
                    Log.d(TAG, "displayMorePhotos: there is less than 10 more photos");
                    iterations = mPhotos.size() - resultsCount;
                }

                //add the new photos to the paginated list
                for(int i = resultsCount; i < resultsCount + iterations; i++){
                    mPaginatedPhotos.add(mPhotos.get(i));
                }

                resultsCount = resultsCount + iterations;
                adapter.notifyDataSetChanged();
            }
        }catch (IndexOutOfBoundsException e){
            Log.e(TAG, "displayPhotos: IndexOutOfBoundsException:" + e.getMessage() );
        }catch (NullPointerException e){
            Log.e(TAG, "displayPhotos: NullPointerException:" + e.getMessage() );
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_feedSearch:
                String strnlo = Double.toString(longitude);
                String strnla = Double.toString(latitude);


                break;
            default:
                break;
        }
    }

    private void initListViewRefresh(){
        mListView.setHorizontalFadingEdgeEnabled(true);
        mListView.setAdapter(adapter);
        mListView.enableLoadFooter(true)
                .getLoadFooter().setLoadAction(LoadFooter.LoadAction.RELEASE_TO_LOAD);
        mListView.setOnUpdateListener(this)
                .setOnLoadListener(this);
//        mListView.requestUpdate();
    }

    private void getFriendsAccountSettings() {
        Log.d(TAG, "getFriendsAccountSettings: getting friends account settings.");


    }

    private void clearAll(){
        if(mFollowing != null){
            mFollowing.clear();
        }
        if(mPhotos != null){
            mPhotos.clear();
            if(adapter != null){
                adapter.clear();
                adapter.notifyDataSetChanged();
            }
        }
        if(mUserAccountSettings != null){
            mUserAccountSettings.clear();
        }
        if(mPaginatedPhotos != null){
            mPaginatedPhotos.clear();
        }
        mFollowing = new ArrayList<>();
        mPhotos = new ArrayList<>();
        mPaginatedPhotos = new ArrayList<>();
        mUserAccountSettings = new ArrayList<>();
    }

    //사용자가 팔로우한 모든 사용자 ID 검색
    private void getFollowing() {
        Log.d(TAG, "getFollowing: searching for following");

        clearAll();
        //also add your own id to the list




    }

    private void getPhotos() {
        Log.d(TAG, "getPhotos: getting list of photos");

    }

     /*private void setData() {
        //Photo newPhoto = new Photo();
        List<Comment> commentsList = new ArrayList<Comment>();
        //Comment comment = new Comment();
        TypedArray profilePhoto=getResources().obtainTypedArray(R.array.profile_photo);
        String[] userName=getResources().getStringArray(R.array.user_names);
        TypedArray postedPhoto=getResources().obtainTypedArray(R.array.posted_photo);
        String[] likedUser=getResources().getStringArray(R.array.liked_user);
        String[] postedText=getResources().getStringArray(R.array.posted_text);
        String[] postedDate=getResources().getStringArray(R.array.posted_date);

        for (int i=0; i<((TypedArray) profilePhoto).length();i++){
            SNSDTO dto=new SNSDTO();
            dto.setProfilePhoto(profilePhoto.getResourceId(i,0));
            dto.setUserName(userName[i]);
            dto.setPostedPhoto(postedPhoto.getResourceId(i,2));
            dto.setLikedUser(likedUser[i]);
            dto.setPostedText(postedText[i]);
            dto.setPostedDate(postedDate[i]);

            adapter.addItem(dto);

        }

        //displayPhotos();

    }*/


}
