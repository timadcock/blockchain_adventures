import block_chain.Block;
import block_chain.Chain;
import block_chain.Link;
import data_storage.Store;

import java.math.BigInteger;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        byte[]     data = {1, 2, 3};
        BigInteger bi   = new BigInteger("0");

        Store st = new Store("./db.txt");

        Chain c = new Chain(
                new Link(new Block(bi, data, new Date().getTime()), null));

        long startTime = System.nanoTime();
        long total     = 0L;
        c.insert(new Block(c.newest_hash(), data, new Date().getTime()), 25);
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1000000 + "ms");

        total = endTime - startTime;


        startTime = System.nanoTime();
        c.insert(new Block(c.newest_hash(), data, new Date().getTime()), 25);
        endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1000000 + "ms");

        total += endTime - startTime;


        startTime = System.nanoTime();
        c.insert(new Block(c.newest_hash(), data, new Date().getTime()), 25);
        endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1000000 + "ms");

        total += endTime - startTime;
        System.out.println(((total / 3) / 1000000) + "ms");


        st.store_chain(c);

        System.out.println(st.retrieve_chain().validate_chain());
        System.out.println(st.retrieve_chain());

    }
}
