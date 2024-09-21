package block_chain;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Object to hold block data
public class Block implements Serializable {
    private final BigInteger prevHash;
    private final byte[]     data;
    private final long       timestamp;
    private       BigInteger hash;
    private       int        nonce;

    public Block(BigInteger prevHash, byte[] data, long timestamp) {
        this.prevHash  = prevHash;
        this.data      = data;
        this.timestamp = timestamp;
        this.nonce     = 0;
        this.hash      = generateHash();
    }

    private BigInteger generateHash() {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");

            String string_to_be_hash =
                    this.prevHash.toString(16) + this.timestamp +
                    Long.toString(this.data.length) + this.nonce;

            byte[] msgDigest = digest.digest(string_to_be_hash.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, msgDigest);

            return no;

        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm");
        }
        return null;
    }

    // Proof will be determined by a hash that is xonred by a value with x
    // number of zeroes at the least significant end.
    public void provideProof(int difficulty) {

        BigInteger proof = BigInteger.ZERO.not();
        proof = proof.shiftLeft(difficulty).not();
        boolean found = false;

        while (!found) {
            BigInteger anded = this.hash.not().and(proof);
            if (anded.equals(proof)) {
                found = true;
            } else {
                this.nonce++;
                this.hash = generateHash();
            }
        }

    }

    public BigInteger getHash() {
        return hash;
    }

    public BigInteger getPrevHash() {
        return prevHash;
    }

    public byte[] getData() {
        return data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("Hash: %s\n", this.hash.toString(16));
    }
}
