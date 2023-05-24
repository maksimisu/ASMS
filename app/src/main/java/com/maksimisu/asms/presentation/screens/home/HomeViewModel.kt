package com.maksimisu.asms.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import com.maksimisu.asms.domain.usecase.AddCarUseCase
import com.maksimisu.asms.domain.usecase.AddClientUseCase
import com.maksimisu.asms.domain.usecase.AddJobUseCase
import com.maksimisu.asms.domain.usecase.AddPositionUseCase
import com.maksimisu.asms.domain.usecase.AddWorkerPositionUseCase
import com.maksimisu.asms.domain.usecase.AddWorkerUseCase
import com.maksimisu.asms.domain.usecase.DeleteCarUseCase
import com.maksimisu.asms.domain.usecase.DeleteClientUseCase
import com.maksimisu.asms.domain.usecase.DeleteJobUseCase
import com.maksimisu.asms.domain.usecase.DeletePositionUseCase
import com.maksimisu.asms.domain.usecase.DeleteWorkerPositionUseCase
import com.maksimisu.asms.domain.usecase.DeleteWorkerUseCase
import com.maksimisu.asms.domain.usecase.GetAllCarsUseCase
import com.maksimisu.asms.domain.usecase.GetAllCarsWithJobsUseCase
import com.maksimisu.asms.domain.usecase.GetAllClientWithCarsUseCase
import com.maksimisu.asms.domain.usecase.GetAllClientsUseCase
import com.maksimisu.asms.domain.usecase.GetAllClientsWithJobsUseCase
import com.maksimisu.asms.domain.usecase.GetAllJobsUseCase
import com.maksimisu.asms.domain.usecase.GetAllPositionsUseCase
import com.maksimisu.asms.domain.usecase.GetAllPositionsWithWorkersUseCase
import com.maksimisu.asms.domain.usecase.GetAllWorkersPositionsUseCase
import com.maksimisu.asms.domain.usecase.GetAllWorkersUseCase
import com.maksimisu.asms.domain.usecase.GetAllWorkersWithJobsUseCase
import com.maksimisu.asms.domain.usecase.GetAveragePriceUseCase
import com.maksimisu.asms.domain.usecase.GetMinMaxPriceDifferenceUseCase
import com.maksimisu.asms.domain.usecase.UpdateCarUseCase
import com.maksimisu.asms.domain.usecase.UpdateClientUseCase
import com.maksimisu.asms.domain.usecase.UpdateJobUseCase
import com.maksimisu.asms.domain.usecase.UpdatePositionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addCarUseCase: AddCarUseCase,
    private val addClientUseCase: AddClientUseCase,
    private val addJobUseCase: AddJobUseCase,
    private val addPositionUseCase: AddPositionUseCase,
    private val addWorkerPositionUseCase: AddWorkerPositionUseCase,
    private val addWorkerUseCase: AddWorkerUseCase,
    private val deleteCarUseCase: DeleteCarUseCase,
    private val deleteClientUseCase: DeleteClientUseCase,
    private val deleteJobUseCase: DeleteJobUseCase,
    private val deletePositionUseCase: DeletePositionUseCase,
    private val deleteWorkerPositionUseCase: DeleteWorkerPositionUseCase,
    private val deleteWorkerUseCase: DeleteWorkerUseCase,
    private val getAllCarsUseCase: GetAllCarsUseCase,
    private val getAllClientsUseCase: GetAllClientsUseCase,
    private val getAllJobsUseCase: GetAllJobsUseCase,
    private val getAllPositionsUseCase: GetAllPositionsUseCase,
    private val getAllWorkersPositionsUseCase: GetAllWorkersPositionsUseCase,
    private val getAllWorkerUseCase: GetAllWorkersUseCase,
    private val getAllCarsWithJobsUseCase: GetAllCarsWithJobsUseCase,
    private val getAllClientsWithCarsUseCase: GetAllClientWithCarsUseCase,
    private val getAllClientsWithJobsUseCase: GetAllClientsWithJobsUseCase,
    private val getAllWorkersWithJobsUseCase: GetAllWorkersWithJobsUseCase,
    private val getAllPositionsWithWorkersUseCase: GetAllPositionsWithWorkersUseCase,
    private val getAveragePriceUseCase: GetAveragePriceUseCase,
    private val getMinMaxPriceDifferenceUseCase: GetMinMaxPriceDifferenceUseCase,
    private val updateCarUseCase: UpdateCarUseCase,
    private val updateClientUseCase: UpdateClientUseCase,
    private val updateJobUseCase: UpdateJobUseCase,
    private val updatePositionUseCase: UpdatePositionUseCase,
    private val updateWorkerUseCase: AddWorkerUseCase
) : ViewModel() {

    private val _cars = MutableLiveData<List<CarWithClient>>()
    val cars: LiveData<List<CarWithClient>>
        get() = _cars

    private val _clients = MutableLiveData<List<Client>>()
    val clients: LiveData<List<Client>>
        get() = _clients

    private val _jobs = MutableLiveData<List<JobWithCarAndClientAndWorker>>()
    val jobs: LiveData<List<JobWithCarAndClientAndWorker>>
        get() = _jobs

    private val _positions = MutableLiveData<List<Position>>()
    val positions: LiveData<List<Position>>
        get() = _positions

    private val _workers = MutableLiveData<List<Worker>>()
    val workers: LiveData<List<Worker>>
        get() = _workers

    private val _workersPositions = MutableLiveData<List<WorkerWithPositions>>()
    val workersPositions: LiveData<List<WorkerWithPositions>>
        get() = _workersPositions

    private val _carsWithJobs = MutableLiveData<List<CarWithJobs>>()
    val carsWithJobs: LiveData<List<CarWithJobs>>
        get() = _carsWithJobs

    private val _clientsWithCars = MutableLiveData<List<ClientWithCars>>()
    val clientWithCars: LiveData<List<ClientWithCars>>
        get() = _clientsWithCars

    private val _clientsWithJobs = MutableLiveData<List<ClientWithJobs>>()
    val clientWithJobs: LiveData<List<ClientWithJobs>>
        get() = _clientsWithJobs

    private val _workersWithJobs = MutableLiveData<List<WorkerWithJobs>>()
    val workersWithJobs: LiveData<List<WorkerWithJobs>>
        get() = _workersWithJobs

    private val _positionsWithWorkers = MutableLiveData<List<PositionWithWorkers>>()
    val positionsWithWorkers: LiveData<List<PositionWithWorkers>>
        get() = _positionsWithWorkers

    private val _averagePrice = MutableLiveData<Double>()
    val averagePrice: LiveData<Double>
        get() = _averagePrice

    private val _minMaxPriceDifference = MutableLiveData<Double>()
    val minMaxPriceDifference: LiveData<Double>
        get() = _minMaxPriceDifference

    init {
        getAllData()
    }

    private fun getAllData() {
        viewModelScope.launch {
            getAllCarsUseCase.invoke().let {
                _cars.postValue(it)
            }
            getAllClientsUseCase.invoke().let {
                _clients.postValue(it)
            }
            getAllJobsUseCase.invoke().let {
                _jobs.postValue(it)
            }
            getAllPositionsUseCase.invoke().let {
                _positions.postValue(it)
            }
            getAllWorkerUseCase.invoke().let {
                _workers.postValue(it)
            }
            getAllWorkersPositionsUseCase.invoke().let {
                _workersPositions.postValue(it)
            }
            getAllCarsWithJobsUseCase.invoke().let {
                _carsWithJobs.postValue(it)
            }
            getAllClientsWithCarsUseCase.invoke().let {
                _clientsWithCars.postValue(it)
            }
            getAllClientsWithJobsUseCase.invoke().let {
                _clientsWithJobs.postValue(it)
            }
            getAllWorkersWithJobsUseCase.invoke().let {
                _workersWithJobs.postValue(it)
            }
            getAllPositionsWithWorkersUseCase.invoke().let {
                _positionsWithWorkers.postValue(it)
            }

        }
    }

    suspend fun getAveragePrice() {
        getAveragePriceUseCase.invoke().let {
            _averagePrice.postValue(it)
        }
    }

    suspend fun getMinMaxPrice() {
        getMinMaxPriceDifferenceUseCase.invoke().let {
            _minMaxPriceDifference.postValue(it)
        }
    }

    fun addCar(car: Car) {
        viewModelScope.launch {
            addCarUseCase.invoke(car)
            getAllData()
        }
    }

    fun addClient(client: Client) {
        viewModelScope.launch {
            addClientUseCase.invoke(client)
            getAllData()
        }
    }

    fun addJob(job: Job) {
        viewModelScope.launch {
            addJobUseCase.invoke(job)
            getAllData()
        }
    }

    fun addPosition(position: Position) {
        viewModelScope.launch {
            addPositionUseCase.invoke(position)
            getAllData()
        }
    }

    fun addWorker(worker: Worker) {
        viewModelScope.launch {
            addWorkerUseCase.invoke(worker)
            getAllData()
        }
    }

    fun addWorkerPosition(workerPositionCrossRef: WorkerPositionCrossRef) {
        viewModelScope.launch {
            addWorkerPositionUseCase.invoke(workerPositionCrossRef)
            getAllData()
        }
    }

    fun updateCar(car: Car) {
        viewModelScope.launch {
            updateCarUseCase.invoke(car)
            getAllData()
        }
    }

    fun updateClient(client: Client) {
        viewModelScope.launch {
            updateClientUseCase.invoke(client)
            getAllData()
        }
    }

    fun updateJob(job: Job) {
        viewModelScope.launch {
            updateJobUseCase.invoke(job)
            getAllData()
        }
    }

    fun updatePosition(position: Position) {
        viewModelScope.launch {
            updatePositionUseCase(position)
            getAllData()
        }
    }

    fun updateWorker(worker: Worker) {
        viewModelScope.launch {
            updateWorkerUseCase.invoke(worker)
            getAllData()
        }
    }

    fun deleteCar(car: Car) {
        viewModelScope.launch {
            deleteCarUseCase.invoke(car)
            getAllData()
        }
    }

    fun deleteClient(client: Client) {
        viewModelScope.launch {
            deleteClientUseCase.invoke(client)
            getAllData()
        }
    }

    fun deleteJob(job: Job) {
        viewModelScope.launch {
            deleteJobUseCase.invoke(job)
            getAllData()
        }
    }

    fun deletePosition(position: Position) {
        viewModelScope.launch {
            deletePositionUseCase.invoke(position)
            getAllData()
        }
    }

    fun deleteWorker(worker: Worker) {
        viewModelScope.launch {
            deleteWorkerUseCase.invoke(worker)
            getAllData()
        }
    }

    fun deleteWorkerPosition(workerPositionCrossRef: WorkerPositionCrossRef) {
        viewModelScope.launch {
            deleteWorkerPositionUseCase.invoke(workerPositionCrossRef)
            getAllData()
        }
    }
}