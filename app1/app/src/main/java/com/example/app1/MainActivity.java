package com.example.app1;
import com.google.android.material.appbar.MaterialToolbar;
//
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.android.material.appbar.MaterialToolbar;
//
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import androidx.appcompat.widget.Toolbar;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.firstactivity);
//
//        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
//        setSupportActionBar(topAppBar);
//
//        topAppBar.setNavigationOnClickListener(view -> {
//            // Handle navigation icon press
//        });
//
//        @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//            getMenuInflater().inflate(R.menu.top_app_bar, menu);
//            return true;
//        }
//
//        topAppBar.setOnMenuItemClickListener(menuItem -> {
//            int menuItemId = menuItem.getItemId(); // Get the menu item's ID
////            switch (menuItemId) {
////                case R.id.favorite:
////                    // Handle favorite icon press
////                    return true;
////                case R.id.search:
////                    // Handle search icon press
////                    return true;
////                case R.id.more:
////                    // Handle more item (inside overflow menu) press
////                    return true;
////                default:
////                    return false;
////            }
//            if(menuItemId == R.id.favorite) {
//                return true;
//            }
//            else if(menuItemId == R.id.search) {
//                return true;
//            }
//            else if(menuItemId == R.id.more) {
//                return true;
//            }
//            else {
//                return false;
//            }
//        });
//    }
//
//
//}
//
//

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivity);

        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        topAppBar.setNavigationOnClickListener(view -> {
            // Handle navigation icon press
        });

        topAppBar.setOnMenuItemClickListener(menuItem -> {
            int menuItemId = menuItem.getItemId();
            if(menuItemId == R.id.favorite) {
                return true;
            }
            else if(menuItemId == R.id.search) {
                return true;
            }
            else if(menuItemId == R.id.first) {
                return true;
            }
            else {
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }


}

