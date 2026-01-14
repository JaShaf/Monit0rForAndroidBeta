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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {
    companion object {
        val MEMBERS_JSON = stringPreferencesKey("members_json")

        val ORG_NAME = stringPreferencesKey("org_name")

        val ORG_DESC = stringPreferencesKey("org_desc")
    }

    val members: Flow<List<TeamMember>> =
        context.dataStore.data.map { prefs ->
            prefs[MEMBERS_JSON]?.let {
                Json.decodeFromString(it)
            } ?: emptyList()
        }

    val orgName: Flow<String> = context.dataStore.data.map {
        it[ORG_NAME] ?: ""
    }

    val orgDesc: Flow<String> = context.dataStore.data.map {
        it[ORG_DESC] ?: ""
    }

    suspend fun saveOrgDetails(name: String, desc: String) {
        context.dataStore.edit {
            it[ORG_NAME] = name
            it[ORG_DESC] = desc
        }
    }


    suspend fun saveMembers(members: List<TeamMember>) {
        context.dataStore.edit { prefs ->
            prefs[MEMBERS_JSON] = Json.encodeToString(members)
        }
    }



}