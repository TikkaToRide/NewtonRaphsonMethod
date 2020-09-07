
var principal = prompt("Principal Amount ")
var payment = prompt("Payment Amount ") //this is the repayment amount
var numberOfYears = prompt("How Many Years is Loan ") //how many years is the loan for?
var repayFrequency = prompt("Payments per Year ") //how many payments in the year eg. 1 = yearly, 12 = monthly
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
                        ((-1 + ((1 + x) ** numberOfPayments)) ** 2) +
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
console.log("Interest Rate is " + (interestRate.toFixed(2)) + "%")
console.log(approx) //this figure is what is used for the interest rate for the amortisation schedule
