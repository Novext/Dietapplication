package com.novext.dietapp.FragmentNavigationDrawer;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.novext.dietapp.FragmentHomePage.ChatFragment;
import com.novext.dietapp.FragmentHomePage.DietFragment;
import com.novext.dietapp.FragmentHomePage.ExercisesFragment;
import com.novext.dietapp.FragmentHomePage.FriendsFragment;
import com.novext.dietapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class HomeFragment extends Fragment {

    private AppBarLayout AppBarHome;
    private TabLayout TabHome;
    ViewPager viewPage;
    CollapsingToolbarLayout collpaser;

    public HomeFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        if (savedInstanceState == null){
            AddTabs(container);
            viewPage  = (ViewPager) view.findViewById(R.id.pageViewHome);
            //collpaser = (CollapsingToolbarLayout) view.findViewById(R.id.IdCollapsingHome);
            //collpaser.setTitle(getString(R.string.app_name));
            AddViewPager(viewPage);
            TabHome.setupWithViewPager(viewPage);
        }
        return view;
    }
    private void AddViewPager(ViewPager viewPage){
        AdapterSection adapter = new AdapterSection(getFragmentManager());
        adapter.AddFragment(new DietFragment(),getString(R.string.TitleDietHomeFragment));
        adapter.AddFragment(new ExercisesFragment(),getString(R.string.TitleExercisesHomeFragment));
        adapter.AddFragment(new FriendsFragment(),getString(R.string.TitleFriendsHomeFragment));
        adapter.AddFragment(new ChatFragment(),getString(R.string.TitleChatHomeFragment));

        viewPage.setAdapter(adapter);
    }
    private void AddTabs(ViewGroup container){
        View father = (View) container.getParent();
        AppBarHome = (AppBarLayout) father.findViewById(R.id.AppBar);
        TabHome = new TabLayout(getActivity());
        TabHome.setTabTextColors(Color.parseColor("#FFFFFF"),Color.parseColor("#FFFFFF"));
        AppBarHome.addView(TabHome);
    }
    public void onDestroyView(){
        super.onDestroyView();
        AppBarHome.removeView(TabHome);
    }
    public class AdapterSection extends FragmentStatePagerAdapter{
        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> titleFragments = new ArrayList<>();

        public AdapterSection(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
        @Override
        public int getCount() {
            return fragments.size();
        }
        public void AddFragment(Fragment fragment, String title){
            fragments.add(fragment);
            titleFragments.add(title);
        }
        public CharSequence getPageTitle(int position){
            return titleFragments.get(position);
        }
    }
}

/*
 Drawable image = getContext().getResources().getDrawable(imageResId[position]);
            image.setBounds(0,0,image.getIntrinsicWidth(),image.getIntrinsicHeight());

            SpannableString sb = new SpannableString("   "+ titleFragments.get(position));
            ImageSpan imageSpan = new ImageSpan(image,ImageSpan.ALIGN_BOTTOM);
            sb.setSpan(imageSpan,0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

*/