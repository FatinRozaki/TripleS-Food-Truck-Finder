<?php
header('Content-Type: application/json');

// Database connection parameters
$servername = "localhost";
$username = "your_username";
$password = "your_password";
$dbname = "your_database";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// SQL query to fetch food truck data
$sql = "SELECT id, name, location, cuisine FROM food_trucks";
$result = $conn->query($sql);

$food_trucks = array();

if ($result->num_rows > 0) {
    // Output data of each row
    while($row = $result->fetch_assoc()) {
        $food_trucks[] = $row;
    }
} else {
    echo json_encode([]);
}
$conn->close();

// Output the data as JSON
echo json_encode($food_trucks);
?>
