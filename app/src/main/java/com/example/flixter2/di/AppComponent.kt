package com.example.flixter2.di

import android.app.Application
import com.example.flixter2.fragments.details.DetailFragment
import com.example.flixter2.fragments.movie.MovieFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ViewModelModule::class, ServiceModule::class]
)
interface AppComponent {
    fun inject(fragment: MovieFragment)
    fun inject(fragment: DetailFragment)
}

class MyApplication: Application() {
    // Reference to the application graph that is used across the whole app
    var appComponent: AppComponent = DaggerAppComponent.create()
}