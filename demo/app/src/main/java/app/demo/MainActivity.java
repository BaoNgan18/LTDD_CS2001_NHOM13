package app.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import app.demo.Adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

//    RecyclerView rcvTruyen;
//    List<Truyen> truyenList;

    private ViewPager2 mViewPager;
    private BottomNavigationView mBotNav;

    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.view_pager);
        mBotNav = findViewById(R.id.bottom_nav);
        mToolBar = findViewById(R.id.top_nav);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(R.drawable.r);



        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        mViewPager.setAdapter(adapter);

        mBotNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.menu_home) {
                    mViewPager.setCurrentItem(0);
                } else if(id == R.id.menu_lib) {
                    mViewPager.setCurrentItem(1);
                } else if (id == R.id.menu_noti) {
                    mViewPager.setCurrentItem(2);
                } else if (id == R.id.menu_account) {
                    mViewPager.setCurrentItem(3);
                } return true;
            }
        });

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        mBotNav.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        mBotNav.getMenu().findItem(R.id.menu_lib).setChecked(true);
                        break;
                    case 2:
                        mBotNav.getMenu().findItem(R.id.menu_noti).setChecked(true);
                        break;
                    case 3:
                        mBotNav.getMenu().findItem(R.id.menu_account).setChecked(true);
                        break;
                    default:
                        mBotNav.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                }
            }
        });


//        rcvTruyen = findViewById(R.id.rcv_truyen);
//        rcvTruyen.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
//
//        truyenList = new ArrayList<>();
//        CallAPI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
//    public void  CallAPI(){
//        APIService.API_SERVICE.layDSTruyen().enqueue(new Callback<List<Truyen>>() {
//            @Override
//            public void onResponse(Call<List<Truyen>> call, Response<List<Truyen>> response) {
//                truyenList.addAll(response.body());
//                TruyenAdapter truyenAdapter = new TruyenAdapter(truyenList);
//                rcvTruyen.setAdapter(truyenAdapter);
//                Toast.makeText(MainActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<List<Truyen>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "That bai", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}