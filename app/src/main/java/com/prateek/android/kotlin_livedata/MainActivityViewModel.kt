package com.prateek.android.kotlin_livedata

import androidx.lifecycle.*

class MainActivityViewModel : ViewModel() {
    val name = MutableLiveData<String>()
    private val transformedMapId = MutableLiveData<Int>()
    private val transformedSwitchMapId = MutableLiveData<Int>()
    val transformedMapName: LiveData<String> =
        Transformations.map(transformedMapId) { id -> nameAgainstId(id) }
    val transformedSwitchMapName: LiveData<String> =
        Transformations.switchMap(
            transformedSwitchMapId
        ) { id -> getNameFromRepository(id) }

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
        println("viewmodel ${System.currentTimeMillis()}")
        return when (id) {
            1 -> "Prateek"
            2 -> "Shelly"
            3 -> "Eksha"
            else -> "All"
        }
    }

    private fun getNameFromRepository(id: Int): LiveData<String> {
        return MutableLiveData<String>()
    }

}