package app.kbman.view.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun NoticeAlertDialog(text: String, onDismissRequest: () -> Unit = {}) {
    val showDialog = remember {
        mutableStateOf(true)
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
                onDismissRequest()
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog.value = false
                    onDismissRequest()
                }) {
                    Text(text = "확인")
                }
            },
            title = {
                Text(text = "알림")
            },
            text = {
                Text(text = text)
            }
        )
    }
}