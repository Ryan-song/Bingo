package com.ryan.bingo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.ryan.bingo.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Drawer drawer;
    private AccountHeader header;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        header = new AccountHeaderBuilder().withActivity(this)
                .withCompactStyle(false)
                .withHeaderBackground(R.drawable.header)
                .build();
        drawer = new DrawerBuilder().withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.news)
                                .withIcon(R.drawable.ic_news).withIdentifier(R.drawable.ic_news),
                        new PrimaryDrawerItem().withName(R.string.reading)
                                .withIcon(R.drawable.ic_reading).withIdentifier(R.drawable.ic_reading),
                        new PrimaryDrawerItem().withName(R.string.science)
                                .withIcon(R.drawable.ic_science).withIdentifier(R.drawable.ic_science),
                        new PrimaryDrawerItem().withName(R.string.video).
                                withIcon(R.drawable.ic_video).withIdentifier(R.drawable.ic_video),
                        new PrimaryDrawerItem().withName(R.string.music).
                                withIcon(R.drawable.ic_music).withIdentifier(R.drawable.ic_music),
                        new SectionDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.setting).
                                withIcon(R.drawable.ic_setting).withIdentifier(R.drawable.ic_setting),
                        new PrimaryDrawerItem().withName(R.string.about).
                                withIcon(R.drawable.ic_about).withIdentifier(R.drawable.ic_about)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch ((int) drawerItem.getIdentifier()) {
                            case R.drawable.ic_news:
                                Toast.makeText(MainActivity.this, "news", Toast.LENGTH_SHORT).show();
                                break;
                            case R.drawable.ic_reading:
                                Toast.makeText(MainActivity.this, "reading", Toast.LENGTH_SHORT).show();
                                break;
                            case R.drawable.ic_science:
                                Toast.makeText(MainActivity.this, "science", Toast.LENGTH_SHORT).show();
                                break;
                            case R.drawable.ic_video:
                                Toast.makeText(MainActivity.this, "video", Toast.LENGTH_SHORT).show();
                                break;
                            case R.drawable.ic_music:
                                Toast.makeText(MainActivity.this, "music", Toast.LENGTH_SHORT).show();
                                break;
                            case R.drawable.ic_setting:
                                Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();
                                break;
                            case R.drawable.ic_about:
                                Toast.makeText(MainActivity.this, "about", Toast.LENGTH_SHORT).show();
                                break;
                        }

                        return false;
                    }
                })
                .build();
    }
}
