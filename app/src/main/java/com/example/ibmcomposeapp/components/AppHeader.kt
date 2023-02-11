package com.example.ibmcomposeapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ibmcomposeapp.R
import com.example.ibmcomposeapp.ui.theme.IbmComposeAppTheme
import com.example.ibmcomposeapp.ui.theme.PrimaryColor

@Composable
fun AppHeader() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .offset(0.dp, (-15).dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_logo),
            contentDescription = "App logo",
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, (-32).dp))

        Text(text = stringResource(id = R.string.app_header),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp, color = PrimaryColor)
    }
}

@Preview(showBackground = true)
@Composable
fun AppHeaderPreview() {
    IbmComposeAppTheme {
        AppHeader()
    }
}