package com.maksimisu.asms.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maksimisu.asms.domain.model.Car
import com.maksimisu.asms.domain.model.Client
import com.maksimisu.asms.domain.model.Job
import com.maksimisu.asms.domain.model.Position
import com.maksimisu.asms.domain.model.Worker
import com.maksimisu.asms.domain.model.relation.WorkerPositionCrossRef
import com.maksimisu.asms.domain.repository.ASMSRepository

@Database(
    entities = [Car::class,
        Client::class,
        Job::class,
        Worker::class,
        Position::class,
        WorkerPositionCrossRef::class],
    version = 1,
    exportSchema = true
)
abstract class ASMSDatabase : RoomDatabase() {
    abstract fun asmsDao(): ASMSRepository
}