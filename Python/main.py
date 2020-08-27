loan_amount = float(input("Loan Amount: $"))
payment_amount = float(input("Payment Amount: $"))  # this is the repayment amount
term_of_loan = int(input("Term of Loan: "))  # how many years is the loan for
payment_frequency = int(input("Payment Frequency: "))  # how many payments in the year eg. 1 = yearly, 12 = monthly

number_of_payments = term_of_loan * payment_frequency
previous_approx = float(0)
error = float(10 ** -5)
approx = float(0.05 / 12)


def f(x):
    return loan_amount * x * ((1 + x) ** number_of_payments) / (((1 + x) ** number_of_payments) - 1) - payment_amount


def f_prime(x):
    return loan_amount * (((1 + x) ** number_of_payments) / (-1 + ((1 + x) ** number_of_payments)) -
                          number_of_payments * x * ((1 + x) ** (-1 + 2 * number_of_payments)) /
                          ((-1 + ((1 + x) ** number_of_payments)) ** 2.00) +
                          number_of_payments * x * ((1 + x) ** (-1 + number_of_payments)) /
                          (-1 + ((1 + x) ** number_of_payments)))


k = 0
while k < 20:
    previous_approx = approx
    approx = previous_approx - f(previous_approx) / f_prime(previous_approx)
    diff = abs(approx - previous_approx)
    if diff < error:
        break
    k += 1

interest_rate = ((approx * payment_frequency * 10000) / 100)
print(f"Interest Rate is {interest_rate:.2f}%")
print(approx)  # this figure is what is used for the interest rate on the amortisation schedule
