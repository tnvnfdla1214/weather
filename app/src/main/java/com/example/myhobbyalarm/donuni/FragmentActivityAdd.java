package com.example.hadon.customwidgetlistview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentActivityAdd extends Fragment implements View.OnClickListener {
    Button f4BtnName;
    View view;
    TextView textView;
    public FragmentActivityAdd() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add,container, false);
        f4BtnName=view.findViewById(R.id.fBtn);
        textView=view.findViewById(R.id.textView);

        String checkValueStr=getArguments().getString("ㅅㅅ");

        textView.setText(checkValueStr);
        f4BtnName.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fBtn:
                toastDisplay("111111111111");
                break;
        }
    }

    private void toastDisplay(String s) {
        Toast.makeText(view.getContext(), s,Toast.LENGTH_LONG).show();
    }
}
