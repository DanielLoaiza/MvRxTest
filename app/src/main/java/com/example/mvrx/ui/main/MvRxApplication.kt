package com.example.mvrx.ui.main

import android.app.Application
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class MvRxModule() {
    @Singleton
    @Provides
    fun provideUserRepository() = UserRepository()
}

@Singleton
@Component(modules = [MvRxModule::class])
interface MvRxComponent {
    fun userRepository(): UserRepository
}
class MvRxApplication : Application() {
    val component = DaggerMvRxComponent.create()
}