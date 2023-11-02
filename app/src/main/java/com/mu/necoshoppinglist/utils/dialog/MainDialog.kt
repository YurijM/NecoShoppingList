package com.mu.necoshoppinglist.utils.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mu.necoshoppinglist.ui.theme.BlueLight
import com.mu.necoshoppinglist.ui.theme.LightText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDialog(
    dialogController: DialogController
) {
    if (dialogController.openDialog.value) {
        AlertDialog(
            containerColor = BlueLight,
            onDismissRequest = {
                dialogController.onDialogEvent(DialogEvent.OnCancel)
            },
            confirmButton = {
                TextButton(
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.Blue
                    ),
                    onClick = {
                        dialogController.onDialogEvent((DialogEvent.OnOK))
                    }
                ) {
                    Text(
                        text = "OK"
                    )
                }
            },
            dismissButton = {
                TextButton(
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = LightText
                    ),
                    onClick = {
                        dialogController.onDialogEvent((DialogEvent.OnCancel))
                    }
                ) {
                    Text(
                        text = "Отмена"
                    )
                }
            },
            title = null,
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = dialogController.dialogTitle.value,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    if (dialogController.showEditableText.value) {
                        TextField(
                            value = dialogController.editableText.value,
                            onValueChange = { text ->
                                dialogController.onDialogEvent(DialogEvent.OnTextChange(text))
                            },
                            label = {
                                Text(
                                    text = "Название",
                                    fontSize = 16.sp
                                )
                            },
                            singleLine = true,
                            textStyle = TextStyle(
                                fontSize = 20.sp
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                textColor = LightText,
                                focusedIndicatorColor = LightText,
                                cursorColor = LightText,
                                focusedLabelColor = Color.White,
                                unfocusedIndicatorColor = LightText,
                                unfocusedLabelColor = LightText
                            )
                        )
                    }
                }
            }
        )
    }
}