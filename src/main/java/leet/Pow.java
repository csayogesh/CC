package leet;

public class Pow {
    public double myPow(double x, int n) {
        if (n == 0) return 1.0;
        double ans = myPow(x, n / 2);
        if (Math.abs(n % 2) == 1)
            return getAns(x, n) * ans * ans;
        return ans * ans;
    }

    private double getAns(double x, int n) {
        if (n < 0) return 1.0 / x;
        return x;
    }

    public static void main(String[] args) {
        double out = new Pow().myPow(2, -2);
        System.out.println(out);
    }
}
