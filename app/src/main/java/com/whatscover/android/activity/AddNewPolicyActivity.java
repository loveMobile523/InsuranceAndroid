package com.whatscover.android.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whatscover.android.R;
import com.whatscover.android.fragment.AnpGeneralInfoFragment;
import com.whatscover.android.fragment.AnpMembersSelectionFragment;
import com.whatscover.android.utility.A;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

public class AddNewPolicyActivity extends BaseActivity implements FragmentManager.OnBackStackChangedListener{

    // 
    @BindView(R.id.btnNext) Button btnNext;
    @BindView(R.id.btnSubmit) Button btnSubmit;
    @BindView(R.id.pager) ViewPager mPager;

    // ---------------------------------------------------------------------------------------------
    @BindViews({ R.id.imgSlideSelectGeneral, R.id.imgSlideSelectMembers }) List<ImageView> imageSelectTag;
    @BindViews({ R.id.tvGeneralInfo, R.id.tvMembersSelection }) List<TextView> tvSelectTag;
    @BindViews({ R.id.liLayNext, R.id.liLaySubmit }) List<LinearLayout> liLaySelectTag;

    @BindColor(R.color.colorBreak) int colorBreak;
    @BindColor(R.color.colorSelected) int colorSelected;

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_add_new_policy);

        btnNext.setPaintFlags(btnNext.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        btnSubmit.setPaintFlags(btnSubmit.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        // -----------------------------------------------------------------------------------------

        // Determine which type of sheet to create
        FragmentManager manager = getSupportFragmentManager();
        mPager.setAdapter(new AddNewPolicyActivity.InstallAdapter(manager));


        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetWidget();
                selectedPageDisplay(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_add_new_policy;
    }


    @OnClick(R.id.reLayGeneralInfo)
    public void onClickGeneralInfo() {
        mPager.setCurrentItem(0);
    }

    @OnClick(R.id.reLayMembersSelection)
    public void onClickMembersSelection() {
        mPager.setCurrentItem(1);
    }

    @OnClick(R.id.btnNext)
    public void onClickNext() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnNext.getWindowToken(), 0);
        mPager.setCurrentItem(1);
    }

    @OnClick(R.id.btnSubmit)
    public void onClickSubmit() {
        A.goOtherActivity(context, PolicyMakeDetailsActivity.class);
    }

    private void resetWidget() {
        for (int i = 0; i < NUM_PAGES; i++)
            imageSelectTag.get(i).setBackgroundResource(R.drawable.ic_select_off);

        for (int i = 0; i < NUM_PAGES; i++)
            tvSelectTag.get(i).setTextColor(colorBreak);

        for (int i = 0; i < NUM_PAGES; i++)
            liLaySelectTag.get(i).setVisibility(View.GONE);
    }

    @Override
    public void onBackStackChanged() {

    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private void selectedPageDisplay (int selectedNum) {
        imageSelectTag.get(selectedNum).setBackgroundResource(R.drawable.ic_select_on);

        tvSelectTag.get(selectedNum).setTextColor(colorSelected);

        liLaySelectTag.get(selectedNum).setVisibility(View.VISIBLE);
    }

    // --------------------------------------------------------
    class InstallAdapter extends FragmentPagerAdapter {

        private final String[]      TITLES      = { "", "" };
        private final int           PAGE_COUNT  = TITLES.length;
        private ArrayList<Fragment> FRAGMENTS   = null;


        @Override
        public Fragment getItem(int pos) {
            return FRAGMENTS.get(pos);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int pos) {
            return TITLES[pos];
        }

        public InstallAdapter(FragmentManager fm) {
            super(fm);

            addFragment0(fm);
        }

        private void addFragment0(FragmentManager fm){
            FRAGMENTS = new ArrayList<Fragment>();

            Fragment frag0 = new AnpGeneralInfoFragment();
            FRAGMENTS.add(frag0);

            Fragment frag1 = new AnpMembersSelectionFragment();
            FRAGMENTS.add(frag1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
