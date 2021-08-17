package activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmarket.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import fragment.AppFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private TextView tvUsername;
    private NavigationView navigationView;
    private String username;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private List<AppFragment> fragments = new ArrayList<>();
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String[] title = new String[]{"游戏", "交友", "直播", "金融"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = getIntent().getStringExtra("username");

        initViews();
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.dr);
        toolbar = findViewById(R.id.tb);
        navigationView = findViewById(R.id.nv);
        viewPager = findViewById(R.id.vp);
        tabLayout = findViewById(R.id.tab_layout);
        tvUsername = navigationView.getHeaderView(0).findViewById(R.id.tv_username);
        tvUsername.setText(username);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayShowTitleEnabled(false);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        AppFragment appFragment1 = AppFragment.newInstance(1);
        AppFragment appFragment2 = AppFragment.newInstance(2);
        AppFragment appFragment3 = AppFragment.newInstance(3);
        AppFragment appFragment4 = AppFragment.newInstance(4);
        fragments.add(appFragment1);
        fragments.add(appFragment2);
        fragments.add(appFragment3);
        fragments.add(appFragment4);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.option1:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option2:
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                break;
            default: break;
        }
        return true;
    }
}