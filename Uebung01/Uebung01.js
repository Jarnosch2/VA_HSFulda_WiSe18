function calculateBMI() {
    var weight = document.getElementById('inputWeight').value;
    var height = document.getElementById('inputHeight').value;

    var bmi = weight / (height * height);

    var displayResult = document.getElementById('result');
    displayResult.innerHTML = bmi;
}