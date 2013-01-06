package edu.hm.androidsweeper.misc;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/** Utility class for reading and writing objects from/to streams.
 */
public final class FileUtil {
    
    private FileUtil() { }
    
    /** Writes an object to an {@link OutputStream}.
     * @param outStream The stream to write to.
     * @param object The object to write.
     * @throws IOException If an IO error occurs.
     */
    public static void saveObject(final OutputStream outStream, final Object object)
            throws IOException {
        ObjectOutputStream objectOutputStream = null;
        objectOutputStream = new ObjectOutputStream(outStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
    }
    
    /** Reads an object from an {@link InputStream}.
     * @param inStream The stream to read from.
     * @return The object read.
     * @throws IOException If an IO error occurs.
     * @throws ClassNotFoundException if the class of one of the objects in the object graph cannot be found.
     */
    public static Object loadObject(final InputStream inStream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        return object;
    }
    
}

