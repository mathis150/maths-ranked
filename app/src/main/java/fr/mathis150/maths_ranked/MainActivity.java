package fr.mathis150.maths_ranked;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Connection connection = new Connection();

        registerFragment(new LoginPage(this));
    }

    public void fragmentLogin(View v) {
        registerFragment(new LoginPage(this));
    }

    public void fragmentRegister(View v) {
        registerFragment(new RegisterPage(this));
    }

    public void registerFragment(Fragment f) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, f);
        fragmentTransaction.commit();
    }
    public void onLogged() {
        Intent switchActivityIntent = new Intent(this, Dashboard.class);
        startActivity(switchActivityIntent);
    }
}