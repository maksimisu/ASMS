package com.maksimisu.asms.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maksimisu.asms.domain.model.Car
import com.maksimisu.asms.domain.model.Client
import com.maksimisu.asms.domain.model.Job
import com.maksimisu.asms.domain.model.Worker
import com.maksimisu.asms.domain.model.relation.JobWithCarAndClientAndWorker

@Composable
fun JobListItem(jobWithCarAndClientAndWorker: JobWithCarAndClientAndWorker, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(5.dp),
        backgroundColor = Color.White,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "ID: ${jobWithCarAndClientAndWorker.job.id}",
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Description: " + jobWithCarAndClientAndWorker.job.description)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Price: " + jobWithCarAndClientAndWorker.job.price)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Start date: " + jobWithCarAndClientAndWorker.job.startDate)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Execution time: " + jobWithCarAndClientAndWorker.job.executionTime)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Car: " + jobWithCarAndClientAndWorker.car?.brand + " " + jobWithCarAndClientAndWorker.car?.model)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Client: " + jobWithCarAndClientAndWorker.client?.name)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Worker: " + jobWithCarAndClientAndWorker.worker?.name)
        }
    }
}
