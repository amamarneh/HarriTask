package com.amarnehsoft.harritask.base;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class App extends Application implements HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjectorActivity;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjectorFragment;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjectorActivity;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjectorFragment;
    }
}
