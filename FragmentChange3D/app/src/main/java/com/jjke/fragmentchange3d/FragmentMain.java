package com.jjke.fragmentchange3d;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;

/**
 * Created by Administrator on 6/18/2016.
 */
public class FragmentMain extends Fragment {
    private View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main,null);
        root.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .setCustomAnimations(R.animator.frag_another_in,R.animator.frag_main_out,R.animator.frag_main_in,R.animator.frag_another_out)
                        .replace(R.id.container,new FragmentAnother()).commit();
            }
        });
        return root;
    }
}
