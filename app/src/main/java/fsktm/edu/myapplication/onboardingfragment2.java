package fsktm.edu.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class onboardingfragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //inflate the fragment and bind with corresponding xml file
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_onboarding2, container, false);

        return root;
    }
}
