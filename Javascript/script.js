
var principal = prompt("Principal Amount ")
var payment = prompt("Payment Amount ")
var numberOfYears = prompt("How Many Years is Loan ")
var repayFrequency = prompt("Payments per Year ") //how many payments in the year eg. 1 = yearly, 12 = monthly
var numberOfPayments = numberOfYears * repayFrequency

function newtonRaphsonMethod(){
var error = (10 ** -5)
var approx = (0.05 / 12) //start with a gusess that the APR is 5%
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

return approx

}

var interestPerPayment = newtonRaphsonMethod()
var interestRate = ((interestPerPayment * repayFrequency * 10000) / 100)
console.log("Interest Rate is " + (interestRate.toFixed(2)) + "%")
console.log(interestPerPayment) //this figure is what is used for the interest rate for the amortisation schedule
