package com.example.ftiga;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ftiga._sliders.FragmentSlider;
import com.example.ftiga._sliders.SliderIndicator;
import com.example.ftiga._sliders.SliderPagerAdapter;
import com.example.ftiga._sliders.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private SliderView sliderView;
    private LinearLayout mLinearLayout;

    ListView mListView;

    int[] images ={R.drawable.slide_1,R.drawable.slide_2,R.drawable.slide_3};

    String[]  titles ={"Antar: Son of Shadad","Bathtubs Over Broadway","Gatao 2: Rise of the King"};

    String[]  yearMovies ={"2017","2018","2018"};

    String[]  ratingMovies ={"5.7/10","8.0/10","6.1/10"};

    String[]  durationMovies ={"110 min","87 min","126 min"};

    String[]  genreMovies ={"Comedies","Documentaries","Dramas"};

    String[]  regionalMovies ={"International Movies","Music & Musicals","International Movies"};

    String[]  sinopsisMovies ={"Learning he is the grandson of a famous knight, a young man hilariously fights a rival gang to win over the most beautiful woman in the tribe.",
            "A comedy writer for David Letterman unearths a hidden world of hilariously bizarre musicals, which turns into a toe-tapping obsession.",
            "When a zealous gang leader plans to take out a friend-turned-rival for control of his turf, internal tensions complicate the battle to be the boss."};

    String[]  directiorMovies ={"Sherif Ismail","Dava Whisenant","Yen Cheng-kuo"};

    String[]  castMovies ={"Mohamed Henedi, Bassem Samra, Dorra Zarrouk, Lotfy Labib",
            "Steve Young","Collin Chou, Jason Wang, Jack Kao, Tsai Chen-nan, Lu Hsueh-feng, Peng Sun"};

    String[]  prodcounMovies ={"Egypt","United States","Taiwan"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        sliderView = (SliderView) rootView.findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) rootView.findViewById(R.id.pagesContainer);
        setupSliderOfline();

        return rootView;

        /*mListView = (ListView) rootView.findViewById(R.id.listView);

        CustomeAdaptor customeAdaptor = new CustomeAdaptor();
        mListView.setAdapter(customeAdaptor);*/

   /*     mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), content_movies.class);
                intent.putExtra("judul",titles[position]);
                intent.putExtra("gambar",images[position]);
                intent.putExtra("tahun",yearMovies[position]);
                intent.putExtra("durasi",durationMovies[position]);
                intent.putExtra("genre",genreMovies[position]);
                intent.putExtra("regional",regionalMovies[position]);
                intent.putExtra("sinopsisFilm",sinopsisMovies[position]);
                intent.putExtra("direktur",directiorMovies[position]);
                intent.putExtra("casting",castMovies[position]);
                intent.putExtra("negaraProduksi",prodcounMovies[position]);
                startActivity(intent);

            }
        });*/
    }

    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("http://www.menucool.com/slider/prod/image-slider-1.jpg"));
        fragments.add(FragmentSlider.newInstance("http://www.menucool.com/slider/prod/image-slider-2.jpg"));
        fragments.add(FragmentSlider.newInstance("http://www.menucool.com/slider/prod/image-slider-3.jpg"));
        fragments.add(FragmentSlider.newInstance("http://www.menucool.com/slider/prod/image-slider-4.jpg"));

        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

    private void setupSliderOfline() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("slide_1"));
        fragments.add(FragmentSlider.newInstance("slide_2"));
        fragments.add(FragmentSlider.newInstance("slide_3"));
        fragments.add(FragmentSlider.newInstance("slide_4"));

        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

   /* @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.listView:
                Intent intent = new Intent(getActivity(), content_movies.class);
                intent.putExtra("judul",titles[position]);
                intent.putExtra("gambar",images[position]);
                intent.putExtra("tahun",yearMovies[position]);
                intent.putExtra("durasi",durationMovies[position]);
                intent.putExtra("genre",genreMovies[position]);
                intent.putExtra("regional",regionalMovies[position]);
                intent.putExtra("sinopsisFilm",sinopsisMovies[position]);
                intent.putExtra("direktur",directiorMovies[position]);
                intent.putExtra("casting",castMovies[position]);
                intent.putExtra("negaraProduksi",prodcounMovies[position]);
                getActivity().startActivity(intent);

        }
    }*/

    class CustomeAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.item_movies,null);

            ImageView mImageView = view.findViewById(R.id.imgViewOne);

            TextView titleTxtView = view.findViewById(R.id.txtTitle);

            TextView yearTxtView = view.findViewById(R.id.txtYearTitle);

            TextView ratingTxtView = view.findViewById(R.id.txtRatingTitle);

            mImageView.setImageResource(images[position]);

            titleTxtView.setText(titles[position]);

            yearTxtView.setText(yearMovies[position]);

            ratingTxtView.setText(ratingMovies[position]);

            return view;
        }
    }
}
