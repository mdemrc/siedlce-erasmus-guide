package com.siedlce.erasmusguide.ui.map

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.siedlce.erasmusguide.R
import com.siedlce.erasmusguide.data.model.Poi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PoiDetailSheet(
    poi: Poi,
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            poi.imageUrl?.takeIf { it.isNotBlank() }?.let { url ->
                AsyncImage(
                    model = url,
                    contentDescription = poi.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }

            Text(
                text = poi.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )

            AssistChip(
                onClick = {},
                enabled = false,
                label = { Text(poiCategoryLabel(poi.category)) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Category,
                        contentDescription = stringResource(R.string.poi_detail_category_cd),
                        modifier = Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            )

            DetailRow(
                icon = {
                    Icon(
                        Icons.Default.Place,
                        contentDescription = stringResource(R.string.poi_detail_address_cd)
                    )
                },
                primary = poi.address,
                onClick = { openMaps(context, poi) }
            )

            poi.phone?.takeIf { it.isNotBlank() }?.let { phone ->
                DetailRow(
                    icon = {
                        Icon(
                            Icons.Default.Phone,
                            contentDescription = stringResource(R.string.poi_detail_phone_cd)
                        )
                    },
                    primary = phone,
                    onClick = {
                        context.startActivity(Intent(Intent.ACTION_DIAL, "tel:$phone".toUri()))
                    }
                )
            }

            poi.website?.takeIf { it.isNotBlank() }?.let { url ->
                DetailRow(
                    icon = {
                        Icon(
                            Icons.Default.Public,
                            contentDescription = stringResource(R.string.poi_detail_website_cd)
                        )
                    },
                    primary = url,
                    onClick = {
                        context.startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
                    }
                )
            }

            poi.openingHours?.takeIf { it.isNotBlank() }?.let { hours ->
                DetailRow(
                    icon = {
                        Icon(
                            Icons.Default.Schedule,
                            contentDescription = stringResource(R.string.poi_detail_hours_cd)
                        )
                    },
                    primary = hours
                )
            }

            Text(
                text = poi.description,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun DetailRow(
    icon: @Composable () -> Unit,
    primary: String,
    onClick: (() -> Unit)? = null
) {
    val baseModifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
    val finalModifier = if (onClick != null) baseModifier.clickable(onClick = onClick) else baseModifier

    Row(
        modifier = finalModifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        icon()
        Text(
            text = primary,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

/**
 * Opens the platform map application centered on [poi]. Uses the standard
 * `geo:` URI so any installed maps app handles the intent.
 */
private fun openMaps(context: Context, poi: Poi) {
    val uri = "geo:${poi.latitude},${poi.longitude}?q=${Uri.encode(poi.name)}".toUri()
    context.startActivity(Intent(Intent.ACTION_VIEW, uri))
}
