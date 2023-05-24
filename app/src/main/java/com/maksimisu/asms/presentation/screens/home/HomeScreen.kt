package com.maksimisu.asms.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maksimisu.asms.domain.model.Car
import com.maksimisu.asms.domain.model.Client
import com.maksimisu.asms.domain.model.Job
import com.maksimisu.asms.domain.model.Position
import com.maksimisu.asms.domain.model.Worker
import com.maksimisu.asms.domain.model.relation.WorkerPositionCrossRef
import com.maksimisu.asms.presentation.ui.components.CarListItem
import com.maksimisu.asms.presentation.ui.components.CarWithJobsListItem
import com.maksimisu.asms.presentation.ui.components.ClientListItem
import com.maksimisu.asms.presentation.ui.components.ClientWithCarsListItem
import com.maksimisu.asms.presentation.ui.components.ClientWithJobsListItem
import com.maksimisu.asms.presentation.ui.components.HomeMenuItem
import com.maksimisu.asms.presentation.ui.components.JobListItem
import com.maksimisu.asms.presentation.ui.components.PositionListItem
import com.maksimisu.asms.presentation.ui.components.PositionWithWorkersListItem
import com.maksimisu.asms.presentation.ui.components.WorkerListItem
import com.maksimisu.asms.presentation.ui.components.WorkerPositionListItem
import com.maksimisu.asms.presentation.ui.components.WorkerWithJobsListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .padding(top = 5.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ViewModel
        val homeViewModel = hiltViewModel<HomeViewModel>()

        // Full data
        val cars = homeViewModel.cars.observeAsState().value
        val clients = homeViewModel.clients.observeAsState().value
        val jobs = homeViewModel.jobs.observeAsState().value
        val positions = homeViewModel.positions.observeAsState().value
        val workers = homeViewModel.workers.observeAsState().value
        val workersPositions = homeViewModel.workersPositions.observeAsState().value
        val carsWithJobs = homeViewModel.carsWithJobs.observeAsState().value
        val clientsWithCars = homeViewModel.clientWithCars.observeAsState().value
        val clientsWithJobs = homeViewModel.clientWithJobs.observeAsState().value
        val workersWithJobs = homeViewModel.workersWithJobs.observeAsState().value
        val positionsWithWorkers = homeViewModel.positionsWithWorkers.observeAsState().value
        val averagePrice = homeViewModel.averagePrice.observeAsState().value
        val minMaxDifference = homeViewModel.minMaxPriceDifference.observeAsState().value

        // Car editables
        var carVIN by rememberSaveable { mutableStateOf("") }
        var carBrand by rememberSaveable { mutableStateOf("") }
        var carModel by rememberSaveable { mutableStateOf("") }
        var carYear by rememberSaveable { mutableStateOf("") }
        var carOwnerId by rememberSaveable { mutableStateOf("") }

        // Client editables
        var clientId by rememberSaveable { mutableStateOf("") }
        var clientName by rememberSaveable { mutableStateOf("") }
        var clientPhone by rememberSaveable { mutableStateOf("") }

        // Job editables
        var jobId by rememberSaveable { mutableStateOf("") }
        var jobDescription by rememberSaveable { mutableStateOf("") }
        var jobPrice by rememberSaveable { mutableStateOf("") }
        var jobStartDate by rememberSaveable { mutableStateOf("") }
        var jobExecutionTime by rememberSaveable { mutableStateOf("") }
        var jobCarVin by rememberSaveable { mutableStateOf("") }
        var jobClientId by rememberSaveable { mutableStateOf("") }
        var jobWorkerId by rememberSaveable { mutableStateOf("") }

        // Position editables
        var positionId by rememberSaveable { mutableStateOf("") }
        var positionTitle by rememberSaveable { mutableStateOf("") }

        // Worker editables
        var workerId by rememberSaveable { mutableStateOf("") }
        var workerName by rememberSaveable { mutableStateOf("") }
        var workerPhone by rememberSaveable { mutableStateOf("") }

        // WorkerPosition editables
        var fkWorkerId by rememberSaveable { mutableStateOf("") }
        var fkPositionId by rememberSaveable { mutableStateOf("") }

        var home by rememberSaveable { mutableStateOf(true) }

        var car by rememberSaveable { mutableStateOf(false) }
        var client by rememberSaveable { mutableStateOf(false) }
        var worker by rememberSaveable { mutableStateOf(false) }
        var job by rememberSaveable { mutableStateOf(false) }
        var position by rememberSaveable { mutableStateOf(false) }
        var workersPositionsCondition by rememberSaveable { mutableStateOf(false) }
        var carJob by rememberSaveable { mutableStateOf(false) }
        var clientCar by rememberSaveable { mutableStateOf(false) }
        var clientJob by rememberSaveable { mutableStateOf(false) }
        var workerJob by rememberSaveable { mutableStateOf(false) }
        var positionWorker by rememberSaveable { mutableStateOf(false) }
        var average by rememberSaveable { mutableStateOf(false) }
        var minMax by rememberSaveable { mutableStateOf(false) }

        var addCar by rememberSaveable { mutableStateOf(false) }
        var addClient by rememberSaveable { mutableStateOf(false) }
        var addWorker by rememberSaveable { mutableStateOf(false) }
        var addJob by rememberSaveable { mutableStateOf(false) }
        var addPosition by rememberSaveable { mutableStateOf(false) }
        var addWorkersPositionsCondition by rememberSaveable { mutableStateOf(false) }

        var editCar by rememberSaveable { mutableStateOf(false) }
        var editClient by rememberSaveable { mutableStateOf(false) }
        var editWorker by rememberSaveable { mutableStateOf(false) }
        var editJob by rememberSaveable { mutableStateOf(false) }
        var editPosition by rememberSaveable { mutableStateOf(false) }

        val menu: List<Pair<String, () -> Unit>> = listOf(
            Pair("Show all cars") {
                home = false
                car = true
            },
            Pair("Show all clients") {
                home = false
                client = true
            },
            Pair("Show all workers") {
                home = false
                worker = true
            },
            Pair("Show all jobs") {
                home = false
                job = true
            },
            Pair("Show all positions") {
                home = false
                position = true
            },
            Pair("Show all workers-positions") {
                home = false
                workersPositionsCondition = true
            },
            Pair("Show all cars with jobs") {
                home = false
                carJob = true
            },
            Pair("Show all clients with cars") {
                home = false
                clientCar = true
            },
            Pair("Show all clients with jobs") {
                home = false
                clientJob = true
            },
            Pair("Show all workers with jobs") {
                home = false
                workerJob = true
            },
            Pair("Show all positions with workers") {
                home = false
                positionWorker = true
            },
            Pair("Show average price") {
                home = false
                average = true
                CoroutineScope(Dispatchers.IO).launch {
                    homeViewModel.getAveragePrice()
                }
            },
            Pair("Show min max price difference") {
                home = false
                minMax = true
                CoroutineScope(Dispatchers.IO).launch {
                    homeViewModel.getMinMaxPrice()
                }
            },

            Pair("Add new car") {
                home = false
                addCar = true
            },
            Pair("Add new client") {
                home = false
                addClient = true
            },
            Pair("Add new worker") {
                home = false
                addWorker = true
            },
            Pair("Add new job") {
                home = false
                addJob = true
            },
            Pair("Add new position") {
                home = false
                addPosition = true
            },
            Pair("Add new worker-position") {
                home = false
                addWorkersPositionsCondition = true
            },

            Pair("Edit car") {
                home = false
                editCar = true
            },
            Pair("Edit client") {
                home = false
                editClient = true
            },
            Pair("Edit worker") {
                home = false
                editWorker = true
            },
            Pair("Edit job") {
                home = false
                editJob = true
            },
            Pair("Edit position") {
                home = false
                editPosition = true
            }
        )

        if (home) {
            LazyColumn(
                verticalArrangement = Arrangement.Top
            ) {
                menu.forEach {
                    item {
                        HomeMenuItem(title = it.first) { it.second.invoke() }
                    }
                }
            }
        } else {
            Button(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                onClick = {
                    home = true

                    car = false
                    client = false
                    worker = false
                    job = false
                    position = false
                    workersPositionsCondition = false
                    carJob = false
                    clientCar = false
                    clientJob = false
                    workerJob = false
                    positionWorker = false
                    average = false
                    minMax = false

                    addCar = false
                    addClient = false
                    addWorker = false
                    addJob = false
                    addPosition = false
                    addWorkersPositionsCondition = false

                    editCar = false
                    editClient = false
                    editWorker = false
                    editJob = false
                    editPosition = false
                }
            ) {
                Text(text = "Back")
            }
        }

        if (car) {
            LazyColumn {
                cars?.forEach {
                    item {
                        CarListItem(it) {
                            homeViewModel.deleteCar(it.car)
                        }
                    }
                }
            }
        }

        if (addCar) {
            Column {
                TextField(
                    value = carVIN,
                    onValueChange = {
                        carVIN = it
                    },
                    label = { Text(text = "VIN") }
                )
                TextField(
                    value = carBrand,
                    onValueChange = {
                        carBrand = it
                    },
                    label = { Text(text = "Brand") }
                )
                TextField(
                    value = carModel,
                    onValueChange = {
                        carModel = it
                    },
                    label = { Text(text = "Model") }
                )
                TextField(
                    value = carYear,
                    onValueChange = {
                        carYear = it
                    },
                    label = { Text(text = "Year") }
                )
                TextField(
                    value = carOwnerId,
                    onValueChange = {
                        carOwnerId = it
                    },
                    label = { Text(text = "Owner Id") }
                )
                Button(onClick = {
                    homeViewModel.addCar(Car(
                        carVIN,
                        carBrand,
                        carModel,
                        carYear.toInt(),
                        carOwnerId.toLong()
                    ))
                    home = true
                    addCar = false
                }) {
                    Text(text = "Save car")
                }
            }
        }

        if (editCar) {
            Column {
                TextField(
                    value = carVIN,
                    onValueChange = {
                        carVIN = it
                    },
                    label = { Text(text = "VIN") }
                )
                TextField(
                    value = carBrand,
                    onValueChange = {
                        carBrand = it
                    },
                    label = { Text(text = "Brand") }
                )
                TextField(
                    value = carModel,
                    onValueChange = {
                        carModel = it
                    },
                    label = { Text(text = "Model") }
                )
                TextField(
                    value = carYear,
                    onValueChange = {
                        carYear = it
                    },
                    label = { Text(text = "Year") }
                )
                TextField(
                    value = carOwnerId,
                    onValueChange = {
                        carOwnerId = it
                    },
                    label = { Text(text = "Owner Id") }
                )
                Button(onClick = {
                    homeViewModel.updateCar(Car(
                        carVIN,
                        carBrand,
                        carModel,
                        carYear.toInt(),
                        carOwnerId.toLong()
                    ))
                    home = true
                    editCar = false
                }) {
                    Text(text = "Save car")
                }
            }
        }

        if (client) {
            LazyColumn {
                clients?.forEach {
                    item {
                        ClientListItem(it) {
                            homeViewModel.deleteClient(it)
                        }
                    }
                }
            }
        }

        if (addClient) {
            Column {
                TextField(
                    value = clientName,
                    onValueChange = {
                        clientName = it
                    },
                    label = { Text(text = "Name") }
                )
                TextField(
                    value = clientPhone,
                    onValueChange = {
                        clientPhone = it
                    },
                    label = { Text(text = "Phone") }
                )
                Button(onClick = {
                    homeViewModel.addClient(
                        Client(
                        name = clientName,
                        phone = clientPhone
                    )
                    )
                    home = true
                    addClient = false
                }) {
                    Text(text = "Save client")
                }
            }
        }

        if (editClient) {
            Column {
                TextField(
                    value = clientId,
                    onValueChange = {
                        clientId = it
                    },
                    label = { Text(text = "Id") }
                )
                TextField(
                    value = clientName,
                    onValueChange = {
                        clientName = it
                    },
                    label = { Text(text = "Name") }
                )
                TextField(
                    value = clientPhone,
                    onValueChange = {
                        clientPhone = it
                    },
                    label = { Text(text = "Phone") }
                )
                Button(onClick = {
                    homeViewModel.updateClient(
                        Client(
                            id = clientId.toLong(),
                            name = clientName,
                            phone = clientPhone
                        )
                    )
                    home = true
                    editClient = false
                }) {
                    Text(text = "Save client")
                }
            }
        }

        if (job) {
            LazyColumn {
                jobs?.forEach {
                    item {
                        JobListItem(it) {
                            homeViewModel.deleteJob(it.job)
                        }
                    }
                }
            }
        }

        if (addJob) {
            Column {
                TextField(
                    value = jobDescription,
                    onValueChange = {
                        jobDescription = it
                    },
                    label = { Text(text = "Description") }
                )
                TextField(
                    value = jobPrice,
                    onValueChange = {
                        jobPrice = it
                    },
                    label = { Text(text = "Price") }
                )
                TextField(
                    value = jobStartDate,
                    onValueChange = {
                        jobStartDate = it
                    },
                    label = { Text(text = "Start date") }
                )
                TextField(
                    value = jobExecutionTime,
                    onValueChange = {
                        jobExecutionTime = it
                    },
                    label = { Text(text = "Execution time") }
                )
                TextField(
                    value = jobCarVin,
                    onValueChange = {
                        jobCarVin = it
                    },
                    label = { Text(text = "Car VIN") }
                )
                TextField(
                    value = jobClientId,
                    onValueChange = {
                        jobClientId = it
                    },
                    label = { Text(text = "Client Id") }
                )
                TextField(
                    value = jobWorkerId,
                    onValueChange = {
                        jobWorkerId = it
                    },
                    label = { Text(text = "Worker Id") }
                )
                Button(onClick = {
                    homeViewModel.addJob(Job(
                        carVin = jobCarVin,
                        clientId = jobClientId.toLong(),
                        description = jobDescription,
                        executionTime = jobExecutionTime,
                        price = jobPrice.toDouble(),
                        startDate = jobStartDate,
                        workerId = jobWorkerId.toLong()
                    )
                    )
                    home = true
                    addJob = false
                }) {
                    Text(text = "Save job")
                }
            }
        }

        if (editJob) {
            Column {
                TextField(
                    value = jobId,
                    onValueChange = {
                        jobId = it
                    },
                    label = { Text(text = "Id") }
                )
                TextField(
                    value = jobDescription,
                    onValueChange = {
                        jobDescription = it
                    },
                    label = { Text(text = "Description") }
                )
                TextField(
                    value = jobPrice,
                    onValueChange = {
                        jobPrice = it
                    },
                    label = { Text(text = "Price") }
                )
                TextField(
                    value = jobStartDate,
                    onValueChange = {
                        jobStartDate = it
                    },
                    label = { Text(text = "Start date") }
                )
                TextField(
                    value = jobExecutionTime,
                    onValueChange = {
                        jobExecutionTime = it
                    },
                    label = { Text(text = "Execution time") }
                )
                TextField(
                    value = jobCarVin,
                    onValueChange = {
                        jobCarVin = it
                    },
                    label = { Text(text = "Car VIN") }
                )
                TextField(
                    value = jobClientId,
                    onValueChange = {
                        carOwnerId = it
                    },
                    label = { Text(text = "Client Id") }
                )
                TextField(
                    value = jobWorkerId,
                    onValueChange = {
                        jobWorkerId = it
                    },
                    label = { Text(text = "Worker Id") }
                )
                Button(onClick = {
                    homeViewModel.updateJob(Job(
                        id = jobId.toLong(),
                        carVin = jobCarVin,
                        clientId = jobClientId.toLong(),
                        description = jobDescription,
                        executionTime = jobExecutionTime,
                        price = jobPrice.toDouble(),
                        startDate = jobStartDate,
                        workerId = jobWorkerId.toLong()
                    )
                    )
                    home = true
                    editJob = false
                }) {
                    Text(text = "Save job")
                }
            }
        }

        if (position) {
            LazyColumn {
                positions?.forEach {
                    item {
                        PositionListItem(it) {
                            homeViewModel.deletePosition(it)
                        }
                    }
                }
            }
        }

        if (addPosition) {
            Column {
                TextField(
                    value = positionTitle,
                    onValueChange = {
                        positionTitle = it
                    },
                    label = { Text(text = "Title") }
                )
                Button(onClick = {
                    homeViewModel.addPosition(Position(jobTitle = positionTitle))
                    home = true
                    addPosition = false
                }) {
                    Text(text = "Save position")
                }
            }
        }

        if (editPosition) {
            Column {
                TextField(
                    value = positionId,
                    onValueChange = {
                        positionId = it
                    },
                    label = { Text(text = "Id") }
                )
                TextField(
                    value = positionTitle,
                    onValueChange = {
                        positionTitle = it
                    },
                    label = { Text(text = "Title") }
                )
                Button(onClick = {
                    homeViewModel.updatePosition(Position(
                        positionId = positionId.toLong(),
                        jobTitle = positionTitle
                    ))
                    home = true
                    addPosition = false
                }) {
                    Text(text = "Save position")
                }
            }
        }

        if (worker) {
            LazyColumn {
                workers?.forEach {
                    item {
                        WorkerListItem(it) {
                            homeViewModel.deleteWorker(it)
                        }
                    }
                }
            }
        }

        if (addWorker) {
            Column {
                TextField(
                    value = workerName,
                    onValueChange = {
                        workerName = it
                    },
                    label = { Text(text = "Name") }
                )
                TextField(
                    value = workerPhone,
                    onValueChange = {
                        workerPhone = it
                    },
                    label = { Text(text = "Phone") }
                )
                Button(onClick = {
                    homeViewModel.addWorker(Worker(
                        name = workerName,
                        phone = workerPhone
                    ))
                    home = true
                    addWorker = false
                }) {
                    Text(text = "Save worker")
                }
            }
        }

        if (editWorker) {
            Column {
                TextField(
                    value = workerId,
                    onValueChange = {
                        workerId = it
                    },
                    label = { Text(text = "Id") }
                )
                TextField(
                    value = workerPhone,
                    onValueChange = {
                        workerPhone = it
                    },
                    label = { Text(text = "Phone") }
                )
                Button(onClick = {
                    homeViewModel.updateWorker(Worker(
                        workerId = workerId.toLong(),
                        name = workerName,
                        phone = workerPhone
                    ))
                    home = true
                    editWorker = false
                }) {
                    Text(text = "Save worker")
                }
            }
        }

        if (workersPositionsCondition) {
            LazyColumn {
                workersPositions?.forEach {
                    item {
                        WorkerPositionListItem(it) {
                            it.positions?.forEach { position ->
                                homeViewModel.deleteWorkerPosition(WorkerPositionCrossRef(
                                    workerId = it.worker.workerId,
                                    positionId = position.positionId
                                ))
                            }
                        }
                    }
                }
            }
        }

        if (addWorkersPositionsCondition) {
            Column {
                TextField(
                    value = fkWorkerId,
                    onValueChange = {
                        fkWorkerId = it
                    },
                    label = { Text(text = "Worker Id") }
                )
                TextField(
                    value = fkPositionId,
                    onValueChange = {
                        fkPositionId = it
                    },
                    label = { Text(text = "Position Id") }
                )
                Button(onClick = {
                    homeViewModel.addWorkerPosition(WorkerPositionCrossRef(
                        fkWorkerId.toLong(),
                        fkPositionId.toLong()
                    ))
                    home = true
                    addWorkersPositionsCondition = false
                }) {
                    Text(text = "Save worker-position")
                }
            }
        }
        
        if (carJob) {
            LazyColumn {
                carsWithJobs?.forEach {
                    item {
                        CarWithJobsListItem(carWithJobs = it)
                    }
                }
            }
        }

        if (clientCar) {
            LazyColumn {
                clientsWithCars?.forEach {
                    item {
                        ClientWithCarsListItem(clientWithCars = it)
                    }
                }
            }
        }

        if (clientJob) {
            LazyColumn {
                clientsWithJobs?.forEach {
                    item {
                        ClientWithJobsListItem(clientWithJobs = it)
                    }
                }
            }
        }

        if (workerJob) {
            LazyColumn {
                workersWithJobs?.forEach {
                    item {
                        WorkerWithJobsListItem(workerWithJobs = it)
                    }
                }
            }
        }

        if (positionWorker) {
            LazyColumn {
                positionsWithWorkers?.forEach {
                    item {
                        PositionWithWorkersListItem(positionWithWorkers = it)
                    }
                }
            }
        }

        if (average) {
            Text(text = "Average price: $averagePrice")
        }

        if (minMax) {
            Text(text = "Min max price difference: $minMaxDifference")
        }
    }
}
