package com.abizer_r.friendsconnect.timeline

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.abizer_r.friendsconnect.R

@Composable
fun Timeline(
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {
        Text(text = stringResource(id = R.string.timeline))
    }

}