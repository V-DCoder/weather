package com.apps.demo.apixuweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.philippeboisney.retrokotlin.di.TestAppComponent
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.File

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {

    lateinit var mockServer : MockWebServer
    lateinit var testAppComponent: TestAppComponent

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule() // Force tests to be executed synchronously


    @Before
    open fun setUp(){
        this.configureMockServer()
        this.configureDi()
    }

    @After
    open fun tearDown(){
        this.stopMockServer()
    }

    open fun configureMockServer(){
            mockServer = MockWebServer()
            mockServer.start()

    }

    open fun stopMockServer() {
            mockServer.shutdown()
    }


    open fun mockHttpResponse(fileName: String, responseCode: Int) = mockServer.enqueue(
        MockResponse()
        .setResponseCode(responseCode)
        .setBody(getJson(fileName)))

    private fun getJson(path : String) : String {
        val uri = this.javaClass.classLoader.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())

    }

    open fun configureDi(){

//        this.testAppComponent = DaggerTestAppComponent.builder()
//            .netModule(NetModule(if (isMockServerEnabled()) mockServer.url("/").toString() else BASE_URL))
//            .repositoryModule(RepositoryModule())
//            .testRxJavaModule(TestRxJavaModule())
//            .build()
        this.testAppComponent.inject(this)
    }
}
