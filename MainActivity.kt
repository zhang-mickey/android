package com.aladin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aladin.ui.theme.AladinTheme
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class LeaveViewModel : ViewModel() {
    val leaveRequests: SnapshotStateList<LeaveRequest> = mutableStateListOf()

    fun addLeaveRequest(leaveRequest: LeaveRequest) {
        leaveRequests.add(leaveRequest)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AladinTheme {
                val navController = rememberNavController()
                Surface(color = Color.White) {
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") { LoginScreen(navController) }
                        composable("adminHome") { ScaffoldDemo2(navController) }
                        composable("employeeHome") { ScaffoldDemo(navController) }

                    }
                }

            }
        }
    }
}

@Composable
fun AdminHomeScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Welcome admin！",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(16.dp)
            )
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Logout")
            }
        }
    }
}

@Composable
fun EmployeeHomeScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "欢迎员工！",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(16.dp)
            )
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Logout")
            }
            SignInModule()
        }
        // Employee-specific content here
    }
}

//
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(msg = message)
        }
    }
}

@Composable
fun ScaffoldDemo(navController: NavHostController){
    var selectedItem by remember { mutableStateOf(0) }
    val viewModel: LeaveViewModel = viewModel()  // Get ViewModel instance
    val items = listOf("Dashboard", "Notification", "MY team","Regulations")
    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                // Add a suitable icon here
                                imageVector = when (item) {
                                    "Dashboard" -> Icons.Default.Home
                                    "Notification" -> Icons.Default.Notifications
                                    "MY team" -> Icons.Default.People
                                    "Regulations" -> Icons.Default.Info
                                    else -> Icons.Default.RemoveRedEye
                                },
                                contentDescription = item
                            )
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ){ paddingValues ->
        // Adding Conversation inside the Scaffold's content with padding
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedItem) {
                0 -> EmployeeHomeScreen(navController)
                1 -> Conversation(messages = MsgData.messages)
                2 -> TeamScreen()
                3 -> RegulationScreen()
            }
        }
    }
}

@Composable
fun ScaffoldDemo2(navController: NavHostController){
    var selectedItem by remember { mutableStateOf(0) }
    val viewModel: LeaveViewModel = viewModel()  // Get ViewModel instance
    val items = listOf("Dashboard", "Status", "Add employee","Form")
    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                // Add a suitable icon here
                                imageVector = when (item) {
                                    "Dashboard" -> Icons.Default.Home
                                    "Status" -> Icons.Default.Schedule
                                    "Add employee" -> Icons.Default.People
                                    "Form" -> Icons.Default.FactCheck
                                    else -> Icons.Default.RemoveRedEye
                                },
                                contentDescription = item
                            )
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ){ paddingValues ->
        // Adding Conversation inside the Scaffold's content with padding
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedItem) {
                0 -> AdminHomeScreen(navController)
                1 -> EmployeeAttendance()
                2 -> EmployeeFormScreen()
                3 -> SummitFormScreen()
                else -> LeaveModule(viewModel)
            }
        }
    }
}

data class LeaveRequest(val employeeName: String, val leaveReason: String)


@Composable
fun SummitFormScreen(viewModel: LeaveViewModel = viewModel()) {
    val leaveRequests = viewModel.leaveRequests

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(leaveRequests) { leaveRequest ->
            LeaveRequestItem(leaveRequest)
        }
    }
}

@Composable
fun LeaveRequestItem(leaveRequest: LeaveRequest) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Employee: ${leaveRequest.employeeName}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Reason: ${leaveRequest.leaveReason}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun EmployeeFormScreen(){
    var employeeName by remember { mutableStateOf("") }
    var attendanceStatus by remember { mutableStateOf("Present") }
    var employees by remember { mutableStateOf(listOf(
        EmployeeAttendance("Alice", "Present"),
        EmployeeAttendance("Bob", "Absent"),
        EmployeeAttendance("Charlie", "Present"),
        EmployeeAttendance("David", "On Leave"),
        EmployeeAttendance("Emma", "Present")
    )) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Employee Attendance",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        EmployeeForm(
            employeeName = employeeName,
            onEmployeeNameChange = { employeeName = it },
            attendanceStatus = attendanceStatus,
            onAttendanceStatusChange = { attendanceStatus = it },
            onAddEmployee = {
                employees = employees + EmployeeAttendance(employeeName, attendanceStatus)
                employeeName = ""
                attendanceStatus = "Present"
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(employees) { employee ->
                EmployeeAttendanceItem(employee)
            }
        }
    }
}
@Composable
fun EmployeeForm(
    employeeName: String,
    onEmployeeNameChange: (String) -> Unit,
    attendanceStatus: String,
    onAttendanceStatusChange: (String) -> Unit,
    onAddEmployee: () -> Unit
) {
    Column {
        OutlinedTextField(
            value = employeeName,
            onValueChange = onEmployeeNameChange,
            label = { Text("Employee Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = attendanceStatus,
            onValueChange = onAttendanceStatusChange,
            label = { Text("Attendance Status") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onAddEmployee) {
            Text("Add Employee")
        }
    }
}





data class EmployeeAttendance(val name: String, val attendanceStatus: String)

@Composable
fun EmployeeAttendance()
{
    val employees = listOf(
        EmployeeAttendance("Alice", "Present"),
        EmployeeAttendance("Bob", "Absent"),
        EmployeeAttendance("Charlie", "Present"),
        EmployeeAttendance("David", "On Leave"),
        EmployeeAttendance("Emma", "Present")
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Employee Attendance",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(employees) { employee ->
                    EmployeeAttendanceItem(employee)
                }
            }
        }
    }
}
@Composable
fun EmployeeAttendanceItem(employee: EmployeeAttendance) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = employee.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = employee.attendanceStatus,
                style = MaterialTheme.typography.bodyLarge,
                color = when (employee.attendanceStatus) {
                    "Present" -> Color.Green
                    "Absent" -> Color.Red
                    "On Leave" -> Color.Yellow
                    else -> Color.Black
                }
            )
        }
    }
}

