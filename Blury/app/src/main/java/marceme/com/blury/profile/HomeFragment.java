package marceme.com.blury.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import marceme.com.blury.R;
import marceme.com.blury.adapter.FeedAdapter;
import marceme.com.blury.dependencyinjection.Injector;
import marceme.com.blury.model.Feed;
import marceme.com.blury.model.Profile;
import timber.log.Timber;


public class HomeFragment extends Fragment implements HomeViewController{

    private HomePresenter homePresenter;

    @BindView(R.id.image_avatar_feed)
    ImageView imageAvatar;
    @BindView(R.id.text_name)
    TextView name;
    @BindView(R.id.text_pts_today)
    TextView todayPts;
    @BindView(R.id.text_pts_week)
    TextView weekPts;
    @BindView(R.id.text_pts_total)
    TextView totalPts;
    @BindView(R.id.text_rank)
    TextView rank;
    @BindView(R.id.feed_list)
    RecyclerView feedRecyclerView;

    private Unbinder unbinder;
    private FeedAdapter feedAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setupToolbar(view);
        injectViews(view);
        setupRecyclerView();
        setupProfilePresenter();
        return view;
    }

    private void setupRecyclerView() {
        feedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        feedAdapter = Injector.provideFeedAdapter();
        feedRecyclerView.setAdapter(feedAdapter);
    }

    private void injectViews(View view) {
        unbinder = ButterKnife.bind(this,view);
    }

    private void setupToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }

    private void setupProfilePresenter() {
        homePresenter = Injector.provideHomePresenter();
        homePresenter.attachView(this);
        homePresenter.loadProfile();
    }

    @Override
    public void onResume() {
        super.onResume();
        homePresenter.loadFeed();
    }

    @Override
    public void onPause() {
        super.onPause();
        homePresenter.unsubscribeLoadFeed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        homePresenter.detachView();
        unbinder.unbind();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showProfile(Profile profile) {
        Timber.e("one profile");
        Picasso.with(getActivity()).load(profile.avatarUrl().url()).into(imageAvatar);
        name.setText(profile.name());
        todayPts.setText(profile.todayPointsAsString());
        weekPts.setText(profile.weekPointsAsString());
        totalPts.setText(profile.totalPointsAsString());
        rank.setText(profile.rankAsString());
    }

    @Override
    public void showProfileEmptyMessage() {
        Timber.e("profile empty");
    }

    @Override
    public void showProfileProgressBar(boolean show) {
    }

    @Override
    public void profileErrorMessage() {
        Timber.e("profile error");
    }

    @Override
    public void showFeedProgressBar(boolean show) {

    }

    @Override
    public void showFeeds(List<Feed> feeds) {
        Timber.e("Feed list");
        feedAdapter.addFeeds(feeds);
    }

    @Override
    public void showFeedEmptyMessage() {
        Timber.e("Feed empty");
    }

    @Override
    public void feedErrorMessage() {
        Timber.e("Feed error");
    }
}
