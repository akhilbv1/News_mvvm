package org.akiapps.news.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import org.akiapps.news.News

@Component(modules = [AndroidInjectionModule::class,ActivitiesModule::class,AppModule::class])
interface ApplicationComponent {

    fun inject(app:News)

    @Component.Builder
    interface Builder{
        fun build():ApplicationComponent

        @BindsInstance
        fun applicationBind(app:Application):Builder
    }
}