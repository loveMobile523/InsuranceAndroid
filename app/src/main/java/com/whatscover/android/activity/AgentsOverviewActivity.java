package com.whatscover.android.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whatscover.android.R;
import com.whatscover.android.fragment.FragmentAgentsONew;
import com.whatscover.android.fragment.FragmentAgentsOUpcoming;

import java.util.ArrayList;
import java.util.List;

import com.whatscover.android.fragment.FragmentAgentsOFollowUp;
import com.whatscover.android.model.ModelAgent;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgentsOverviewActivity extends BaseActivity implements FragmentManager.OnBackStackChangedListener{
    @BindView(R.id.pager) ViewPager mPager;

    @BindViews({ R.id.tvSlideNew, R.id.tvSlideUpcoming, R.id.tvSlideFollowUp }) List<TextView> tvSelectTag;
    @BindViews({ R.id.liLayUnderLineNew, R.id.liLayUnderLineUpcoming, R.id.liLayUnderLineFollowUp }) List<LinearLayout> liLaySelectTag;

    @BindColor(R.color.colorSelected) int colorSelected;
    public static ArrayList<ModelAgent> arrayList;
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Determine which type of sheet to create
        FragmentManager manager = getSupportFragmentManager();
        mPager.setAdapter(new AgentsOverviewActivity.InstallAdapter(manager));


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

        resetWidget();
        selectedPageDisplay(0);

        arrayList = new ArrayList<>();
        ModelAgent modelAgent;

        arrayList.clear();
        modelAgent = new ModelAgent(0, "Applicant 1", "Sam Norval", "$80,000", "12/07/16, 1:20pm", "75");
        arrayList.add(modelAgent);

        modelAgent = new ModelAgent(1, "Applicant 2", "Josh Ronale", "$100,000", "12/11/16, 5:35pm", "35");
        arrayList.add(modelAgent);

        modelAgent = new ModelAgent(2, "Applicant 3", "Amanda Fernano", "$150,000", "2/08/17, 11:10am", "15");
        arrayList.add(modelAgent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_agents_overview;
    }

    @OnClick(R.id.liLayBack)
    public void onClickBack() {
        finish();
    }

    @OnClick(R.id.reLayNew)
    public void onClickNew() {
        mPager.setCurrentItem(0);
    }

    @OnClick(R.id.reLayUpcoming)
    public void onClickUpcoming() {
        mPager.setCurrentItem(1);
    }

    @OnClick(R.id.reLayFollowUp)
    public void onClickFollowUp() {
        mPager.setCurrentItem(2);
    }
    // ---------------------------------------------------------------------------------------------

    private void resetWidget() {
        for (int i = 0; i < NUM_PAGES; i++)
            tvSelectTag.get(i).setTextColor(colorSelected);

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
        tvSelectTag.get(selectedNum).setTextColor(Color.parseColor("#8DBDD3"));

        liLaySelectTag.get(selectedNum).setVisibility(View.VISIBLE);
    }

    // --------------------------------------------------------
    class InstallAdapter extends FragmentPagerAdapter {

        private final String[]      TITLES      = { "", "", "" };
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

            Fragment frag0 = new FragmentAgentsONew();
            FRAGMENTS.add(frag0);

            Fragment frag1 = new FragmentAgentsOUpcoming();
            FRAGMENTS.add(frag1);

            Fragment frag2 = new FragmentAgentsOFollowUp();
            FRAGMENTS.add(frag2);
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
}
