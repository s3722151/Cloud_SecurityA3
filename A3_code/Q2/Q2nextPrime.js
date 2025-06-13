const s = BigInt("0xc4190d72fe91ca6e0bee6359fcb1b74390c3bb30483847e7c50823caa9c955d204db8dae71e950e9917efae84c48c06998b7f358b538eb49be675d1f90c900d5");

// Faster Miller-Rabin primality test (essential for large numbers)
function isPrime(n, k = 5) {
    if (n <= 1n) return false;
    if (n <= 3n) return true;
    if (n % 2n === 0n) return false;

    // Write n as d*2^s + 1
    let d = n - 1n;
    let s = 0n;
    while (d % 2n === 0n) {
        d /= 2n;
        s++;
    }

    for (let i = 0; i < k; i++) {
        const a = 2n + BigInt(Math.floor(Math.random() * 10000));
        let x = modPow(a, d, n);
        if (x === 1n || x === n - 1n) continue;

        let isComposite = true;
        for (let j = 0n; j < s - 1n; j++) {
            x = modPow(x, 2n, n);
            if (x === n - 1n) {
                isComposite = false;
                break;
            }
        }
        if (isComposite) return false;
    }
    return true;
}

function modPow(a, b, mod) {
    let result = 1n;
    a = a % mod;
    while (b > 0n) {
        if (b % 2n === 1n) result = (result * a) % mod;
        a = (a * a) % mod;
        b = b / 2n;
    }
    return result;
}

// Find next prime after s
function findNextPrime(start) {
    let candidate = start % 2n === 0n ? start + 1n : start + 2n;
    while (true) {
        if (isPrime(candidate)) return candidate;
        candidate += 2n;
    }
}

// Execute
console.log("Secret s (hex):", s.toString(16));
console.log("Finding next prime after s... This may take several minutes...");

const P = findNextPrime(s);
console.log("\nPrime P found:");
console.log("Hex:", P.toString(16));
console.log("Decimal:", P.toString());