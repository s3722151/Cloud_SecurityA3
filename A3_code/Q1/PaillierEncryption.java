import java.math.BigInteger;

public class PaillierEncryption {

    // Precomputed values (from 1.1)
    private static final String N_HEX = "7aa8c19fc4dca05c1837aa11f2e9be3d80ea0505d390a3c5c847d236f28f2edb733ee84528d140385fde2e34d77e4a3fd628e10347a8ad374491fef17db4711176403d710305f4abe53618a558a4ef6bcdbcd4143a9b6b9bd4db218ebfb6e2a3d6de4daa72ed82973f32a5f862033fb752d7e8cf1ba1da1ef04dc28a38911035";
    private static final String G_HEX = "7AA8C19FC4DCA05C1837AA11F2E9BE3D80EA0505D390A3C5C847D236F28F2EDB733EE84528D140385FDE2E34D77E4A3FD628E10347A8AD374491FEF17DB4711176403D710305F4ABE53618A558A4EF6BCDBCD4143A9B6B9BD4DB218EBFB6E2A3D6DE4DAA72ED82973F32A5F862033FB752D7E8CF1BA1DA1EF04DC28A38911036";
    private static final String NSQUARED_HEX = "3ac547cafbc6f4f50b3179c35e7c0a2b487de24cf2b8236af857f449ddc2d8a6e862aa15de04cef1d9d123f337944c1f4883c7f8569198f73bdc57e20a21dfa5d5b121e5106ef4f1e500de74a5177406abb49a6a81505e807da467c0bf6b8872510bca701cea1c08b52226f477fcaf4fca1dd91e7f669aa98b378663ec651ec375a7e98299138b5abdc9010e9d3852543ec3367b2ffadd40cfec31becb8941c3577d39e0962c0053d268b733cf27d2b26236dec9fb609c8e1fceb78c7fe203dc1607d192e2e661bcd04ae77c975c9e9649ca73a6b29de23bce6694f3bb96ca695128397ea3bfea647a1c151cb4d3b3b911e6eab5a74fde700557966e8d10aaf9";

    // Plaintext incomes (mod 10000)
    private static final int INCOME_JAN = 7649; // 0x1DE1
    private static final int INCOME_FEB = 8674; // 0x21E2
    private static final int INCOME_MAR = 421; // 0x1A5

    public static void main(String[] args) {
        // Initialize Paillier parameters
        BigInteger n = new BigInteger(N_HEX, 16);
        BigInteger g = new BigInteger(G_HEX, 16);
        BigInteger nSquared = new BigInteger(NSQUARED_HEX, 16);

        // --- Encryption ---
        // Encrypt with fixed randomness (r1=12, r2=34, r3=56)
        BigInteger c1 = encrypt(INCOME_JAN, n, g, nSquared, BigInteger.valueOf(12));
        BigInteger c2 = encrypt(INCOME_FEB, n, g, nSquared, BigInteger.valueOf(34));
        BigInteger c3 = encrypt(INCOME_MAR, n, g, nSquared, BigInteger.valueOf(56));

        System.out.println("C1 (January): " + c1.toString(16));
        System.out.println("C2 (February): " + c2.toString(16));
        System.out.println("C3 (March): " + c3.toString(16));

        // --- Homomorphic Summation ---
        BigInteger cSum = c1.multiply(c2).multiply(c3).mod(nSquared);
        System.out.println("Homomorphic Sum (C1*C2*C3 mod n²): " + cSum.toString(16));
    }

    // Paillier Encryption: c = g^m * r^n mod n²
    private static BigInteger encrypt(int plaintext, BigInteger n, BigInteger g, BigInteger nSquared, BigInteger r) {
        BigInteger m = BigInteger.valueOf(plaintext);
        BigInteger term1 = g.modPow(m, nSquared); // g^m mod n²
        BigInteger term2 = r.modPow(n, nSquared); // r^n mod n²
        return term1.multiply(term2).mod(nSquared);
    }
}