package rqg.fantasy.open163.tv.business.course

import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import rqg.fantasy.open163.tv.ActivityScope

/**
 * * Created by rqg on 15/08/2017.
 */

@Module
abstract class CourseActivityModule {
    @Binds
    abstract fun bindView(courseActivity: CourseActivity): CourseContract.View

    @Binds
    abstract fun bindPresenter(coursePresenterImpl: CoursePresenterImpl): CourseContract.Presenter
}

@ActivityScope
@Subcomponent(modules = arrayOf(CourseActivityModule::class))
interface CourseActivitySubComponent : AndroidInjector<CourseActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<CourseActivity>()
}