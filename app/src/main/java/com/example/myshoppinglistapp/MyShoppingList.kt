package com.example.myshoppinglistapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment


import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyShoppingListApp() {

    var sItems = remember { mutableStateOf(listOf<ShoppingList>()) }

    val isAlertDialog = remember {
        mutableStateOf(false)
    }

    var itemName = remember {
        mutableStateOf("")
    }

    var itemQuantity = remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            isAlertDialog.value = true
        }) {
            Text(text = "Add Items")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            items(sItems.value) { item ->
                if (item.isEditing) {
                    ShoppingItemEditor(item = item, onEditComplete = { editName, editQuantity ->
                        sItems.value = sItems.value.map {
                            if (it.id == item.id) it.copy(
                                itemName = editName,
                                itemQuantity = editQuantity,
                                isEditing = false
                            ) else it
                        }
                    })
                } else {
                    AddItemsInShoppingList(itemName = item,
                        onEditClick = {
                            sItems.value = sItems.value.map {
                                if (it.id == item.id) it.copy(isEditing = true) else it
                            }
                        },
                        onDeleteClick = {
                            sItems.value = sItems.value.filter { it.id != item.id }
                        })
                }
            }
        }
    }

    if (isAlertDialog.value) {
        AlertDialog(
            onDismissRequest = {
                isAlertDialog.value = false
            },
            title = { Text(text = "Add Items-AlertDialog") },
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName.value,
                        onValueChange = {
                            itemName.value = it
                        },
                        label = { Text(text = "Enter item name:") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(value = itemQuantity.value, onValueChange = {
                        itemQuantity.value = it
                    },
                        label = { Text(text = "Enter item quantity:") })
                }
            },
            confirmButton = {
                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Button(onClick = {
                        if (itemName.value.isNotBlank()) {
                            val newItem = ShoppingList(
                                id = sItems.value.size + 1,
                                itemName = itemName.value,
                                itemQuantity = itemQuantity.value.toInt(),
                                isEditing = false
                            )
                            sItems.value += newItem
                            isAlertDialog.value = false
                            itemName.value = ""
                            itemQuantity.value = ""
                        }
                    }) {
                        Text(text = "Ok")
                    }
                    Button(onClick = {
                        isAlertDialog.value = false
                    }) {
                        Text(text = "Cancel")
                    }
                }
            }
        )
    }

}


@Composable
fun ShoppingItemEditor(item: ShoppingList, onEditComplete: (String, Int) -> Unit) {
    var editedName by remember { mutableStateOf(item.itemName) }
    var editedQuantity by remember { mutableStateOf(item.itemQuantity.toString()) }
    var isEditing by remember { mutableStateOf(item.isEditing) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    )
    {
        Column {
            BasicTextField(
                value = editedName,
                onValueChange = { editedName = it },
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
            BasicTextField(
                value = editedQuantity,
                onValueChange = { editedQuantity = it },
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
        }

        Button(
            onClick = {
                isEditing = false
                onEditComplete(editedName, editedQuantity.toIntOrNull() ?: 1)
            }
        ) {
            Text("Save")
        }
    }
}

@Composable
fun AddItemsInShoppingList(
    itemName: ShoppingList,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, Color(0XFF018786)),
                shape = RoundedCornerShape(20)
            )
    ) {
        Text(text = itemName.itemName, modifier = Modifier.padding(8.dp))
        Text(
            text = "Quantity: " + itemName.itemQuantity.toString(),
            modifier = Modifier.padding(8.dp)
        )
        Row(modifier = Modifier.padding(8.dp)) {
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}
