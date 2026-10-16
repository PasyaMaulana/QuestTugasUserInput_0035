package com.example.tugaspertemuan5

import androidx.annotation.DimenRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.TextUnit

@Composable
fun spResource(@DimenRes id: Int): TextUnit {
    val px = LocalContext.current.resources.getDimension(id)
    return with(LocalDensity.current) { px.toSp() }
}

@Composable
fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: (@Composable () -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        readOnly = readOnly,
        singleLine = true,
        label = { Text(text = label) },
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        interactionSource = interactionSource,
        shape = RoundedCornerShape(dimensionResource(R.dimen.field_corner)),
        textStyle = LocalTextStyle.current.copy(fontSize = spResource(R.dimen.text_body)),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(R.color.brand_purple),
            unfocusedBorderColor = colorResource(R.color.field_border),
            focusedLabelColor = colorResource(R.color.brand_purple),
            unfocusedLabelColor = colorResource(R.color.field_label),
            cursorColor = colorResource(R.color.brand_purple),
            focusedTextColor = colorResource(R.color.text_primary),
            unfocusedTextColor = colorResource(R.color.text_primary)
        )
    )
}