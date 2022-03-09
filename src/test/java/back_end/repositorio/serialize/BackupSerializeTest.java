package back_end.repositorio.serialize;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Hashtable;
import java.util.Map;

public class BackupSerializeTest {


    @Test
    public void deveConseguirSerializarEstadoBd(){
        try
        {

            Map<String, String> mapa = new Hashtable<>();

            mapa.put("Nome", "Davide");
            mapa.put("Sobrenome", "Sgalambro");


            String pathToResource = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" +
                                                            System.getProperty("file.separator") + "main" +
                                                            System.getProperty("file.separator") + "resources" +
                                                            System.getProperty("file.separator") + "mapa.bkp";

            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(pathToResource);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(mapa);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }


    @Test
    public void deveConseguirDeserializarEstadoBd(){
        // Deserialization
        try
        {

            String pathToResource = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" +
                    System.getProperty("file.separator") + "main" +
                    System.getProperty("file.separator") + "resources" +
                    System.getProperty("file.separator") + "mapa.bkp";

            // Reading the object from a file
            FileInputStream file = new FileInputStream(pathToResource);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            Map<String,String> mapa = (Hashtable) in.readObject();

            in.close();
            file.close();

            mapa.forEach((k,v) -> System.out.println("Key : " + k + ";Value : " + v));

            System.out.println("Object has been deserialized ");
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
    }



}
