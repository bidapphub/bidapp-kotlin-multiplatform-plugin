package io.bidapp.demo.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinmultiplatform.demo.shared.generated.resources.Res
import kotlinmultiplatform.demo.shared.generated.resources.bidapp_logo
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Logo(){
    Image(
        painter = painterResource(Res.drawable.bidapp_logo),
        contentDescription = null,
        modifier = Modifier.size(75.dp, 75.dp)
    )
    Text(
        text = "Bidapp demo",
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}
