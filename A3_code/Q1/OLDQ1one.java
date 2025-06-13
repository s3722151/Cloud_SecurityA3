import java.math.BigInteger;

public class OLDQ1one  {

    // gcd() method for BigInteger (replaces your int version)
    static BigInteger gcd(BigInteger a, BigInteger b) {
        return a.gcd(b); // Built-in GCD for BigInteger
    }

    public static void main(String[] args) {
        // Step 1: Define p and q in hex (from MobileFish)
        String pHex = "c4190d72fe91ca6e0bee6359fcb1b74390c3bb30483847e7c50823caa9c955d204db8dae71e950e9917efae84c48c06998b7f358b538eb49be675d1f90c900d5";
        String qHex = "a020c3ef1340da20b8b09f0c380cbcfad5976a760ff90fa02bd29e2d3ace902e1066e4804390a04a42042d26179b6fbfdfac92625b93e5c69acfce0e528c81e1";

        // Step 2: Convert hex to BigInteger
        BigInteger p = new BigInteger(pHex, 16);
        BigInteger q = new BigInteger(qHex, 16);

        // Step 3: Compute p-1 and q-1
        BigInteger pMinus1 = p.subtract(BigInteger.ONE);
        BigInteger qMinus1 = q.subtract(BigInteger.ONE);

        // Step 4: Compute GCD(p-1, q-1) using your original logic (now with BigInteger)
        BigInteger gcd = gcd(pMinus1, qMinus1); // Calls the updated gcd() method

        // Step 5: Compute λ = (p-1)(q-1) / gcd(p-1, q-1)
        BigInteger lambda = pMinus1.multiply(qMinus1).divide(gcd);

        // Print results (matches your original output style)
        System.out.println("p-1 (decimal) = " + pMinus1);
        System.out.println("q-1 (decimal) = " + qMinus1);
        System.out.println("GCD(p-1, q-1) = " + gcd);
        System.out.println("Lambda (decimal) = " + lambda);
        System.out.println("Lambda (hex) = " + lambda.toString(16));

        // Compute g = n + 1
        BigInteger n = p.multiply(q);
        BigInteger g = n.add(BigInteger.ONE);

        // Compute g^λ mod n²
        BigInteger nSquared = n.pow(2);
        BigInteger gLambdaModNSquared = g.modPow(lambda, nSquared);

        // Compute L(g^λ mod n²) = (g^λ mod n² - 1) / n
        BigInteger L = gLambdaModNSquared.subtract(BigInteger.ONE).divide(n);

        // Compute μ = L⁻¹ mod n
        BigInteger mu = L.modInverse(n);

        System.out.println("Mu (mu, decimal) = " + mu);
        System.out.println("Mu (hex) = " + mu.toString(16));
    }
}