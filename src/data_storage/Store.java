package data_storage;

import block_chain.Block;
import block_chain.Chain;

import java.io.*;

public class Store {

    private final String db_file;


    public Store(String db_file) {
        this.db_file = db_file;
    }

    public void store_chain(Chain block) {

        try (FileOutputStream fio = new FileOutputStream(this.db_file)) {

            ObjectOutputStream oos = new ObjectOutputStream(fio);
            oos.writeObject(block);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Chain retrieve_chain() {

        try (FileInputStream fio = new FileInputStream(this.db_file)) {

            ObjectInputStream ois = new ObjectInputStream(fio);
            return (Chain) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return null;

    }


}
