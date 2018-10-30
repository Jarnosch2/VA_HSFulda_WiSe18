function calculateBMI(weight, height) {
    let w = parseInt(weight);
    let h = parseInt(height) / 100;
    return parseFloat(w / (h * h));
}

module.exports.calculateBMI = calculateBMI;