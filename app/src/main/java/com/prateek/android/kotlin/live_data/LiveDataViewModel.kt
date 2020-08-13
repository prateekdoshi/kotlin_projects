package com.prateek.android.kotlin.live_data

import androidx.lifecycle.*
import kotlinx.coroutines.delay

class LiveDataViewModel : ViewModel() {
    val name = MutableLiveData<String>()
    private val transformedMapId = MutableLiveData<Int>()
    private val transformedSwitchMapId = MutableLiveData<Int>()
    val transformedMapName: LiveData<String> =
        Transformations.map(transformedMapId) { id -> nameAgainstId(id) }
    val transformedSwitchMapName: LiveData<String> =
        Transformations.switchMap(
            transformedSwitchMapId
        ) { id -> getNameFromRepository(id) }

    val transformedLiveDataBuilderName: LiveData<String> =
        Transformations.switchMap(
            transformedSwitchMapId
        ) { id -> getLiveDataFromBuilder(id)}

    val mediatorLiveData: MediatorLiveData<String> = MediatorLiveData()

    //simple live data object
    fun fetchName(id: Int) {
        name.value = nameAgainstId(id)
    }

    //live data observer using map operator. This takes a source live data and function and sets return value of that function in source live data
    fun fetchNameWithMapTransformation(id: Int) {
        transformedMapId.value = id
    }

    //live data observer using switch map operator. This takes a source live data and function and sets return value(return type should be always live data) of that function in source live data
    //This is useful in getting data from async call from repository
    fun fetchNameWithSwitchMapTransformation(id: Int) {
        transformedSwitchMapId.value = id
    }

    private fun nameAgainstId(id: Int): String {
        return when (id) {
            1 -> "Prateek"
            2 -> "Shelly"
            3 -> "Eksha"
            else -> "All"
        }
    }

    private fun lastNameAgainstId(id: Int): String {
        return when (id) {
            1 -> "Doshi"
            2 -> "Gandhi"
            3 -> "Pithwa"
            else -> "Jain"
        }
    }

    private fun getNameFromRepository(id: Int): LiveData<String> {
        val localLiveData = MutableLiveData<String>();
        localLiveData.value = lastNameAgainstId(id)
        return localLiveData
    }

    //observes two live data and their values and provides updated new live data based on that
    fun fetchCombinedName() {
        mediatorLiveData.addSource(
            transformedMapName
        ) {
            mediatorLiveData.value =
                transformedMapName.value + " " + transformedSwitchMapName.value
        }
        mediatorLiveData.addSource(
            transformedSwitchMapName
        ) {
            mediatorLiveData.value =
                transformedMapName.value + " " + transformedSwitchMapName.value
        }

    }
    //live data builder
    private fun getLiveDataFromBuilder(id: Int): LiveData<String> {
        return liveData {
            emit(nameAgainstId(id))
            delay(2000)
            emitSource(mediatorLiveData)
        }
    }

}