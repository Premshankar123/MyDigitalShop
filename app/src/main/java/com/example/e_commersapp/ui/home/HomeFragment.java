package com.example.e_commersapp.ui.home;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;
import com.example.e_commersapp.R;
import com.example.e_commersapp.SliderAdapter;
import com.example.e_commersapp.SliderData;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;


public class HomeFragment extends Fragment  {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        String url1 = "https://unreposeful-rheosta.000webhostapp.com/img/vivo.jpg";
        String url2 = "https://unreposeful-rheosta.000webhostapp.com/img/bat_ball.jpg";
        String url3 = "https://unreposeful-rheosta.000webhostapp.com/img/mi.jpg";
        String url4 = "https://unreposeful-rheosta.000webhostapp.com/img/headphone.jpg";
        String url5 = "https://unreposeful-rheosta.000webhostapp.com/img/marketing_kids.jpg";

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // we are creating array list for storing our image urls.
            ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

            // initializing the slider view.
            SliderView sliderView = root.findViewById(R.id.slider);

            // adding the urls inside array list
            sliderDataArrayList.add(new SliderData(url1));
            sliderDataArrayList.add(new SliderData(url2));
            sliderDataArrayList.add(new SliderData(url3));
            sliderDataArrayList.add(new SliderData(url4));
            sliderDataArrayList.add(new SliderData(url5));

            // passing this array list inside our adapter class.
            SliderAdapter adapter = new SliderAdapter(getContext(), sliderDataArrayList);

            // below method is used to set auto cycle direction in left to
            // right direction you can change according to requirement.
            sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

            // below method is used to
            // setadapter to sliderview.
            sliderView.setSliderAdapter(adapter);

            // below method is use to set
            // scroll time in seconds.
            sliderView.setScrollTimeInSec(3);

            // to set it scrollable automatically
            // we use below method.
            sliderView.setAutoCycle(true);
            sliderView.startAutoCycle();

        //horizantal product slider program


        return root;


    }


}