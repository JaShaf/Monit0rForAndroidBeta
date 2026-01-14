package com.example.monit0r10forandroid.ui.theme

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.monit0r10forandroid.MemberCount
import com.example.monit0r10forandroid.ui.theme.Charcoal
import com.example.monit0r10forandroid.ui.theme.MintGreen
import com.example.monit0r10forandroid.ui.theme.RoyalBlue
import com.example.monit0r10forandroid.ui.theme.SoftPink
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.monit0r10forandroid.TeamMember
import com.example.monit0r10forandroid.UserPreferences
import kotlinx.serialization.Serializable

@Composable
fun OrganizationField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text("Unspecified") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}

@Composable
fun EmployerNameField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text("Unspecified") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrgTypeDropdown(
    value: String,
    options: List<String>,
    onValueChange: (String) -> Unit
){
    var expanded by remember {mutableStateOf(false)}

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {expanded = !expanded}
    ) {

        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            label = { Text("Org Type") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false}
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    }

                )
            }
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeTypeDropdown(
    value: String,
    options: List<String>,
    onValueChange: (String) -> Unit
){

    var expanded by remember {mutableStateOf(false)}

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {expanded = !expanded}
    ) {

        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            label = { Text("Employee Type") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false}
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    }

                )
            }
        }

    }


}





@Composable
fun OrgDetailsArray(userPrefs: UserPreferences) {

    val scope = rememberCoroutineScope()

    // Load persisted values
    val orgName by userPrefs.orgName.collectAsState(initial = "")
    val orgDesc by userPrefs.orgDesc.collectAsState(initial = "")
    val members by userPrefs.members.collectAsState(initial = emptyList())

    // Editable UI state (keyed so it refreshes correctly)
    var organization by remember(orgName) { mutableStateOf(orgName) }
    var employername by remember(orgDesc) { mutableStateOf(orgDesc) }
    var orgType by remember { mutableStateOf("") }
    var employeetype by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(vertical = 100.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {

        Column(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(55.dp)
        ) {
            Text("Organization Name:")
            Text("Employer Name:")
            Text("Type of Org:")
            Text("Type of Employees:")
            Text(
                "Number of Employees/Players:",
                modifier = Modifier.padding(vertical = 20.dp)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            OrganizationField(
                value = organization,
                onValueChange = {
                    organization = it
                    scope.launch {
                        userPrefs.saveOrgDetails(
                            name = organization,
                            desc = employername
                        )
                    }
                }
            )

            EmployerNameField(
                value = employername,
                onValueChange = {
                    employername = it
                    scope.launch {
                        userPrefs.saveOrgDetails(
                            name = organization,
                            desc = employername
                        )
                    }
                }
            )

            OrgTypeDropdown(
                value = orgType,
                options = listOf(
                    "Generic",
                    "Educational",
                    "Government",
                    "Contractor",
                    "Non-Profit",
                    "Political"
                ),
                onValueChange = { orgType = it }
            )

            EmployeeTypeDropdown(
                value = employeetype,
                options = listOf(
                    "Generic",
                    "Teacher",
                    "Player",
                    "Government Worker",
                    "Contract",
                    "Non-Profit Employee",
                    "Non-Profit Volunteer",
                    "Political Worker"
                ),
                onValueChange = { employeetype = it }
            )

            MemberCount(count = members.size)
        }
    }
}