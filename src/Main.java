import java.util.Random;

public class Main {

    private static float f(float x)
    {
        return 4 / (1 + (x * x));
    }

    private static float trapezoidal()
    {
        float N = 2, prev_result = 0, result, delta;
        while(true)
        {
            result = 0;
            delta = 1 / N;

            for(int i = 1; i < N; i++)
                result += 2 * f(delta * i);
            result = (delta / 2) * (result + 4 + 2);
            if(result < prev_result) {
                System.out.printf("The value of pi using Trapezoidal method is: %.8f\n", prev_result);
                System.out.printf("The number of intervals used is %.0f\n", N);
                break;
            }
            prev_result = result;
            N++;
        }
        return N;
    }

    private static void simpson(float N)
    {
        float result = 0;
        float delta = 1 / N;
        float value1 = 0, value2 = -delta/2, x1;
        float x = f(0);
        for(int i = 1; i <= N; i++) {
            value1 += delta;
            value2 += delta;
            x1 = f(value1);
            result += (x + x1 + 4*f(value2));
            x = x1;
        }
        result = (result * delta)/6;
        System.out.printf("The value of pi using Simpson's method is: %.8f\n", result);
    }

    private static void midPoint(float N)
    {
        float result = 0;
        float delta = 1/ N;
        float tempResult = delta/2;
        for(int i = 1; i <= N; i++) {
            result += f(tempResult);
            tempResult += delta;
        }
        result *= delta;
        System.out.printf("The value of pi using midpoint method is: %.8f\n", result);
    }

    private static void monteCarlo()
    {
        Random rnd = new Random();
        float xPos = 1, xNeg = 0, yNeg = 0, yPos = 4;
        float x1, x0, result, area;
        int increase = 0;
        long random_points = 100000;
        for (int i = 1; i <= random_points; i++)
        {
            x1 = rnd.nextFloat();
            x0 = getRandomNumberInRange(yNeg, yPos);
            if (x0 <= f(x1)) {
                ++increase;
            }
        }
        result = (float) increase / (float) random_points;
        area = (xPos - xNeg) * (yPos - yNeg);
        result = result * area;
        System.out.printf("The value of pi using Monte carlo method with 1000000 random points is: %.8f\n", result);
    }

    private static float getRandomNumberInRange(float min, float max) {
        Random rnd = new Random();
        return min + (rnd.nextFloat() * (max - min));
    }

    public static void main(String[] args) {
        float N = trapezoidal();
        simpson(N);
        midPoint(N);
        monteCarlo();
    }
}
