package security;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Security {
    public String encriptMD5(String string){
        String endcode = "MD5";
        String error = "Fail";

        try {
            MessageDigest md = MessageDigest.getInstance(endcode);
            md.update(string.getBytes());
            byte[] digest = md.digest();
            String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            return hash;
        } catch (NoSuchAlgorithmException e) {
            return error;
        }
    }

    public String salt(String string){
        byte[] array = new byte[8];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }

    public void writeLog(String string){
        String path = "log.txt";
        File file = new File(path);
        String status = "";
        String pattern = "yyyy-mm-dd hh:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            if (file.createNewFile()){
                status = dateFormat.format(new Date() + " - LOG : File " + path + " was created !");
            }
            else {
                status = "";
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.append(status);
            bw.newLine();
            bw.append(dateFormat.format(new Date()) + " - LOG : " + string);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
