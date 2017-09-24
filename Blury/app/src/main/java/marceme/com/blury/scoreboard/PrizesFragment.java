package marceme.com.blury.scoreboard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import marceme.com.blury.R;


public class PrizesFragment extends Fragment {


    public PrizesFragment() {
        // Required empty public constructor
    }

    public static PrizesFragment newInstance() {
        return new PrizesFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prizes, container, false);
    }

}
