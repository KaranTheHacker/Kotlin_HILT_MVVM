package com.example.myapplication

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

class ScopedCounter @Inject constructor() {
    private var count = 0

    init {
        Log.d("ScopedCounter", "ScopedCounter created: $this")
    }

    fun increment() {
        count++
        Log.d("ScopedCounter", "Count incremented to: $count in $this")
    }

    fun getCounter(): Int {
        return count
    }
}

/**
 * @Singleton (Singleton Component)
 * provide a single instance of the dependency for the entire application lifecycle
 * -> the instance is created only when its first request => remain in memory untill app destroyed
 *
 *
 */
//@Module
//@InstallIn(FragmentComponent::class)
//object FragmentModule{
//    @Provides
//    @FragmentScoped
//    @Vi/
//}