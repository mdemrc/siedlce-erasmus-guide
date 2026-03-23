package com.siedlce.erasmusguide.ui.checklist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

private val categoryLabels = mapOf(
    "before_arrival" to "Before Arrival",
    "arrival" to "First Days",
    "settling" to "Settling In",
    "ongoing" to "Ongoing"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChecklistScreen(
    onNavigateBack: () -> Unit,
    viewModel: ChecklistViewModel = hiltViewModel()
) {
    val checkedIds by viewModel.checkedIds.collectAsState()
    val itemsByCategory = viewModel.getItemsByCategory()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Erasmus Checklist") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsByCategory.forEach { (category, items) ->
                item {
                    Text(
                        text = categoryLabels[category] ?: category,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
                    )
                }
                items(items, key = { it.id }) { checklistItem ->
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Top
                        ) {
                            Checkbox(
                                checked = checkedIds.contains(checklistItem.id),
                                onCheckedChange = { viewModel.toggleItem(checklistItem.id) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = checklistItem.title,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = checklistItem.description,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
