package com.example.vijay_assignment.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.vijay_assignment.Model.MovieRelease
import com.example.vijay_assignment.ViewModel.HomeViewModel


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val movies by viewModel.movies.collectAsState()
    val tvShows by viewModel.tvShows.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var isMoviesSelected by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Toggle Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ToggleButton(
                selected = isMoviesSelected,
                onSelectedChange = { isMoviesSelected = it }
            )
        }


        if (isLoading) {
            ShimmerEffect()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                val list = if (isMoviesSelected) movies else tvShows
                items(list) { release ->
                    MovieItem(
                        release = release,
                        onClick = {
                            navController.navigate("details/${release.id}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ToggleButton(
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray)
    ) {
        TextButton(
            onClick = { onSelectedChange(true) },
            modifier = Modifier
                .background(if (selected) Color.Cyan else Color.LightGray)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Movies",
                color = if (selected) Color.Black else Color.Gray
            )
        }
        TextButton(
            onClick = { onSelectedChange(false) },
            modifier = Modifier
                .background(if (!selected) Color.Cyan else Color.LightGray)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "TV Shows",
                color = if (!selected) Color.Black else Color.Gray
            )
        }
    }
}

@Composable
fun MovieItem(release: MovieRelease, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = release.poster_url,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = release.title, style = MaterialTheme.typography.bodyMedium)
                release.source_name?.let { Text(text = it, style = MaterialTheme.typography.bodySmall) }
            }
        }
    }
}




