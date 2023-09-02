package app.demo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import app.demo.API.APIService;
import app.demo.Adapter.BookAdapter;
import app.demo.Adapter.GenreAdapter;
import app.demo.Adapter.ViewPagerAdapter;
import app.demo.model.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 mViewPager;
    private BottomNavigationView mBotNav;
    private Toolbar mToolBar;
    SearchView searchView;
    List<Book> listBook;
    RecyclerView rcvResult;
    BookAdapter bookAdapter;
    GenreAdapter genreAdapter;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //thiết lập các thanh top bar và bottom bar
        mViewPager = findViewById(R.id.view_pager);
        mBotNav = findViewById(R.id.bottom_nav);
        mToolBar = findViewById(R.id.top_nav);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(R.drawable.r);
        listBook = new ArrayList<>();
        getAllBook();

//        rcvResult = findViewById(R.id.rcv_search);
//        rcvResult.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
//        listBook = new ArrayList<>();
//        getAllBook();


        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        mViewPager.setAdapter(adapter);

        mBotNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_home) {
                    mViewPager.setCurrentItem(0);
                } else if (id == R.id.menu_lib) {
                    mViewPager.setCurrentItem(1);
                } else if (id == R.id.menu_create) {
                    mViewPager.setCurrentItem(2);
                } else if (id == R.id.menu_account) {
                    mViewPager.setCurrentItem(4);
                } else if (id == R.id.menu_search) {
                    mViewPager.setCurrentItem(3);
                }
                return true;}
        });

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        mBotNav.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        mBotNav.getMenu().findItem(R.id.menu_lib).setChecked(true);
                        break;
                    case 2:
                        mBotNav.getMenu().findItem(R.id.menu_create).setChecked(true);
                        break;
                    case 4:
                        mBotNav.getMenu().findItem(R.id.menu_account).setChecked(true);
                        break;
                    case 3:
                        mBotNav.getMenu().findItem(R.id.menu_search).setChecked(true);
                        break;
                    default:
                        mBotNav.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                }
            }
        });

//
//        rcvBook = findViewById(R.id.rcv_book);
//        rcvBook.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        bookList = new ArrayList<>();
//        CallAPI();
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.img_avatar){
            mViewPager.setCurrentItem(3);
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.top_menu, menu);
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        searchView = (SearchView) mToolBar.getMenu().findItem(R.id.search).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//
//        bookAdapter = new BookAdapter(listBook);
//        rcvResult.setAdapter(bookAdapter);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                bookAdapter.filter(newText);
//                return false;
//            }
//        });
//        return true;
//    }

    public void getAllBook() {
        APIService.API_SERVICE.getListBook().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.isSuccessful()){
                    listBook.addAll(response.body());
                    Log.d("Error", String.valueOf(listBook.size()));
                }
                else Log.d("Error", "Failure");
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}