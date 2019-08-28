package com.apps.demo.apixuweather.di.modules;


import com.apps.demo.apixuweather.view.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityProvideModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();
}
