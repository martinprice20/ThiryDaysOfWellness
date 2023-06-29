package com.example.thirtydaysofwellness

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirtydaysofwellness.model.WellnessRepository
import com.example.thirtydaysofwellness.model.WellnessTip

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WellnessTipList(
    tipList: List<WellnessTip>,
    modifier: Modifier = Modifier
) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = modifier
    ) {
        LazyColumn {
            itemsIndexed(tipList) {index, tip ->
                WellnessTipCard(
                    tip = tip,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) }
                            )
                        )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessTipCard(tip: WellnessTip, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(tip.dayRes),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(tip.titleRes),
                style = MaterialTheme.typography.displayMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(15.dp)),
                painter = painterResource(tip.imgRes),
                contentDescription = null,
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.FillWidth,
            )
            Spacer(Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                TipItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
                if (expanded) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(tip.msgRes),
                        style = MaterialTheme.typography.bodyLarge
                    )
                } else {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.read_more),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

        }
    }
}


@Composable
fun TipItemButton( expanded: Boolean,
                   onClick: () -> Unit,
                   modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowRight,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun WellnessTipMsg() {

}

@Preview
@Composable
fun CardPreview() {
    WellnessTipList(tipList = WellnessRepository.wellnessTipList)
}