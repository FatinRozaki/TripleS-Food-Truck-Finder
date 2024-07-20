<?php
$link = new mysqli("localhost", "root", "", "food_truck_mapper");

// Check connection
if ($link->connect_error) {
    die("Connection failed: " . $link->connect_error);
}
?>
