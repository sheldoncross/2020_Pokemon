package sample;
/***********************************************************************
 * Title: Transfer a file via socket
 * Author: Real Gagnon
 * Date: 26-03-2020
 * Availability: https://www.rgagnon.com/javadetails/java-0542.html
 *
 **********************************************************************/

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable{

    public final static int SOCKET = 13268;      // Port
    public final static String SERVER = "127.0.0.1";  // localhost is 127.0.0.1,
    public final static String
            FILE = "saves/monsterSave.txt";

    public final static int SIZE = 6022386; // hard coded size

    @Override
    public void run(){
        int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock = null;
        try {
            sock = new Socket(SERVER, SOCKET);
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
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sock != null) {
                try {
                    sock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}