import java.math.BigInteger;

public class Q1fourMine {
//Main method
public static void main(String[] args) {
    //First I define the values needed
    // Ciphertext from multiplying values - Addititive Homomorphic Sum
    BigInteger additiveHomoSum = new BigInteger(
            "2c642fd462b922a5cbc851ba64acb49c86f72ce59078c2fab677b42ee490e947558e480bc299c8dff1f75e8096434561f24e4f99065fe9c808d87aa28c19762c287cbaa16e60dbb1bf2d8c8865f3a3be745fa434ef7bd50e02c3f33b89b268aeb0cd14d2e217a7223133875bacf024a2248ed71201602b889394cc00748691be5e7901d654c48c8337ee606015b97ff24f593fb94f3cc9b8be0260c6b1ba988c8f0e4fa5b90d61449bffbb2655cc667fef1accd982e829d9f6a2b72f11415e7960562ce1873c0dc73d35eea47930b0bcfc37136b76ef63f177aabda42d83b9ec28f5003a1dc263638739ef92c19ca2c4965d90ef5ec79f2638703238a2568781",
            16);

    BigInteger pHex = new BigInteger(
            "c4190d72fe91ca6e0bee6359fcb1b74390c3bb30483847e7c50823caa9c955d204db8dae71e950e9917efae84c48c06998b7f358b538eb49be675d1f90c900d5",
            16);
            
    BigInteger qHex = new BigInteger(
            "a020c3ef1340da20b8b09f0c380cbcfad5976a760ff90fa02bd29e2d3ace902e1066e4804390a04a42042d26179b6fbfdfac92625b93e5c69acfce0e528c81e1",
            16);

    //This is my generator, N + 1 in hexadecimal
    BigInteger N = new BigInteger(
            "7AA8C19FC4DCA05C1837AA11F2E9BE3D80EA0505D390A3C5C847D236F28F2EDB733EE84528D140385FDE2E34D77E4A3FD628E10347A8AD374491FEF17DB4711012066C0EF133501D2097163F23E67B2D6761AE6DE26A1413E4005F96DB1EFCA3C19BDB7BBD7391636BAF7DE9FE1F0F8DDA7363140AD5090E9716975C553B8D80",
            16);
    //This was calculated manually
    BigInteger Nsquare = new BigInteger(
            "3ac547cafbc6f4f50b3179c35e7c0a2b487de24cf2b8236af857f449ddc2d8a6e862aa15de04cef1d9d123f337944c1f4883c7f8569198f73bdc57e20a21dfa480545f94f5544aa977e78d756cb46acc149838a5320c90506d1484b3fa87190166a06243de2c9a4dc471c647c19d509905399cd66e4ba02652c2b0d3b3e441da1373ff3da8490e590f561663f4c7761c5953a58706609904bd6ec82a421ea747d3647e4eb1ce55865742b1ab903d40e5a0f20be2886616ee1fff877226724c7e8d639c30e0dea007c8c19ee1310e0009b57c6cc3698a3ee14cbf24d87cafe1e6e86ce89711a89e642ce3331eac4d26b80e564ccc435f6f02f21b4dd187364000",
            16);

    BigInteger pMinus1 = pHex.subtract(BigInteger.ONE);
    BigInteger qMinus1 = qHex.subtract(BigInteger.ONE);
    BigInteger pMultiplyQ = pMinus1.multiply(qMinus1);

    lineBreak();
    System.out.println("The result is: " + decryption(pHex,qHex,pMultiplyQ, 
    additiveHomoSum,Nsquare, N));
  
    }
    

    // Method to provide line breaks
    static void lineBreak() {
            System.out.println("                 ");
    }
    



    public static BigInteger decryption(BigInteger pHex, BigInteger qHex, BigInteger pMultiplyQ, 
        BigInteger additiveHomoSum,BigInteger Nsquare, BigInteger N) {
        //Calculate numerator:  [c(p-1)(q-1)mod N2]-1
         //Simplified as c^lambda mod N^2
        BigInteger cSquared = additiveHomoSum.modPow(pMultiplyQ,Nsquare);
        BigInteger cPowLambda = cSquared.mod(Nsquare).subtract(BigInteger.ONE);

        // Calculate denominator: N [(p-1)(q-1)]^-1
         //Simplified as L(c^lambda mod N^2) = (u - 1) / N
        BigInteger L = cSquared.subtract(BigInteger.ONE).divide(N); // Apply L function
    
        // Method to calulate [c(p-1)(q-1)(mod N2)-1] / N [(p-1)(q-1)]-1(mod N)
        BigInteger m = L.mod(N); // Get the decrypted message
        return m;
    }

}
