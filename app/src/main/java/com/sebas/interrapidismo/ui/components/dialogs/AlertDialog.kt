package com.sebas.interrapidismo.ui.components.dialogs


import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sebas.interrapidismo.ui.theme.InterrapidismoTheme

@Composable
fun AlertDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            OutlinedButton(onClick = { onDismiss() }) {
                Text(text = "Confirmar")
            }
        },
        title = {
            Text(title)
        },
        text = {
            Text(text = message)
        },

    )
}

@Preview
@Composable
fun AlertDialogPreview() {
    InterrapidismoTheme {
        AlertDialog(
            "Version",
            "Version mas baja"
        ) {

        }
    }
}