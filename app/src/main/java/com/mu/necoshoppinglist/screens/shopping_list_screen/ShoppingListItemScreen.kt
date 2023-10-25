package com.mu.necoshoppinglist.screens.shopping_list_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mu.necoshoppinglist.data.entity.ShoppingListItemEntity
import com.mu.necoshoppinglist.ui.theme.BlueLight
import com.mu.necoshoppinglist.ui.theme.BlueMain
import com.mu.necoshoppinglist.ui.theme.LightText
import com.mu.necoshoppinglist.utils.Routes

@Composable
fun ShoppingListItemScreen(
    item: ShoppingListItemEntity,
    onEvent: (ShoppingListEvent) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 20.dp,
                start = 4.dp,
                end = 4.dp
            )
    ) {
        val (card, rowButton) = createRefs()
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(card) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(bottom = 4.dp)
                .clickable {
                    onEvent(
                        ShoppingListEvent.OnItemClick(
                            Routes.ADD_ITEM_LIST + "/${item.id}"
                        )
                    )
                },
            colors = CardDefaults.cardColors(
                containerColor = BlueLight,
                contentColor = BlueMain
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = item.name,
                    style = TextStyle(
                        //color = DarkText,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )
                Text(
                    text = item.time,
                    style = TextStyle(
                        color = LightText,
                        fontSize = 16.sp
                    )
                )
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    color = BlueMain,
                    trackColor = Color.LightGray,
                    progress = if (item.allItemsCount == 0) {
                        0f
                    } else {
                        item.allSelectedItemsCount.toFloat() / item.allItemsCount.toFloat()
                    }
                )
            }
        }
        Row(
            modifier = Modifier
                //.wrapContentWidth()
                .padding(end = 12.dp)
                .constrainAs(rowButton) {
                    top.linkTo(card.top)
                    bottom.linkTo(card.top)
                    end.linkTo(card.end)
                },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = BlueMain,
                    contentColor = Color.White
                )
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "${item.allItemsCount} / ${item.allSelectedItemsCount}"
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(28.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Green,
                    contentColor = Color.White
                ),
                onClick = {
                    onEvent(ShoppingListEvent.OnShowEditDialog(item))
                }
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Delete"
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(28.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                onClick = {
                    onEvent(ShoppingListEvent.OnShowDeleteDialog(item))
                }
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }
        }
    }
}