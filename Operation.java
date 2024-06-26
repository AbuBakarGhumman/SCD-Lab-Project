package calculator;

public class Operation {
    private double firstNumber;
    private double secondNumber;
    private String operator;

    public Operation(double firstNumber, double secondNumber, String operator) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operator = operator;
    }

    public double performOperation() {
        double result = 0;

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "X":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    // Handle division by zero error
                }
                break;
            case "√":
                result = Math.sqrt(firstNumber);
                break;
            case "x²":
                result = Math.pow(firstNumber, 2);
                break;
            case "1/x":
                if (firstNumber != 0) {
                    result = 1 / firstNumber;
                } else {
                    // Handle division by zero error
                }
                break;
        }

        return result;
    }
}