package io.github.philippeboisney.retrokotlin.di

import com.apps.demo.apixuweather.ExampleUnitTest
import com.apps.demo.apixuweather.di.RepositoryModuleTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModuleTest::class,
        TestRxJavaModule::class]
)
interface TestAppComponent {
    fun inject(baseTest: ExampleUnitTest)
}