package sample;
/***********************************************************************
 * Title: Transfer a file via socket
 * Author: Real Gagnon
 * Date: 26-03-2020
 * Availability: https://www.rgagnon.com/javadetails/java-0542.html
 *
 **********************************************************************/

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    public final static int SOCKET = 13267;  // Port
    public final static String FILE = "saves/monsterSave.txt";

    @Override
    public void run()  {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        ServerSocket servsock = null;
        Socket sock = null;
        try {
            servsock = new ServerSocket(SOCKET);
            while (true) {
                System.out.println("Waiting...");
                try {
                    sock = servsock.accept();
                    System.out.println("Accepted connection : " + sock);
                    // send file
                    File myFile = new File(FILE);
                    byte[] byteArray = new byte[(int) myFile.length()];
                    fis = new FileInputStream(myFile);
                    bis = new BufferedInputStream(fis);
                    bis.read(byteArray, 0, byteArray.length);
                    os = sock.getOutputStream();
                    System.out.println("Sending " + FILE + "(" + byteArray.length + " bytes)");
                    os.write(byteArray, 0, byteArray.length);
                    os.flush();
                    System.out.println("Done.");
                } finally {
                    if (bis != null) bis.close();
                    if (os != null) os.close();
                    if (sock != null) sock.close();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (servsock != null) {
                try {
                    servsock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

