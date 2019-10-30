package com.syzible.irishnoungenders;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.syzible.irishnoungenders.common.persistence.LocalStorage;
import com.syzible.irishnoungenders.screens.MainMenuFragment;
import com.syzible.irishnoungenders.screens.intro.IntroActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment(getSupportFragmentManager(), MainMenuFragment.getInstance());

        if (!LocalStorage.getBooleanPref(this, LocalStorage.Pref.FIRST_RUN_COMPLETE)) {
            setupInitialSettings();
            startActivity(new Intent(this, IntroActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            super.onBackPressed();
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("Exit game?")
                .setMessage("Click close below if you wish to exit the game")
                .setPositiveButton("Close", (dialogInterface, i) -> MainActivity.this.finish())
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void setupInitialSettings() {
        LocalStorage.setBooleanPref(this, LocalStorage.Pref.SHOW_HINTS, true);
    }

    public static void setFragment(FragmentManager fragmentManager, Fragment fragment) {
        if (fragmentManager != null) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_content, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }
    }

    public static void setFragmentBackstack(FragmentManager fragmentManager, Fragment fragment) {
        if (fragmentManager != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_content, fragment)
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }
    }
}
