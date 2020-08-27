var principal = 154340
var payment = 35316
var numberOfYears = 5
var repayFrequency = 1
var numberOfPayments = numberOfYears * repayFrequency

var error = (10 ** -5)
var approx = (0.05 / 12)
var previousApprox = 0

function f(x) {
    return principal * x * ((1 + x) ** numberOfPayments) / (((1 + x) ** numberOfPayments) - 1) - payment
}

function fPrime(x) {
    return principal * (((1 + x) ** numberOfPayments) / (-1 + ((1 + x) ** numberOfPayments)) -
                        numberOfPayments * x * ((1 + x) ** (-1 + 2 * numberOfPayments)) /
                        ((-1 + ((1 + x) ** numberOfPayments)) ** 2.00) +
                        numberOfPayments * x * ((1 + x) ** (-1 + numberOfPayments)) /
                        (-1 + ((1 + x) ** numberOfPayments)))
}

var k = 0
while (k < 20) {
    previousApprox = approx
    approx = previousApprox - f(previousApprox) / fPrime(previousApprox)
    var diff = Math.abs(approx - previousApprox)
    if (diff < error) {
        break
        }
    k += 1
}
interestRate = ((approx * repayFrequency * 10000) / 100)
console.log("Interest Rate is " + (interestRate.toFixed(2)) + "% final approx " + approx)