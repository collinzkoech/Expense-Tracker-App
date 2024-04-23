package com.example.expensetracker

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.expensetracker.viewmodel.database.ExpenseRepository
import com.example.expensetracker.viewmodel.database.RecurringExpenseDatabase
import com.example.expensetracker.viewmodel.database.UserPreferencesRepository

class ExpenseTrackerApplication : Application() {
    private val database by lazy { RecurringExpenseDatabase.getDatabase(this) }
    val repository by lazy { ExpenseRepository(database.recurringExpenseDao()) }

    private val Context.dataStore by preferencesDataStore(name = com.example.expensetracker.ExpenseTrackerApplication.Companion.USER_PREFERENCES_DATA_STORE)
    val userPreferencesRepository by lazy { UserPreferencesRepository(dataStore = dataStore) }

    private companion object {
        private const val USER_PREFERENCES_DATA_STORE = "USER_PREFERENCES_DATA_STORE"
    }
}
