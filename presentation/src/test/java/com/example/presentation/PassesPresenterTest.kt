package com.example.presentation

import androidx.appcompat.app.AppCompatActivity
import com.example.domain.entities.LocationEntity
import com.example.domain.entities.PassEntity
import com.example.domain.usecases.GetLocation
import com.example.domain.usecases.GetPasses
import com.example.presentation.entities.Pass
import com.example.presentation.mappers.PassEntityPassMapper
import com.example.presentation.passes.MainActivity
import com.example.presentation.passes.PassesContract
import com.example.presentation.passes.PassesPresenter
import com.nhaarman.mockitokotlin2.KArgumentCaptor
import com.nhaarman.mockitokotlin2.isNotNull
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito4kotlin.annotation.*
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.mockito.Mockito.`when` as whenever

@RunWith(RobolectricTestRunner::class)
class PassesPresenterTest {

    @Mock
    private lateinit var passesContractView: PassesContract.View

    @Mock
    private lateinit var getLocation: GetLocation

    @Mock
    private lateinit var getPasses: GetPasses

    @KCaptor
    private lateinit var passListCaptor: KArgumentCaptor<List<Pass>>

    @Spy
    private var passEntityPassMapper: PassEntityPassMapper = PassEntityPassMapper()
    private lateinit var passesPresenter: PassesPresenter
    private lateinit var passListEntities: List<PassEntity>

    @Before
    fun setUp() {
        MockAnnotations.initMocks(this)
        passesPresenter = PassesPresenter(passesContractView, getLocation, getPasses, passEntityPassMapper)
        passListEntities = listOf(
            PassEntity("10", "10"),
            PassEntity("11", "11"),
            PassEntity("12", "12")
        )
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun shouldGetPassesAndPassThemToView() {
        whenever(getLocation.observable()).thenReturn(Observable.just(LocationEntity(1.toDouble(), 1.toDouble())))
        whenever(getPasses.observable(isNotNull())).thenReturn(Observable.just(passListEntities))
        passesPresenter.getPasses()
        verify(passesContractView).displayPasses(passListCaptor.capture())
        assertEquals(3, passListCaptor.firstValue.size)
    }

    @Test
    fun shouldFireOnStart() {
        //Grab the activity controller
        val controller = Robolectric.buildActivity(MainActivity::class.java).create().start()
        val activity: AppCompatActivity = controller.get()

        activity.lifecycle.addObserver(passesPresenter)
        controller.stop()
        verify(passesContractView).checkPermissions()
    }

    @Test
    fun shouldFireOnStop() {
        //Grab the activity controller
        val controller = Robolectric.buildActivity(MainActivity::class.java).create().start()
        val activity: AppCompatActivity = controller.get()

        activity.lifecycle.addObserver(passesPresenter)
        controller.stop()
        verify(getLocation).removeListener()
    }
}