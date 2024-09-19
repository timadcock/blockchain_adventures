import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Object to hold block data
public class Block {
    private final BigInteger hash;
    private final BigInteger prevHash;
    private final byte[]     data;
    private final long       timestamp;

    public Block(BigInteger prevHash, byte[] data, long timestamp) {
        this.prevHash  = prevHash;
        this.data      = data;
        this.timestamp = timestamp;
        this.hash      = generateHash();
    }

    private BigInteger generateHash() {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");

            String string_to_be_hash =
                    this.prevHash.toString(16) + this.timestamp +
                    this.data.length;

            byte[] msgDigest = digest.digest(string_to_be_hash.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, msgDigest);

            return no;

        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm");
        }
        return null;
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
        return String.format("Hash: %s\n", this.hash.toString(16)
        );
    }
}
