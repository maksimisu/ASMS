package com.maksimisu.asms.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.maksimisu.asms.domain.model.relation.CarWithJobs

@Composable
fun CarWithJobsListItem(carWithJobs: CarWithJobs) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        backgroundColor = Color.White,
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${carWithJobs.car.brand} ${carWithJobs.car.model}",
                    fontWeight = FontWeight.Bold
                )
                Text(text = "VIN: ${carWithJobs.car.vin}")
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Year: ${carWithJobs.car.year}")
            Spacer(modifier = Modifier.height(5.dp))
            Column {
                carWithJobs.jobs?.forEach {
                    Text(text = "Job: ${it.description}")
                }
            }
        }
    }
}