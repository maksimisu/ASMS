package com.maksimisu.asms.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maksimisu.asms.domain.model.Car
import com.maksimisu.asms.domain.model.Client
import com.maksimisu.asms.domain.model.relation.CarWithClient

@Composable
fun CarListItem(carWithClient: CarWithClient, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            },
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
                    text = "${carWithClient.car.brand} ${carWithClient.car.model}",
                    fontWeight = FontWeight.Bold
                )
                Text(text = "VIN: ${carWithClient.car.vin}")
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Year: ${carWithClient.car.year}")
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Owner: ${carWithClient.client?.name}")
        }
    }
}