package fr.mathis150.maths_ranked;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RegisterPage extends Fragment implements View.OnClickListener {

    View view;
    MainActivity main;

    public RegisterPage(MainActivity mainActivity) {
        this.main = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register_page, container, false);

        TextView textView = (TextView) view.findViewById(R.id.textView2);

        textView.setOnClickListener(this);

        return view;
    }

    public void getLoginButton(View view) {
        this.main.registerFragment(new LoginPage(this.main));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.textView2)
        {
            getLoginButton(v);
        }
    }
}