import 'dart:math';

void main() {
  var principal = 38070;
  var payment = 730.35;
  var numberOfYears = 5;
  var repaymentFrequency = 12;

  var numberOfPayments = numberOfYears * repaymentFrequency;

  double calculateInterest() {
    var tolerableError = pow(10, -5);
    var guess = 0.05 / 12;
    var updateGuess;

    double f(x) => principal * x * pow((1 + x), numberOfPayments) / (pow((1 + x), numberOfPayments) - 1) - payment;

    double fPrime(x) => principal * (pow((1 + x), numberOfPayments) / (-1 + pow((1 + x), numberOfPayments)) - numberOfPayments * x * pow((1 + x), (-1 + 2 * numberOfPayments)) /
                pow(-1 + pow((1 + x), numberOfPayments), 2) + numberOfPayments * x * pow((1 + x), (-1 + numberOfPayments)) / (-1 + pow((1 + x), numberOfPayments)));

    var k = 0;

    while (k < 20) {
      updateGuess = guess;
      guess = updateGuess - f(updateGuess) / fPrime(updateGuess);
      var diff = (guess - updateGuess).abs();
      if (diff < tolerableError) {
        break;
      }
      k += 1;
    }

  return guess;
  }

  var interestPerPayment = calculateInterest();
  var interestRate = (interestPerPayment * repaymentFrequency * 10000) / 100;
  print('Interest rate is ' + interestRate.toStringAsFixed(2) + '%');
  print(interestPerPayment);
}
