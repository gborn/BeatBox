package me.gborn.beatbox;

import android.support.v4.app.Fragment;

public class BeatBoxActivity extends AbstractFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return  BeatBoxFragment.newInstance();
    }

}
