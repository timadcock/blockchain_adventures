package block_chain;

import java.io.Serializable;
import java.math.BigInteger;

public class Chain implements Serializable {

    private final Link head;
    private       Link tail;
    private       int  length;

    public Chain(Link head) {
        this.head   = head;
        this.tail   = this.head;
        this.length = 1;
    }

    public void insert(Block newLink, int difficulty) {
        this.tail.setNext(new Link(newLink, tail));
        this.tail = this.tail.getNext();
        this.length++;
        this.mine_newest_block(difficulty);
    }

    public BigInteger newest_hash() {
        return this.tail.getHash();
    }

    public void mine_newest_block(int difficulty) {
        this.tail.mine(difficulty);
    }

    // Will return length if valid, otherwise will return the index of which
    // invalidates the chain.
    private int validate_hash_chain() {

        Link    current = this.head;
        boolean valid   = true;
        int     index   = 1;

        while (current.getNext() != null && valid) {

            if (!current.getHash().equals(current.getNext().getPrevHash())) {
                valid = false;
                break;
            }

            current = current.getNext();
            index++;
        }

        return index;

    }

    public boolean validate_chain() {
        return this.length == this.validate_hash_chain();
    }


    @Override
    public String toString() {


        StringBuilder sb = new StringBuilder();

        Link current = this.head;
        int  order   = 0;
        while (current != null) {
            sb.append(order);
            sb.append(": ");
            order++;

            sb.append(current);
            current = current.getNext();
        }
        return sb.toString();
    }
}
