package com.syzible.irishnoungenders.screens;

import com.hannesdorfmann.mosby3.mvp.MvpView;

interface MainMenuView extends MvpView {
    void showSignIn();

    void hideSignIn();

    void showSignOut();

    void hideSignOut();

    void showMessage(String message);

    void showSignedOutSuccessfully();

    void showGenericError();

    void showRequestSignIn();
}
