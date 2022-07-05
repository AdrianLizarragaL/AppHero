package com.example.apphero.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.apphero.model.HeroDataModel
import com.example.apphero.repository.HeroRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HeroViewModel(app: Application): AndroidViewModel(app), CoroutineScope {
    private val _itemSelected = MutableLiveData<HeroDataModel>()
    var itemDataSelected: HeroDataModel? = null

    private val _listState = MutableLiveData<List<HeroDataModel>>()
    var listState: LiveData<List<HeroDataModel>> = _listState

    private val _progressState = MutableLiveData<Boolean>()
    var progressState: LiveData<Boolean> = _progressState

    private val repository = HeroRepository()
    lateinit var observerOnCategorySelected: Observer<HeroDataModel>

    private val viewModelJob = Job()
    override val coroutineContext: CoroutineContext
        get() = viewModelJob + Dispatchers.Default

    init{
        initObserver()
    }

    fun initObserver(){
        observerOnCategorySelected = Observer{ value ->
            value.let{
                _itemSelected.value = value
            }
        }
    }

    fun clearSelection(){
        _itemSelected.value = null
    }

    fun setItemSelection(item: HeroDataModel){
        itemDataSelected = item
    }

    fun fetchHeroData(){
        _progressState.value = true
        viewModelScope.launch{
            val response = repository.getHero()
            response?.body()?.let{
                _listState.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}