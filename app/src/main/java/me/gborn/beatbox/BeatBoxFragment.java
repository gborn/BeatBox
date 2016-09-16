package me.gborn.beatbox;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class BeatBoxFragment extends Fragment {
    private BeatBox mBeatBox;

    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mBeatBox = new BeatBox( getContext() );
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup parent, Bundle savedInstanceState ) {

        View view = layoutInflater.inflate( R.layout.fragment_beat_box, parent, false  );
        RecyclerView recyclerView = ( RecyclerView ) view.findViewById( R.id.recycler_view_beat_box );

        int spanCount = getSpanCount();

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), spanCount));
        recyclerView.setAdapter( new SoundAdapter( mBeatBox.getSounds() ) );
        return view;
    }

    private int getSpanCount() {
        final int PORTRAIT_SPAN_COUNT = 3;
        final int LANDSCAPE_SPAN_COUNT = 4;
        int spanCount;

        if ( getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ) {
            spanCount = PORTRAIT_SPAN_COUNT;
        } else {
            spanCount = LANDSCAPE_SPAN_COUNT;
        }
        return spanCount;
    }

    private class SoundAdapter extends RecyclerView.Adapter< SoundAdapter.SoundHolder > {

        private List<Sound> mSounds;

        public SoundAdapter( List<Sound> sounds ) {
            mSounds = sounds;
        }

        @Override
        public SoundAdapter.SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from( getActivity() );
            return new SoundHolder( inflater, parent );
        }

        @Override
        public void onBindViewHolder(SoundAdapter.SoundHolder holder, int position) {

                holder.bindSound( mSounds.get( position ) );
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }

        public class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private Button mSoundButton;
            private Sound mSound;

            public SoundHolder( LayoutInflater inflater, ViewGroup parent  ) {
                super( inflater.inflate( R.layout.list_item, parent, false) );
                mSoundButton = ( Button ) itemView.findViewById(R.id.item_sound_button);
                mSoundButton.setOnClickListener( this );
            }

            public void bindSound( Sound sound ) {
                mSound = sound;
                mSoundButton.setText( mSound.getAssetName() );
            }

            @Override
            public void onClick(View view) {
                mBeatBox.play( mSound );
            }
        }
    }

    @Override
    public void onDestroy(){
        mBeatBox.release();
        super.onDestroy();
    }

}
