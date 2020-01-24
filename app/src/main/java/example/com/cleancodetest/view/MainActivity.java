package example.com.cleancodetest.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import example.com.cleancodetest.R;
import example.com.cleancodetest.view.fragment.DosenFragment;
import example.com.cleancodetest.view.fragment.HomeFragment;
import example.com.cleancodetest.view.fragment.WorkFragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        fragmentManager.beginTransaction().replace(R.id.fragmentLayout, new HomeFragment()).commit();
                        break;
                    case R.id.navigation_artists:
                        fragmentManager.beginTransaction().replace(R.id.fragmentLayout, new DosenFragment()).commit();
                        break;
                    case R.id.navigation_works:
                        fragmentManager.beginTransaction().replace(R.id.fragmentLayout, new WorkFragment()).commit();
                        break;
                }
            }
        });

        FloatingActionButton add_button = findViewById(R.id.fab_add);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent goToInsertActivity = new Intent(getApplicationContext(), InsertActivity.class);
//                startActivity(goToInsertActivity);
            }
        });
    }
}
