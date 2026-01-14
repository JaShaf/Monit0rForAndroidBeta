package com.example.monit0r10forandroid

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.monit0r10forandroid.ui.theme.Monit0r10ForAndroidTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.monit0r10forandroid.ui.theme.Charcoal
import com.example.monit0r10forandroid.ui.theme.MintGreen
import com.example.monit0r10forandroid.ui.theme.OrgDetailsArray
import com.example.monit0r10forandroid.ui.theme.OrganizationField
import com.example.monit0r10forandroid.ui.theme.RoyalBlue
import com.example.monit0r10forandroid.ui.theme.SoftPink
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete


enum class MemberStatus {
    ACTIVE,
    INACTIVE
}

@Serializable
data class TeamMember (
    val name: String,
    val status: MemberStatus,
    val title: String,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusDropdown(
    value: MemberStatus,
    onValueChange: (MemberStatus) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = value.name.lowercase().replaceFirstChar { it.uppercase() },
            onValueChange = {},
            readOnly = true,
            label = { Text("Status") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            MemberStatus.values().forEach { status ->
                DropdownMenuItem(
                    text = {
                        Text(
                            status.name.lowercase()
                                .replaceFirstChar { it.uppercase() }
                        )
                    },
                    onClick = {
                        onValueChange(status)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun MemberCreator(onAddMember: (TeamMember) -> Unit
) {

    var name by remember { mutableStateOf("") }
    var status by remember { mutableStateOf(MemberStatus.ACTIVE) }
    var title by remember { mutableStateOf("") }



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        StatusDropdown(
            value = status,
            onValueChange = { status = it }
        )


        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                onAddMember(
                    TeamMember(
                        name = name,
                        status = status,
                        title = title
                    )
                )

                name = ""
                status = MemberStatus.ACTIVE
                title = ""
            },
            enabled = name.isNotBlank()
        ) {
            Text("Save Member")
        }





    }
}

@Composable
fun MemberRow(
    member: TeamMember,
    onActionClick: (TeamMember) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Red)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column (
            modifier = Modifier.weight(1f)
        ) {
            Text(text = member.name)
            Text(
                text = member.status.name.lowercase()
                    .replaceFirstChar { it.uppercase() }
            )
            Text(
                text = member.title,
                style = MaterialTheme.typography.bodySmall
            )
        }

        IconButton(
            onClick = { onActionClick(member) }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null
            )
        }


    }
}

@Composable
fun MembersList(
    members: List<TeamMember>,
    onRemove: (TeamMember) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(members) { member ->
            MemberRow(
                member = member,
                onActionClick = onRemove
            )

        }
    }
}

@Composable
fun MemberCount(
    count: Int
){
    OutlinedTextField(
        value = count.toString(),
        onValueChange = {},
        readOnly = true,
        label = { Text("Count") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}


