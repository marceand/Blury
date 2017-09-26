package marceme.com.blury.scoreboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import marceme.com.blury.R;
import marceme.com.blury.adapter.ScoreAdapter;
import marceme.com.blury.dependencyinjection.Injector;
import marceme.com.blury.model.Score;
import timber.log.Timber;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;


public class ScoreboardFragment extends Fragment implements ScoreboardViewController{

    @BindView(R.id.score_list)
    RecyclerView scoreRecyclerView;
    @BindView(R.id.progress_score)
    ProgressBar scoreProgress;

    private ScoreboardPresenter scoreboardPresenter;
    private Unbinder unbinder;
    private ScoreAdapter scoreAdapter;

    public ScoreboardFragment() {
        // Required empty public constructor
    }


    public static ScoreboardFragment newInstance() {
        return new ScoreboardFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Timber.i("on create score board");
        View view = inflater.inflate(R.layout.fragment_scoreboard, container, false);
        injectView(view);
        setupRecyclerView();
        setupScoreboardPresenter();
        return view;
    }

    private void injectView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    private void setupRecyclerView() {
        scoreRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        scoreRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), VERTICAL));
        scoreAdapter = Injector.provideScoreAdapter();
        scoreRecyclerView.setAdapter(scoreAdapter);
    }

    private void setupScoreboardPresenter() {
        scoreboardPresenter = Injector.provideScoreboardPresenter();
        scoreboardPresenter.attachView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.i("on resume score board");
        scoreboardPresenter.loadScore();
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.i("on pause score board");
        scoreboardPresenter.stopLoadScore();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        scoreboardPresenter.detachView();
    }

    @Override
    public void showProgressBar(boolean show) {
        scoreProgress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showScore(List<Score> scores) {
        Timber.i("add score to adapter");
        scoreAdapter.addScore(scores);
    }

    @Override
    public void showEmptyMessage() {
        Timber.e("empty score");
    }

    @Override
    public void errorMessage() {
        Timber.e("error happen while loading score");
    }
}
