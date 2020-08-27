
fun main() {
  
  var principal = 154340
  var payment = 35316  //this is the repayment amount
  var numberOfYears = 5  //how many years is the loan for?
  var repayFreq = 1  //how many payments in the year eg. 1 = yearly, 12 = monthly
  
  val numPay = numberOfYears * repayFreq
  val error = Math.pow(10.00, -5.00)
  var approx = 0.05/12  //let's start with a guess that the APR is 5%
  var prevApprox: Double

fun f(x: Double): Double {
    val base: Double = 1 + x
    return principal * x * Math.pow(base, numPay.toDouble()) / (Math.pow(base, numPay.toDouble()) - 1 ) - payment
}

fun fPrime(x: Double): Double{
    val base: Double = 1 + x
    return principal * (Math.pow(base, numPay.toDouble()) / (-1.00 + Math.pow(base, numPay.toDouble())) -
                          numPay * x * Math.pow(base, (-1.00 + 2.00 * numPay.toDouble())) /
                          Math.pow((-1.00 + Math.pow(base, numPay.toDouble())), 2.00) +
                          numPay * x * Math.pow(base, (-1.00 + numPay.toDouble())) /
                          (-1.00 + Math.pow(base, numPay.toDouble())))
}

var k = 0
while (k < 20){
    prevApprox = approx
    approx = prevApprox - f(prevApprox) / fPrime(prevApprox)
    var diff = Math.abs(approx - prevApprox)
    if (diff < error)
        break
    k += 1
}    

val interestRate = (approx * repayFreq * 10000)/100 //this way we get APRs like 7.5% or 6.55%
println("Interest Rate is ${"%.2f".format(interestRate)}%")
print(approx) //this figure is what is used for the interest rate for the amortisaiton schedule

}
