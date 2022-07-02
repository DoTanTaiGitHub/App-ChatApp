/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;
import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class Convert {

    public static Object fromByteArrayToObject(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }

    public static byte[] fromObjectToByteArray(Object obj)
            throws IOException {
        if (Objects.nonNull(obj)) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try (ObjectOutputStream os = new ObjectOutputStream(bos)) {
                os.writeObject(obj);
            }
            return bos.toByteArray();
        }
        return null;
    }

}
