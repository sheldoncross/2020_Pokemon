import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client_Backup {

    public final static int SOCKET_PORT = 13268;      // Port
    public final static String SERVER = "127.0.0.1";  // localhost is 127.0.0.1,
    public final static String
            FILE = "saves/monsterSave.txt";
    public final static int SIZE = 6022386; // hard coded size

    public static void main(String[] args) throws IOException {
        int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock = null;
        try {
            sock = new Socket(SERVER, SOCKET_PORT);
            System.out.println("Connecting...");

            // receive file
            byte[] byteArray = new byte[SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(byteArray, 0, byteArray.length);
            current = bytesRead;

            do {
                bytesRead =
                        is.read(byteArray, current, (byteArray.length - current));
                if (bytesRead >= 0) current += bytesRead;
            } while (bytesRead > -1);

            bos.write(byteArray, 0, current);
            bos.flush();
            System.out.println("File " + FILE + " downloaded (" + current + " bytes read)");
        } finally {
            if (fos != null) fos.close();
            if (bos != null) bos.close();
            if (sock != null) sock.close();
        }
    }

}