import java.math.BigInteger;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        byte[] data = {1,2,3};
        BigInteger bi = new BigInteger("0");

        Block b = new Block(bi,data,new Date().getTime());
        Block b1 = new Block(b.getHash(),data,new Date().getTime());

        Chain c = new Chain(new Link(b,null));
        c.insert(b1);

        //System.out.printf(b.toString());
        //System.out.printf(b1.toString());
        System.out.println(c.toString());

        }
    }
