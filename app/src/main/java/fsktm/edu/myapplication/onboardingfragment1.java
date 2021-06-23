package fsktm.edu.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class onboardingfragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        //inflate the fragment and bind with corresponding xml file
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_onboarding1,container,false);

        return root;
    }
}
