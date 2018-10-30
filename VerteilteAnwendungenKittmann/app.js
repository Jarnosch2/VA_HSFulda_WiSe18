const express = require('express');
const path = require('path');
const bodyParser = require('body-parser');

// custom modules
const bmiCalc = require(path.join(__dirname+'/public/modules/bmiCalc'));

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
    res.send("Your BMI is: " + bmi);
});


const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`Listening on port ${port}...`));