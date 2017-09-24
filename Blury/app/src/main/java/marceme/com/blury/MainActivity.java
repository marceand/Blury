package marceme.com.blury;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import marceme.com.blury.Helper.BottomNavigationViewHelper;
import marceme.com.blury.profile.HomeFragment;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragmentToContainer(HomeFragment.newInstance());
        setupBottomNavigation();
    }

    private void addFragmentToContainer(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void setupBottomNavigation() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener());
        navigation.setOnNavigationItemReselectedListener(mOnNavigationItemReSelectedListener());
        BottomNavigationViewHelper.removeShiftMode(navigation);
        //navigation.setSelectedItemId(R.id.navigation_profile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener(){
        return new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_profile:
                        Timber.i("on profile navigation");
                        addFragmentToContainer(HomeFragment.newInstance());
                        return true;
                    case R.id.navigation_score:
                        //mTextMessage.setText(R.string.title_dashboard);
                        return true;
                    case R.id.navigation_message:
                        //mTextMessage.setText(R.string.title_notifications);
                        return true;
                    case R.id.navigation_game:
                        //mTextMessage.setText(R.string.title_notifications);
                        return true;
                    case R.id.navigation_group:
                        //mTextMessage.setText(R.string.title_notifications);
                        return true;
                }
                return false;
            }
        };
    }

    private BottomNavigationView.OnNavigationItemReselectedListener mOnNavigationItemReSelectedListener(){
        return new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Timber.i("on profile navigation reselected");
            }
        };
    }

}
