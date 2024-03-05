package fr.mathis150.maths_ranked;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends Fragment implements View.OnClickListener {

    View view;
    MainActivity main;

    public LoginPage(MainActivity mainActivity) {
        this.main = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login_page, container, false);

        TextView textView = (TextView) view.findViewById(R.id.textView2);
        Button button = (Button) view.findViewById(R.id.button2);

        textView.setOnClickListener(this);
        button.setOnClickListener(this);

        return view;
    }

    public void getRegisterButton(View view) {
        this.main.registerFragment(new RegisterPage(this.main));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.textView2)
        {
            getRegisterButton(v);
        }
        if(v.getId() == R.id.button2)
        {
            getLoginButton(v);
        }
    }

    private void getLoginButton(View v) {
        this.main.onLogged();
    }
}