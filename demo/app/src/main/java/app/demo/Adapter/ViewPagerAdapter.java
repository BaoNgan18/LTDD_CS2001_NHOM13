package app.demo.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import app.demo.Fragment.AccountFragment;
import app.demo.Fragment.HomeFragment;
import app.demo.Fragment.LibraryFragment;
import app.demo.Fragment.PostFragment;
import app.demo.Fragment.SearchFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new LibraryFragment();
            case 2:
                return new PostFragment();
            case 3:
                return new AccountFragment();
            case 4:
                return new SearchFragment();
            default:
                return new HomeFragment();
        }}

    @Override
    public int getItemCount() {
        return 5;
    }
}
