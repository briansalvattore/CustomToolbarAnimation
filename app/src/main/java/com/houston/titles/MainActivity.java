package com.houston.titles;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.houston.titles.adapters.MainViewPagerAdapter;
import com.houston.titles.commons.Methods;
import com.houston.titles.helpers.ToolbarColorizeHelper;
import com.houston.titles.listeners.SimpleAnimatorListener;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    @InjectView(R.id.toolbar)           Toolbar toolbar;
    @InjectView(R.id.title)             TextView title;
    @InjectView(R.id.title_hide)        TextView title_hide;

    @InjectView(R.id.circleIndicator)   CircleIndicator circleIndicator;
    @InjectView(R.id.viewpager)         ViewPager viewpager;

    private int oldPosition = 0;
    private static final int BASE = Methods.toPixels(48);
    private static final int DURATION = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 450 : 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        title_hide.animate().y(BASE).setDuration(0);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ToolbarColorizeHelper.colorizeToolbar(toolbar, R.color.white, this);


        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        circleIndicator.setViewPager(viewpager);

        viewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onPageSelected(int position) {

                switch (position){
                    case 0:

                        setBackgroundColor(R.color.primary_green, R.color.primary_dark_green);
                        break;
                    case 1:

                        setBackgroundColor(R.color.primary, R.color.primary_dark);
                        break;
                    case 2:

                        setBackgroundColor(R.color.primary_dark_purple, R.color.primary_dark_purple);
                        break;
                }

                if(oldPosition > position){

                    title.animate().y(0).setDuration(0).setListener(new SimpleAnimatorListener() {

                        @Override
                        public void onAnimationEnd(Animator animation) {

                            title.animate().y(BASE).setDuration(DURATION).setListener(new SimpleAnimatorListener() {

                                @Override
                                public void onAnimationEnd(Animator animation) {

                                    title.animate().y(0).setDuration(0);
                                }
                            });
                        }
                    });

                    title_hide.animate().y(-BASE).setDuration(0).setListener(new SimpleAnimatorListener() {

                        @Override
                        public void onAnimationEnd(Animator animation) {

                            title_hide.animate().y(0).setDuration(DURATION).setListener(new SimpleAnimatorListener() {

                                @Override
                                public void onAnimationEnd(Animator animation) {

                                    title_hide.animate().y(-BASE).setDuration(0);
                                    setTextPostLeft(position);
                                }
                            });
                        }
                    });
                }
                else{

                    title.animate().y(0).setDuration(0).setListener(new SimpleAnimatorListener() {

                        @Override
                        public void onAnimationEnd(Animator animation) {

                            title.animate().y(-BASE).setDuration(DURATION).setListener(new SimpleAnimatorListener() {

                                @Override
                                public void onAnimationEnd(Animator animation) {

                                    title.animate().y(0).setDuration(0);
                                }
                            });
                        }
                    });

                    title_hide.animate().y(BASE).setDuration(0).setListener(new SimpleAnimatorListener() {

                        @Override
                        public void onAnimationEnd(Animator animation) {

                            title_hide.animate().y(0).setDuration(DURATION).setListener(new SimpleAnimatorListener() {

                                @Override
                                public void onAnimationEnd(Animator animation) {

                                    title_hide.animate().y(BASE).setDuration(0);
                                    setTextPostRight(position);
                                }
                            });
                        }
                    });
                }

                oldPosition = position;

            }
        });


    }

    private void setTextPostLeft(int position){

        switch (position){

            case 0:

                title.setText("Football");
                new Handler().postDelayed(() -> title_hide.setText("Cricket"), 50);
                break;

            case 1:

                title.setText("Cricket");
                new Handler().postDelayed(() -> title_hide.setText("Football"), 50);
                break;
        }
    }

    private void setTextPostRight(int position){

        switch (position){

            case 1:

                title.setText("Cricket");
                new Handler().postDelayed(() -> title_hide.setText("Golf"), 50);
                break;

            case 2:

                title.setText("Golf");
                new Handler().postDelayed(() -> title_hide.setText("Cricket"), 50);
                break;
        }
    }

    private void setBackgroundColor(int primary, int dark){

        toolbar.setBackgroundColor(getResources().getColor(primary));
        circleIndicator.setBackgroundColor(getResources().getColor(primary));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(dark));
        }
    }

}