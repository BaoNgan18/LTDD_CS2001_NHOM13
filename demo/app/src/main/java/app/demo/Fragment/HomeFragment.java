package app.demo.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.demo.R;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        String title = getString(R.string.menu_home);
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        if (activity != null) {
            activity.getSupportActionBar().setTitle(title);
        }

        return view;
    }
}