@Composable
fun LoginScreen(navController: NavHostController) {
     var username by remember { mutableStateOf("") }
     var password by remember { mutableStateOf("") }

     Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    modifier = Modifier.padding(16.dp)
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.padding(16.dp)
                )
                Button(
                    onClick = {
                        when {
                            username == "admin" && password == "1" -> {
                                navController.navigate("adminHome")
                            }
                            username == "employee" && password == "2" -> {
                                navController.navigate("employeeHome")
                            }
                        }
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Login")
            }
        }
     }
}


@Composable
fun SignInModule() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Attendance",
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = { /* Handle check-in button click */ }) {
                    Text("Check In")
                }
                Button(onClick = { /* Handle check-out button click */ }) {
                    Text("Check Out")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LeaveModule()
        }
    }
}

@Composable
fun LeaveModule(viewModel: LeaveViewModel = viewModel()) {
    var leaveReason by remember { mutableStateOf("") }
    var employeeName by remember { mutableStateOf("Employee") } // Assuming you have a way to get the employee name

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Leave Request",
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = leaveReason,
                onValueChange = { leaveReason = it },
                label = { Text("Leave Reason") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                viewModel.addLeaveRequest(LeaveRequest(employeeName, leaveReason))
                leaveReason = ""
            }) {
                Text("Submit Leave")
            }
        }
    }
}


@Composable
fun TeamScreen() {
    val teamMembers = listOf("Alice", "Bob", "Charlie", "David", "Emma")

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(teamMembers) { member ->
                TeamMemberItem(name = member)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun TeamMemberItem(name: String) {
    Text(text = "Team Member: $name")
}


@Composable
fun RegulationScreen() {
    val companyRules = listOf(
        "Rule 1: Maintain a professional attitude at all times.",
        "Rule 2: Be punctual and respect deadlines.",
        "Rule 3: Ensure clear and effective communication.",
        "Rule 4: Follow all safety protocols.",
        "Rule 5: Respect company property and resources.",
        "Rule 6: Maintain confidentiality of sensitive information.",
        "Rule 7: Adhere to the company's code of conduct.",
        "Rule 8: Collaborate and work as a team.",
        "Rule 9: Continuously improve and seek feedback.",
        "Rule 10: Uphold the company's values and mission."
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(companyRules) { rule ->
                Text(
                    text = rule,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    var isExpanded by remember { mutableStateOf(false) } // 创建一个能够检测卡片是否被展开的变量
    val surfaceColor by animateColorAsState(
        targetValue = if (isExpanded) Color(0xFFCCCCCC) else MaterialTheme.colorScheme.surface
    )
    Surface(
        shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
        tonalElevation= 5.dp,
        modifier = Modifier
            .padding(all = 8.dp)
            .clickable {
                isExpanded = !isExpanded
            }
    )
    {
    Row(
        modifier = Modifier.padding(all = 8.dp)
    ) {
        Image(
            painterResource(id = R.drawable.profile_picture),
            contentDescription = "profile picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, shape = CircleShape) // 添加边框
        )
        Spacer(Modifier.padding(horizontal = 8.dp))
        Column {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary, // 添加颜色
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.padding(vertical = 4.dp))
            Text(
                text = msg.body,
                maxLines = if (isExpanded) Int.MAX_VALUE else 1,

                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.animateContentSize()
            )
        }
        }
    }
}

object MsgData {
    private const val author = "username"
    val messages = listOf(
        Message(author, "Emergence"),
        Message(author, "The business"),
        Message(author, "！！"),
        Message(author, "Date"),
        Message(author, "Approved"),
        Message(author, "伤心尽处露笑颜，醉里孤单写狂欢。两路殊途情何奈，三千弱水忧忘川。花开彼岸朦胧色，月过长空爽朗天。青鸟思飞无侧羽，重山万水亦徒然"),
        Message(author, "又到绿杨曾折处，不语垂鞭，踏遍清秋路。衰草连天无意绪，雁声远向萧关去。恨天涯行役苦，只恨西风，吹梦成今古。明日客程还几许，沾衣况是新寒雨"),
    )
}



