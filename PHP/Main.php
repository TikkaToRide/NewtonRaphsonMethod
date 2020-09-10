<?php

$principal = 154340;
$payment = 35316;
$numberOfYears = 5;
$paymentsPerYear = 1;
$numberOfPayments = $numberOfYears * $paymentsPerYear;

$error = 10 ** -5;
$startGuess = 0.05/12;
$previousStartGuess = 0;

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
    $previousStartGuess = $startGuess;
    $startGuess = $previousStartGuess - f($previousStartGuess) / fPrime($previousStartGuess);
    $diff = abs($startGuess - $previousStartGuess);
    if ($diff < $error) {
        break;
        }
    $k += 1;
}		

$interestRate = (($startGuess * $paymentsPerYear * 10000) / 100);

print "Interest Rate is " .round($interestRate, 2). "%\n";
print $startGuess; //this figure is what is used for the interest rate for the amortisation schedule	
?>