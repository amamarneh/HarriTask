package com.amarnehsoft.harritask.base;

import com.amarnehsoft.harritask.di.ActivitiesBuilderModule;
import com.amarnehsoft.harritask.network.NetworkModule;
import com.amarnehsoft.harritask.viewModel.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        ActivitiesBuilderModule.class,
        ViewModelModule.class,
        NetworkModule.class
})
public interface AppComponent {
    void inject(App app);
}
