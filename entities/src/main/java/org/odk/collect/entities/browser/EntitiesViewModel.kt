package org.odk.collect.entities.browser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.odk.collect.async.Scheduler
import org.odk.collect.entities.storage.EntitiesRepository
import org.odk.collect.entities.storage.Entity

class EntitiesViewModel(
    private val scheduler: Scheduler,
    private val entitiesRepository: EntitiesRepository
) : ViewModel() {

    private val _lists = MutableLiveData<List<String>>(emptyList())
    val lists: LiveData<List<String>> = _lists

    init {
        scheduler.immediate {
            _lists.postValue(entitiesRepository.getLists().toList())
        }
    }

    fun getEntities(list: String): LiveData<List<Entity>> {
        val result = MutableLiveData<List<Entity>>(emptyList())
        scheduler.immediate {
            result.postValue(entitiesRepository.getEntities(list))
        }

        return result
    }

    fun clearAll() {
        scheduler.immediate {
            entitiesRepository.clear()
            _lists.postValue(entitiesRepository.getLists().toList())
        }
    }
}
