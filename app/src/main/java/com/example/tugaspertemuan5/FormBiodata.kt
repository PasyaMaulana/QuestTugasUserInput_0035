package com.example.tugaspertemuan5

import android.text.format.DateUtils
import androidx.annotation.DimenRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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

@Composable
fun FormHeader(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space_small))
    ) {
        Text(
            text = stringResource(R.string.title_form_biodata),
            fontSize = spResource(R.dimen.text_title),
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.brand_purple)
        )
        Text(
            text = stringResource(R.string.subtitle_form_biodata),
            fontSize = spResource(R.dimen.text_subtitle),
            color = colorResource(R.color.text_subtitle)
        )
    }
}

@Composable
fun NamaRow(
    namaDepan: String,
    namaBelakang: String,
    onNamaDepanChange: (String) -> Unit,
    onNamaBelakangChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val weight = integerResource(R.integer.weight_equal).toFloat()

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space_medium))
    ) {
        FormTextField(
            value = namaDepan,
            onValueChange = onNamaDepanChange,
            label = stringResource(R.string.label_nama_depan),
            modifier = Modifier.weight(weight)
        )
        FormTextField(
            value = namaBelakang,
            onValueChange = onNamaBelakangChange,
            label = stringResource(R.string.label_nama_belakang),
            modifier = Modifier.weight(weight)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateField(
    value: String,
    onDateSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    LaunchedEffect (isPressed) {
        if (isPressed) showDialog = true
    }

    FormTextField(
        value = value,
        onValueChange = {},
        label = stringResource(R.string.label_tgl_lahir),
        readOnly = true,
        interactionSource = interactionSource,
        modifier = modifier,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = stringResource(R.string.cd_pilih_tanggal)
            )
        }
    )

    if (showDialog) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog (
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton (
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            onDateSelected(
                                DateUtils.formatDateTime(
                                    context,
                                    millis,
                                    DateUtils.FORMAT_SHOW_DATE or
                                            DateUtils.FORMAT_SHOW_YEAR or
                                            DateUtils.FORMAT_NUMERIC_DATE or
                                            DateUtils.FORMAT_UTC
                                )
                            )
                        }
                        showDialog = false
                    }
                ) { Text(text = stringResource(R.string.btn_ok)) }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = stringResource(R.string.btn_batal))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExperienceDropdown(
    selected: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val options = stringArrayResource(R.array.experience_options)
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox (
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        FormTextField(
            value = selected,
            onValueChange = {},
            label = stringResource(R.string.label_experience),
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun SkillCheckbox(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = colorResource(R.color.brand_purple),
                uncheckedColor = colorResource(R.color.field_border),
                checkmarkColor = colorResource(R.color.white)
            )
        )
        Text(
            text = label,
            fontSize = spResource(R.dimen.text_small),
            color = colorResource(R.color.text_primary)
        )
    }
}

@Composable
fun SkillSection(
    android: Boolean,
    uiux: Boolean,
    web: Boolean,
    onAndroidChange: (Boolean) -> Unit,
    onUiuxChange: (Boolean) -> Unit,
    onWebChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space_small))
    ) {
        Text(
            text = stringResource(R.string.label_pilih_skill),
            fontSize = spResource(R.dimen.text_label),
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.text_primary)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SkillCheckbox(stringResource(R.string.skill_android), android, onAndroidChange)
            SkillCheckbox(stringResource(R.string.skill_uiux), uiux, onUiuxChange)
            SkillCheckbox(stringResource(R.string.skill_web), web, onWebChange)
        }
    }
}

@Composable
fun ProceedButton(
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button (
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.button_height)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner)),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.button_enabled),
            contentColor = colorResource(R.color.white),
            disabledContainerColor = colorResource(R.color.button_disabled_container),
            disabledContentColor = colorResource(R.color.button_disabled_content)
        )
    ) {
        Text(
            text = stringResource(R.string.btn_proceed),
            fontSize = spResource(R.dimen.text_button),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun FormBiodataScreen(modifier: Modifier = Modifier) {
    val cardShape = RoundedCornerShape(
        topStart = dimensionResource(R.dimen.card_corner),
        topEnd = dimensionResource(R.dimen.card_corner)
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = colorResource(R.color.screen_background)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(R.drawable.bg_tiumy),
                contentDescription = stringResource(R.string.cd_header_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.header_height))
            )
            PageIndicator(
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.dot_padding_start),
                    top = dimensionResource(R.dimen.dot_padding_top)
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = dimensionResource(R.dimen.box_top_offset))
                    .shadow(
                        elevation = dimensionResource(R.dimen.card_elevation),
                        shape = cardShape
                    )
                    .clip(cardShape)
                    .background(colorResource(R.color.card_background))
            )
        }
    }
}