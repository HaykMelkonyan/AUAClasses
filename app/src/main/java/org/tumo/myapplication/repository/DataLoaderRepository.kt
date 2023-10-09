package org.tumo.myapplication.repository

import kotlin.random.Random

class DataLoaderRepository {
    fun getData(): List<String> {
        return listOf(
            "one",
            "2t",
            "4",
            Random(100).toString(),
            Random(200).toString()
        )
    }

}
