package com.siedlce.erasmusguide.ui.map

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

private val SIEDLCE_CENTER = LatLng(52.1690, 22.2760)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    onNavigateToChecklist: () -> Unit,
    viewModel: MapViewModel = hiltViewModel()
) {
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(SIEDLCE_CENTER, 14f)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Siedlce Erasmus Guide") },
                actions = {
                    IconButton(onClick = onNavigateToChecklist) {
                        Icon(Icons.Default.CheckCircle, contentDescription = "Checklist")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // Category filter chips
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedCategory == null,
                    onClick = { viewModel.filterByCategory(null) },
                    label = { Text("All") }
                )
                viewModel.categories.forEach { category ->
                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = { viewModel.filterByCategory(category) },
                        label = { Text(category.replaceFirstChar { it.uppercase() }) }
                    )
                }
            }

            // Google Map
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings = MapUiSettings(zoomControlsEnabled = true)
            ) {
                viewModel.getFilteredPois().forEach { poi ->
                    Marker(
                        state = MarkerState(position = LatLng(poi.latitude, poi.longitude)),
                        title = poi.name,
                        snippet = poi.description
                    )
                }
            }
        }
    }
}
