<?php

$principal = 154340;
$payment = 35316;
$numberOfYears = 5;
$paymentsPerYear = 1;
$numberOfPayments = $numberOfYears * $paymentsPerYear;

function calculateInterest(){

$tolerableError  = 10 ** -5;
$guess = 0.05/12; //Initial interest rate guess of 5%
$updateGuess = 0;

function f($x) {
    global $principal;
    global $numberOfPayments;
    global $payment;

    return $principal * $x * ((1 + $x) ** $numberOfPayments) / (((1 + $x) ** $numberOfPayments) - 1) - $payment;
}

function fPrime($x) {
    global $principal;
    global $numberOfPayments;

    return $principal * (((1 + $x) ** $numberOfPayments) / (-1 + ((1 + $x) ** $numberOfPayments)) -
                        $numberOfPayments * $x * ((1 + $x) ** (-1 + 2 * $numberOfPayments)) /
                        ((-1 + ((1 + $x) ** $numberOfPayments)) ** 2) +
                        $numberOfPayments * $x * ((1 + $x) ** (-1 + $numberOfPayments)) /
                        (-1 + ((1 + $x) ** $numberOfPayments)));
}

$k = 0;
while($k < 20) {
    $updateGuess = $guess;
    $guess = $updateGuess - f($updateGuess) / fPrime($updateGuess);
    $diff = abs($guess - $updateGuess);
    if ($diff < $tolerableError) {
        break;
        }
    $k += 1;
}

return $guess;
}

$interestPerPayment = calculateInterest();
$interestRate = (($interestPerPayment * $paymentsPerYear * 10000) / 100);

print "Interest Rate is " .round($interestRate, 2). "%\n";
print $interestPerPayment; //this figure is what is used for the interest rate for the amortisation schedule	
?>
