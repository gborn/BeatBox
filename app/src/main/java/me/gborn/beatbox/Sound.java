package me.gborn.beatbox;

/**
 * Created by pc on 01-09-2016.
 */

public class Sound {
    private String mAssetPath;
    private String mAssetName;
    private Integer mSoundId;

    public Sound( String assetPath ) {
        mAssetPath = assetPath;
        String[] component = assetPath.split("/");
        mAssetName = component[ component.length - 1 ];
        mAssetName = mAssetName.replace(".wav", "");
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public void setAssetPath(String assetPath) {
        mAssetPath = assetPath;
    }

    public String getAssetName() {
        return mAssetName;
    }

    public void setAssetName(String assetName) {
        mAssetName = assetName;
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }
}
