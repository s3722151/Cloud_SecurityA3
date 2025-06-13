import java.math.BigInteger;

public class Q1oneMine {

    public static void main(String[] args) {
        //Step 1: Define values generated in 1.1
        //Define p and q in hex (from MobileFish)
        BigInteger pHex = new BigInteger(
            "c4190d72fe91ca6e0bee6359fcb1b74390c3bb30483847e7c50823caa9c955d204db8dae71e950e9917efae84c48c06998b7f358b538eb49be675d1f90c900d5",
            16);
        BigInteger qHex = new BigInteger(
            "a020c3ef1340da20b8b09f0c380cbcfad5976a760ff90fa02bd29e2d3ace902e1066e4804390a04a42042d26179b6fbfdfac92625b93e5c69acfce0e528c81e1",
            16);
        // Find Eulers by computing p-1 and q-1 
        BigInteger pMinus1 = pHex.subtract(BigInteger.ONE);
        BigInteger qMinus1 = qHex.subtract(BigInteger.ONE);
        BigInteger phiEuler = pMinus1.multiply(qMinus1);
        // Find N = p*q
        BigInteger N = pHex.multiply(qHex);
        // Compute g = N + 1
        BigInteger g = N.add(BigInteger.ONE);
        // Compute n2
        BigInteger N2 = N.pow(2);
        //Define monthly values in hex
        BigInteger plaintextJan = new BigInteger("1DE1", 16);
        BigInteger plaintextFeb = new BigInteger("21E2", 16);
        BigInteger plaintextMarch = new BigInteger("1A5", 16);
        //Define the random values in hex
        BigInteger R1 = new BigInteger("12", 16);
        BigInteger R2 = new BigInteger("34", 16);
        BigInteger R3 = new BigInteger("56", 16);

        //Step 2: Engage in encryption 

        //Step 3 Output the ciphertext for each month:C1, C2, C3
        System.out.println("The ciphertext for January, C1 is: " + cipherText(plaintextJan, g, R1, N2, N).toString(16));
        myMethod();
        System.out.println("The value for February is: " + cipherText(plaintextFeb, g, R2, N2, N).toString(16));
        myMethod();
        System.out.println("The value for March is: " + cipherText(plaintextMarch, g, R3, N2, N).toString(16));
    }
    
    //Methods
    // C = gplaintext1 * R1n (mod n2)
    public static BigInteger cipherText(BigInteger plaintext, BigInteger g, BigInteger R, BigInteger N2, BigInteger N) {
        //Value 1: Calulate g^plaintext1 (mod n^2)
        BigInteger value1 = g.modPow(plaintext,N2);
        //Value 2: Calculate R1^n (mod n^2)
        BigInteger value2 = R.modPow(N, N2);
        //Value 3: Value 1*Value 2 mod n2
        BigInteger value3 = value1.multiply(value2).mod(N2);
        return value3;
    }

    static void myMethod() {
        System.out.println("                  ");
    }
}




