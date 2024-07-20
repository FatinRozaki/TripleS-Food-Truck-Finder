const express = require('express');
const mysql = require('mysql');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
const port = 3000;

// Middleware
app.use(cors()); // Use cors
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// MySQL Connection
const db = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'NadiaAZ07@ab',
    database: 'food_truck_mapper',
        // Add the following line to resolve authentication mode issue
    authPlugin: 'mysql_native_password' // or 'caching_sha2_password' based on your configuration
});

db.connect((err) => {
    if (err) {
        throw err;
    }
    console.log('Connected to MySQL database');
});

// CRUD API Endpoints
// Example: Create (POST) a new food truck
app.post('/api/foodtrucks', (req, res) => {
    const { name, location, latitude, longitude, operation_hour, menus } = req.body;
    const insertQuery = `INSERT INTO food_trucks (name, location, latitude, longitude, operation_hour, menus) VALUES (?, ?, ?, ?, ?, ?)`;
    db.query(insertQuery, [name, location, latitude, longitude, operation_hour, menus], (err, result) => {
        if (err) {
            console.error(err);
            res.status(500).send('Error inserting new food truck');
        } else {
            console.log('New food truck added:', result.insertId);
            res.status(201).send('Food truck added successfully');
        }
    });
});

// Example: Read (GET) all food trucks
app.get('/api/foodtrucks', (req, res) => {
    const selectQuery = `SELECT * FROM food_trucks`;
    db.query(selectQuery, (err, results) => {
        if (err) {
            console.error(err);
            res.status(500).send('Error retrieving food trucks');
        } else {
            res.json(results);
        }
    });
});

// Example: Update (PUT) a food truck
app.put('/api/foodtrucks/:id', (req, res) => {
    const { id } = req.params;
    const { name, location, latitude, longitude, operation_hour, menus } = req.body;
    const updateQuery = `UPDATE food_trucks SET name=?, location=?, latitude=?, longitude=?, operation_hour=?, menus=? WHERE id=?`;
    db.query(updateQuery, [name, location, latitude, longitude, operation_hour, menus, id], (err, result) => {
        if (err) {
            console.error(err);
            res.status(500).send(`Error updating food truck with id ${id}`);
        } else {
            res.status(200).send(`Food truck with id ${id} updated successfully`);
        }
    });
});

// Example: Delete (DELETE) a food truck
app.delete('/api/foodtrucks/:id', (req, res) => {
    const { id } = req.params;
    const deleteQuery = `DELETE FROM food_trucks WHERE id=?`;
    db.query(deleteQuery, [id], (err, result) => {
        if (err) {
            console.error(err);
            res.status(500).send(`Error deleting food truck with id ${id}`);
        } else {
            res.status(200).send(`Food truck with id ${id} deleted successfully`);
        }
    });
});

// Start server
app.listen(port, () => {
    console.log(`Server running on http://localhost:${port}`);
});
