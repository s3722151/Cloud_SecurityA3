import java.math.BigInteger;

public class Q1three {

    public static void main(String[] args) {
        // Ciphertexts from 1.2 (January, February, March)
        BigInteger jan = new BigInteger(
                "38564932c39614aa29c648b53eb341a701b8fc532864136627367e8e278b55189332829582b58039cb74f636102670091e79414569171db2171747796e49d848fd3b5a9eb2a4ef013df403ddb80fe46ca8dce2b452f8c15641b20e479b0aaafb7b8de2669014cb1663688574a226bb08a6e91751fc76320dd093ea2fe78e44df2f64cf80c0a30997476c716648e11a733f1ea8b71ebfb33730c88871f5b084fc062d45542b6c33ce17872a1605c4326e818a24bad2f0970152476ff854033d03405ee771776db1241d489b3bc2aa4d5eaf4b75da1be43b87f0791c59fd2fea119dacf6d620574ff63c62f5ffbb50308a17df35d73e9700190df62ab72a6ff8fe",
                16);

        BigInteger feb = new BigInteger(
                "3cb06317342fb02372551ac96560a9fa78e46ec419f8008e1dadd95527645c2426678703d21ccff3aa2cc4ee314fd3641e2e14b873295096a6596d8f4f37fc461b38a37e17f08d277349cd9b0acc34ef736e182839baaee180b146d9676a7807f03bc68943c08d60bdd5eee406f631f4deed185d72ad545cec7b7e6737726742b3bbb16ada162dc3060e6146725f1ecbfa3fde83a62a1e87bcccfef2308454fca4451081e2753e03a6e9d03e7568f1168abb7ac996b774919290f3d48f8d4f2480d5cb621b4b6a2e5bdb7dd9e96ad905e8012231c7e4480494e04241e24469ee60531ac0254e24a8c3788576f9ce78721d8e381c05a5e0e967363e31ff99d21",
                16);

        BigInteger march = new BigInteger(
                "24f960541b9d02d0ada0224af3b9e4f5182624bbba7110a2a2b4a76e916d8af556458da15d338aff38a2c274f1cf8bb95bf64b61152fd41a16f8002953f026add02dce3f6132596b92df5e74bc5c346d69ef2cb9897963567049facc84a93431e183223e40f6f0935a014cf812a2776effffa191deac0a288c9453d3bec08ecdf8b5caf18979ba28f0d047b415b4cbb292b7d756f9f2b0d01d4fdd3564c08cf349005ade4c271863291c69a9c423383d0d5c544310d5d0554b5fc22f8c3d2e4b24718a34895cfe4ee2504a8e074978c85c4f09222e3d9ab0e2b3c5be594e4beea431b3a3dada0ea9497e3e7a687820d2d6be7d80920278485f4fc46787194299",
                16);

        // Compute N² (from your public key in 1.1)
        BigInteger N = new BigInteger(
                "7aa8c19fc4dca05c1837aa11f2e9be3d80ea0505d390a3c5c847d236f28f2edb733ee84528d140385fde2e34d77e4a3fd628e10347a8ad374491fef17db4711176403d710305f4abe53618a558a4ef6bcdbcd4143a9b6b9bd4db218ebfb6e2a3d6de4daa72ed82973f32a5f862033fb752d7e8cf1ba1da1ef04dc28a38911035",
                16);
        BigInteger NSquared = N.pow(2);

        // Multiply ciphertexts mod N² (homomorphic sum)
        BigInteger encryptedSum = multiplyValues(jan, feb, march, NSquared);

        // Print results
        System.out.println("January ciphertext: " + jan.toString(16));
        lineBreak();
        System.out.println("February ciphertext: " + feb.toString(16));
        lineBreak();
        System.out.println("March ciphertext: " + march.toString(16));
        lineBreak();
        System.out.println("Additive Homomorphic sum (Encrypted Total): " + encryptedSum.toString(16));
        lineBreak();
        lineBreak();
        System.out.println("This is testing multiplications for each month then squaring it.");
        BigInteger testJanFeb = jan.multiply(feb).mod(NSquared);
        System.out.println("Test value multiplying January and Feb: " + testJanFeb.toString(16));
        lineBreak();
        BigInteger testTotal = testJanFeb.multiply(march).mod(NSquared);
        System.out.println("Test value multiplying previous result to March: " + testTotal.toString(16));
    }

    // Method to provide line breaks
    static void lineBreak() {
        System.out.println("                 ");
    }

    // Method to multiply ciphertexts mod N² (homomorphic addition)
    public static BigInteger multiplyValues(BigInteger jan, BigInteger feb, BigInteger march, BigInteger NSquared) {
        // Multiply all three ciphertexts and reduce mod N²
        return jan.multiply(feb).multiply(march).mod(NSquared);
    }
}