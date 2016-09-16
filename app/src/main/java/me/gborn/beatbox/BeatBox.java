package me.gborn.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 01-09-2016.
 */

public class BeatBox {
    private static String  TAG = "BeatBox";
    private static String SOUNDS_FOLDER = "sample_sounds";
    private static int MAX_SOUNDS = 5;

    private AssetManager mAssestManager;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    public BeatBox(Context context ) {
        mAssestManager = context.getAssets();
        mSoundPool = new SoundPool( MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0 );
        loadSounds();
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    public void play( Sound sound ) {
        Integer soundId = sound.getSoundId();
        if ( soundId == null ) {
            return;
        }
        mSoundPool.play( soundId, 1.0f, 1.0f, 1, 0, 1.0f );
    }

    public void release() {
         mSoundPool.release();
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = mAssestManager.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found  " + soundNames.length + " sounds. ");


            for (String soundName : soundNames) {
                String assetPath = SOUNDS_FOLDER + "/" + soundName;
                Sound sound = new Sound(assetPath);
                load( sound );
                mSounds.add( sound );
            }

        }catch ( IOException e ) {
            Log.e( TAG, "Couldn't list asset ", e );
        }
    }

    private void load( Sound sound ) throws  IOException {
        AssetFileDescriptor assetFd = mAssestManager.openFd( sound.getAssetPath() );
        int soundId = mSoundPool.load( assetFd, 1 );
        sound.setSoundId( Integer.valueOf(soundId) );
    }
}
