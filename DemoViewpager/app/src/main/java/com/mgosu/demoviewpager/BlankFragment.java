package com.mgosu.demoviewpager;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int id = getArguments().getInt("id");
        Log.e("/////////", "onCreateView: "+id );
        View inflate = inflater.inflate(R.layout.fragment_blank, container, false);
        TextView textView = inflate.findViewById(R.id.tv);
        textView.setText(id + "");
        return inflate;
    }

    public static BlankFragment newInstance(int someInt) {
        BlankFragment myFragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt("id", someInt);
        myFragment.setArguments(args);

        return myFragment;
    }
}
