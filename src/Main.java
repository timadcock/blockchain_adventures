import java.math.BigInteger;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        byte[]     data = {1, 2, 3};
        BigInteger bi   = new BigInteger("0");

        // Block b = new Block(bi,data,new Date().getTime());
        // Block b1 = new Block(b.getHash(),data,new Date().getTime());


        Chain c = new Chain(
                new Link(new Block(bi, data, new Date().getTime()), null));

        c.insert(new Block(c.newest_hash(),data,new Date().getTime()),5);
        c.insert(new Block(c.newest_hash(),data,new Date().getTime()),5);
        c.insert(new Block(c.newest_hash(),data,new Date().getTime()),5);

        System.out.println(c.validate_chain());
        System.out.println(c.toString());

    }
}
