package com.maksimisu.asms.domain.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.maksimisu.asms.domain.model.Car
import com.maksimisu.asms.domain.model.Client
import com.maksimisu.asms.domain.model.Job
import com.maksimisu.asms.domain.model.Position
import com.maksimisu.asms.domain.model.Worker
import com.maksimisu.asms.domain.model.relation.CarWithClient
import com.maksimisu.asms.domain.model.relation.CarWithJobs
import com.maksimisu.asms.domain.model.relation.ClientWithCars
import com.maksimisu.asms.domain.model.relation.ClientWithJobs
import com.maksimisu.asms.domain.model.relation.JobWithCarAndClientAndWorker
import com.maksimisu.asms.domain.model.relation.PositionWithWorkers
import com.maksimisu.asms.domain.model.relation.WorkerPositionCrossRef
import com.maksimisu.asms.domain.model.relation.WorkerWithJobs
import com.maksimisu.asms.domain.model.relation.WorkerWithPositions

@Dao
interface ASMSRepository {

    @Insert
    suspend fun insertCar(car: Car)

    @Insert
    suspend fun insertClient(client: Client)

    @Insert
    suspend fun insertWorker(worker: Worker)

    @Insert
    suspend fun insertPosition(position: Position)

    @Insert
    suspend fun insertJob(job: Job)

    @Insert
    suspend fun insertWorkerPosition(workerPositionCrossRef: WorkerPositionCrossRef)

    @Transaction
    @Query("SELECT * FROM Car")
    suspend fun getAllCars(): List<CarWithClient>

    @Query("SELECT * FROM Client")
    suspend fun getAllClients(): List<Client>

    @Query("SELECT * FROM Worker")
    suspend fun getAllWorkers(): List<Worker>

    @Query("SELECT * FROM Position")
    suspend fun getAllPositions(): List<Position>

    @Transaction
    @Query("SELECT * FROM Job")
    suspend fun getAllJobs(): List<JobWithCarAndClientAndWorker>

    @Transaction
    @Query("SELECT * FROM Worker")
    suspend fun getAllWorkersWithPositions(): List<WorkerWithPositions>

    @Transaction
    @Query("SELECT * FROM Car")
    suspend fun getAllCarsWithJobs(): List<CarWithJobs>

    @Transaction
    @Query("SELECT * FROM Client")
    suspend fun getAllClientsWithCars(): List<ClientWithCars>

    @Transaction
    @Query("SELECT * FROM Client")
    suspend fun getAllClientsWithJobs(): List<ClientWithJobs>

    @Transaction
    @Query("SELECT * FROM Worker")
    suspend fun getAllWorkersWithJobs(): List<WorkerWithJobs>

    @Transaction
    @Query("SELECT * FROM Position")
    suspend fun getAllPositionsWithWorkers(): List<PositionWithWorkers>

    @Query("SELECT AVG(price) FROM Job")
    suspend fun getAveragePrice(): Double

    @Query("SELECT MAX(price) - MIN(price) FROM Job")
    suspend fun getMinMaxPriceDifference(): Double

    @Update
    suspend fun updateCar(car: Car)

    @Update
    suspend fun updateClient(client: Client)

    @Update
    suspend fun updateWorker(worker: Worker)

    @Update
    suspend fun updatePosition(position: Position)

    @Update
    suspend fun updateJob(job: Job)

    @Delete
    suspend fun deleteCar(car: Car)

    @Delete
    suspend fun deleteClient(client: Client)

    @Delete
    suspend fun deleteWorker(worker: Worker)

    @Delete
    suspend fun deletePosition(position: Position)

    @Delete
    suspend fun deleteJob(job: Job)

    @Delete
    suspend fun deleteWorkerPosition(workerPositionCrossRef: WorkerPositionCrossRef)

}