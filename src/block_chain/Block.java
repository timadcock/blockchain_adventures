package block_chain;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Object to hold block data
public class Block implements Serializable {
    static volatile int        nonce_found = -1;
    private final   BigInteger prevHash;
    private final   byte[]     data;
    private final   long       timestamp;
    private         BigInteger hash;
    private         long       nonce;

    public Block(BigInteger prevHash, byte[] data, long timestamp) {
        this.prevHash  = prevHash;
        this.data      = data;
        this.timestamp = timestamp;
        this.nonce     = 0;
        this.hash      = generateHash(this.nonce);
    }

    private BigInteger generateHash(long nonce) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");

            String string_to_be_hash =
                    this.prevHash.toString(16) + this.timestamp +
                    Long.toString(this.data.length) + nonce;

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
        nonce_found = -1;
        BigInteger proof = BigInteger.ZERO.not();
        proof = proof.shiftLeft(difficulty).not();

        int          num_threads = 8;
        HashFinder[] threads     = new HashFinder[num_threads];

        long seperation = 4294967296L / num_threads;

        for (int i = 0; i < num_threads; i++) {
            threads[i] = new HashFinder(Long.MIN_VALUE + (seperation * i),
                                        this.hash, proof, i
            );
            threads[i].start();
        }


        while (nonce_found == -1) {
            //System.out.println("Waiting on threads...");
        }

        for (int i = 0; i < num_threads; i++) {
            threads[i].interrupt();
        }

        this.nonce = threads[nonce_found].nonce;
        this.hash  = generateHash(this.nonce);
        System.out.println(difficulty);

//        while (!test_hash.not().and(proof).equals(proof)) {
//
//            this.nonce++;
//            test_hash = generateHash();
//
//        }

        // this.hash = test_hash;

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

    private class HashFinder extends Thread {
        private final int        ID;
        public        long       nonce;
        public        BigInteger hash;
        public        BigInteger proof;


        public HashFinder(long nonce, BigInteger hash, BigInteger proof,
                          int id) {
            this.hash  = hash;
            this.proof = proof;
            this.ID    = id;
            this.nonce = nonce;


        }

        public void run() {
            while (!this.hash.not().and(this.proof).equals(this.proof) &&
                   nonce_found == -1) {
                this.nonce++;

                // Not really a true nonce but I want to be able to have
                // valid threads use
                this.hash = generateHash(this.nonce);
            }

            if (nonce_found == -1) {
                nonce_found = this.ID;
            }
        }
    }


}
