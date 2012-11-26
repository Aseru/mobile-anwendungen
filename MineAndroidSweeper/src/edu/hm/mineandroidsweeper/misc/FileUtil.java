package edu.hm.mineandroidsweeper.misc;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;

/**
 * TODO: Document type FileUtil.
 */
public class FileUtil {
    
    public static void saveObject(final OutputStream outStream, final Object object)
            throws IOException {
        ObjectOutputStream objectOutputStream = null;
        objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
    }
    
    public static Object loadObject(final InputStream inStream) throws OptionalDataException,
    ClassNotFoundException, IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        return object;
    }
    
}

