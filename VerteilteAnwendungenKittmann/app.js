const express = require('express');
const path = require('path');
const bodyParser = require('body-parser');
const mysql = require('mysql');

// custom modules
const bmiCalc = require(path.join(__dirname+'/public/modules/bmiCalc'));

const conn = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "root",
    database: "VerteilteAnwendungen"
});

conn.connect( (err) => {
    if (err) throw err;
    console.log("Connected!");
});

const app = express();
app.use(bodyParser.urlencoded({extended:false}));

// index
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname+'/public/html/index.html'));
});

// BMI-Rechner
app.get('/bmi', (req, res) => {
    res.sendFile(path.join(__dirname+'/public/html/bmiRechner.html'));
});
app.post('/bmi', (req, res) => {
    let bmi = bmiCalc.calculateBMI(req.body.weight, req.body.height);
    let sqlInsert = `INSERT INTO VerteilteAnwendungen.bmi_entries (name, weight, height, bmi, date) VALUES ('${req.body.name}', ${req.body.weight}, ${req.body.height}, ${bmi}, '${req.body.date}')`;
    conn.query(sqlInsert, (err, result) => {
        if(err) throw err;
        console.log("Entry added successfully!");
    });
    res.send("Your BMI is: " + bmi);
});


const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`Listening on port ${port}...`));