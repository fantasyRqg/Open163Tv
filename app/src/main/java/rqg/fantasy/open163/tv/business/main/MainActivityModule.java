package rqg.fantasy.open163.tv.business.main;

import dagger.Binds;
import dagger.Module;
import rqg.fantasy.open163.tv.ActivityScope;

/**
 * * Created by rqg on 04/07/2017.
 */

@Module
public abstract class MainActivityModule {

    @Binds
    @ActivityScope
    public abstract MainContract.View provideView(MainActivity activity);


    @Binds
    @ActivityScope
    public abstract MainContract.Presenter bindPresenter(MainPresenterImpl mainPresenter);
}
