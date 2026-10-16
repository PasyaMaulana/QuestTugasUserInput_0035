package com.example.tugaspertemuan5

import androidx.annotation.DimenRes
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
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

@Composable
fun PageIndicator(modifier: Modifier = Modifier) {
    val total = integerResource(R.integer.dot_count)
    val active = integerResource(R.integer.dot_active_index)

    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dot_spacing))
    ) {
        repeat(total) { index ->
            Box(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.dot_size))
                    .clip(CircleShape)
                    .background(
                        colorResource(
                            if (index == active) R.color.dot_active else R.color.dot_inactive
                        )
                    )
            )
        }
    }
}