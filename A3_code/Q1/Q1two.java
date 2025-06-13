import java.math.BigInteger;
import java.security.SecureRandom;

public class Q1two {

    public static void main(String[] args) {
        // Define public key components
        BigInteger N = new BigInteger(
                "7AA8C19FC4DCA05C1837AA11F2E9BE3D80EA0505D390A3C5C847D236F28F2EDB733EE84528D140385FDE2E34D77E4A3FD628E10347A8AD374491FEF17DB4711012066C0EF133501D2097163F23E67B2D6761AE6DE26A1413E4005F96DB1EFCA3C19BDB7BBD7391636BAF7DE9FE1F0F8DDA7363140AD5090E9716975C553B8D80",
                16);
        BigInteger g = N.add(BigInteger.ONE); // g = N + 1
        BigInteger r = new BigInteger("2"); // Random number r

        // Calculate N squared once
        BigInteger NSquared = N.pow(2);

        // Encrypt January income (E6EA)
        System.out.println("January Encryption:");
        BigInteger janIncome = new BigInteger("E6EA", 16);
        BigInteger janCipher = encryptIncome(janIncome, N, g, generateRandomR(N), NSquared);
        System.out.println("Ciphertext: " + janCipher.toString(16));
        System.out.println("----------------------------------");

        // Encrypt February income (BD42)
        System.out.println("February Encryption:");
        BigInteger febIncome = new BigInteger("BD42", 16);
        BigInteger febCipher = encryptIncome(febIncome, N, g, generateRandomR(N), NSquared);
        System.out.println("Ciphertext: " + febCipher.toString(16));
        System.out.println("----------------------------------");

        // Encrypt March income (DAD1)
        System.out.println("March Encryption:");
        BigInteger marIncome = new BigInteger("DAD1", 16);
        BigInteger marCipher = encryptIncome(marIncome, N, g, generateRandomR(N), NSquared);
        System.out.println("Ciphertext: " + marCipher.toString(16));
    }

    // Method to encrypt a single income value
    public static BigInteger encryptIncome(BigInteger income, BigInteger N, BigInteger g,
            BigInteger r, BigInteger NSquared) {
        // Value 1: Calculate g^m mod N² using the shortcut: (1 + m*N) mod N²
        BigInteger gmModNSquared = BigInteger.ONE.add(income.multiply(N)).mod(NSquared);

        // Value 2: Calculate r^N mod N² 
        BigInteger rNModNSquared = r.modPow(N, NSquared);

        // Value 3: Value 1 * Value 2 = Calculate ciphertext: (gmModNSquared * rNModNSquared) mod N²
        return gmModNSquared.multiply(rNModNSquared).mod(NSquared);
    }

    //Method to generate a random number for each income value
    public static BigInteger generateRandomR(BigInteger N) {
        SecureRandom random = new SecureRandom();
        BigInteger r;
        do {
            r = new BigInteger(N.bitLength(), random);
        } while (r.compareTo(BigInteger.ZERO) <= 0 || r.compareTo(N) >= 0 || !r.gcd(N).equals(BigInteger.ONE));
        return r;
    }
}