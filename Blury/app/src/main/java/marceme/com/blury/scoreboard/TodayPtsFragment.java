package marceme.com.blury.scoreboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import marceme.com.blury.R;


public class TodayPtsFragment extends Fragment {

    public TodayPtsFragment() {
        // Required empty public constructor
    }

    public static TodayPtsFragment newInstance() {
        return new TodayPtsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary_pts, container, false);
    }

}
