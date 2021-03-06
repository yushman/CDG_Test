package com.example.cdg_test.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PersistanceScope(override val coroutineContext: CoroutineContext = Dispatchers.IO) : CoroutineScope