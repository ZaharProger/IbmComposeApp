package com.example.ibmcomposeapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ibmcomposeapp.R
import com.example.ibmcomposeapp.constants.ValidationTypes
import com.example.ibmcomposeapp.entities.UserParams
import com.example.ibmcomposeapp.entities.ValidationCase
import com.example.ibmcomposeapp.ui.theme.*
import java.util.*
import kotlin.math.pow

@Composable
fun AppForm() {
    var userParams by remember { mutableStateOf(UserParams("", "")) }
    var calculationResult by remember { mutableStateOf(0.0) }

    val validate = { validationCases: List<ValidationCase> ->
        var isCorrect = true
        run loop@{
            validationCases.forEach { validationCase ->
                if (!validationCase.data.matches(validationCase.case.regex)) {
                    isCorrect = false
                    return@loop
                }
            }
        }

        isCorrect
    }

    val calculateIbm = {
        val validationCases = listOf(
            ValidationCase(userParams.weight, ValidationTypes.VALIDATE_WEIGHT),
            ValidationCase(userParams.height, ValidationTypes.VALIDATE_HEIGHT)
        )

        calculationResult = if (validate(validationCases)) {
            userParams.weight.toDouble() / (userParams.height.toInt() / 100.0).pow(2.0)
        } else {
            0.0
        }

        calculationResult = "%.2f".format(Locale.US, calculationResult).toDouble()
    }

    val inputColors = TextFieldDefaults.textFieldColors(
        textColor = TextColor,
        backgroundColor = Transparent,
        placeholderColor = HintColor,
        leadingIconColor = PrimaryColor,
        focusedIndicatorColor = PrimaryColor,
        unfocusedIndicatorColor = IndicatorLineColor
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(value = userParams.weight,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 5.dp),
            placeholder = { Text(text = stringResource(id = R.string.weight_hint)) },
            singleLine = true,
            textStyle = Typography.body1,
            colors = inputColors,
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_weight),
                    contentDescription = "Weight input"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            shape = Shapes.small,
            onValueChange = { newWeight: String -> userParams = userParams.copy(
                weight = newWeight,
                height = userParams.height) }
        )

        TextField(value = userParams.height,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 5.dp)
                .offset(0.dp, (3).dp),
            placeholder = { Text(text = stringResource(id = R.string.height_hint)) },
            singleLine = true,
            textStyle = Typography.body1,
            colors = inputColors,
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_height),
                    contentDescription = "Height input"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            shape = Shapes.small,
            onValueChange = { newHeight: String -> userParams = userParams.copy(
                weight = userParams.weight,
                height = newHeight) }
        )

        Button(
            onClick = { calculateIbm() },
            modifier = Modifier
                .padding(10.dp)
                .offset(0.dp, (24).dp),
            shape = Shapes.small,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = SecondaryColor,
                contentColor = AppBackground
            )
        ) {

            Text(
                text = stringResource(id = R.string.calculate_button_text),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp
            )
        }

        if (calculationResult != 0.0) {
            Text(
                text = "${stringResource(id = R.string.result_text)}: $calculationResult",
                modifier = Modifier
                    .offset(0.dp, (25).dp),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = PrimaryColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppFormPreview() {
    IbmComposeAppTheme {
        AppForm()
    }
}