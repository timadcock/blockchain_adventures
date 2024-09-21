package block_chain;

import java.io.Serializable;
import java.math.BigInteger;

public class Link implements Serializable {
    private final Link previous;
    private       Link next;

    private final Block data;

    public Link(Block data, Link previous) {
        this.previous = previous;
        this.data     = data;
    }

    public void mine(int difficulty) {
        this.data.provideProof(difficulty);

    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }

    public BigInteger getHash() {
        return data.getHash();
    }

    public BigInteger getPrevHash() {
        return data.getPrevHash();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
