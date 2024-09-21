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

//        block_chain.Block b = new block_chain.Block(bi, data,
//                                                    new Date().getTime()
//        );
//        block_chain.Block b1 = new block_chain.Block(b.getHash(), data,
//                                                     new Date().getTime()
//        );


        Store st = new Store("./db.txt");

        Chain c = new Chain(
                new Link(new Block(bi, data, new Date().getTime()), null));

        c.insert(new Block(c.newest_hash(), data, new Date().getTime()), 5);
        c.insert(new Block(c.newest_hash(), data, new Date().getTime()), 5);
        c.insert(new Block(c.newest_hash(), data, new Date().getTime()), 5);

        st.store_chain(c);


        System.out.println(st.retrieve_chain().validate_chain());
        System.out.println(st.retrieve_chain());

    }
}
