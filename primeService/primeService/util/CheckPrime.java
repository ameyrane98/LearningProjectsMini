package primeService.util;

public class CheckPrime {
    public static String isPrime(int number) {
        if (number < 3) {
            return "Invalid";
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return "No";
            }
        }
        return "Yes";
    }
}
