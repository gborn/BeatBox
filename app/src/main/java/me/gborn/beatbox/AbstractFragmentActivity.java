package me.gborn.beatbox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by pc on 16-08-2016.
 */

public abstract class AbstractFragmentActivity extends AppCompatActivity {


    protected  abstract Fragment createFragment();

    @Override
    public void onCreate( Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_beat_box );

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById( R.id.fragment_container);// Load fragment container
        if ( fragment == null ){
            fragment = createFragment();
            fragmentManager.beginTransaction()
                           .add( R.id.fragment_container, fragment)
                            .commit();
        }
    }


    /** Can be overridden in subclass to return a different layout at runtime.
     * Helpful while displaying master-detail interface in tablet device.
     */
   // @LayoutRes
   // protected int getLayoutResId() {
 //       return R.layout.activity_fragment;
  //  }


}
