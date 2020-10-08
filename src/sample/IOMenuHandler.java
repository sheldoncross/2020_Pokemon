package sample;
/***********************************************************************
 * Title: Transfer a file via socket
 * Author: Real Gagnon
 * Date: 26-03-2020
 * Availability: https://www.rgagnon.com/javadetails/java-0542.html
 *
 **********************************************************************/

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class IOMenuHandler implements EventHandler<ActionEvent> {
    private IOMenu menu;
    public String currentFile;
    public final static int SOCKET_PORT_LOAD = 13268;
    public final static int SOCKET_PORT_SAVE = 13267;
    public final static String SERVER = "127.0.0.1";
    public final static String File_to_send = "saves/monsterSave.txt";
    public final static String File_to_receive = "saves/monsterSave.txt";
    public final static int FILE_SIZE = 6022386;

    //Runnable run1 = new;


    public IOMenuHandler(IOMenu menu) {
        this.menu = menu;
    }

    @Override
    public void handle(ActionEvent e) {

        Runnable run1 = new Client();
        Runnable run2 = new Server();
        Thread cSave = new Thread(run1);
        Thread cLoad = new Thread(run2);


        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        ServerSocket servsock = null;
        Socket sock = null;
        int bytesRead;
        int current = 0;
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;


        System.out.println(e.getSource());
        if (e.getSource() == menu.load) {
            //load
            System.out.println("LOAD");
            File file = menu.fileChooser.showOpenDialog(menu.mainStage);
            if (file == null) {
                System.out.println("NO FILE SELECTED");
            } else {
                currentFile = file.toString();
                try {
                    load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } else if (e.getSource().equals(menu.save)) {
            //save
            try {
                save();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource().equals(menu.cloudSave)) {
            //This means this program will work as server and will send to program that will work as client.
            try {
                //Start a client thread here <--------
                servsock = new ServerSocket((SOCKET_PORT_LOAD));
                //Thread thread = new Thread(new Client());
                System.out.println("Waiting...");

                //thread.start();

                save();
                cSave.run();
                sock = servsock.accept();
                System.out.println("Accepted connection: " + sock);
                //sends the file
                File myFile = new File(File_to_send);
                byte[] mybytearray = new byte[(int) myFile.length()];
                fis = new FileInputStream(myFile);
                bis = new BufferedInputStream(fis);
                bis.read(mybytearray, 0, mybytearray.length);
                os = sock.getOutputStream();
                System.out.println("Sending" + File_to_send + " (" + mybytearray.length + "bytes)");
                os.write(mybytearray, 0, mybytearray.length);
                os.flush();
                System.out.println("Done.");
                if (bis != null) bis.close();
                if (os != null) os.close();
                if (sock != null) sock.close();
                if (servsock != null) servsock.close();
                cSave.stop();


            } catch (IOException ex) {
                ex.printStackTrace();
            }


        } else if (e.getSource().equals((menu.cloudLoad))) {
            //This means this program will receive the file as client from other program as server.
            try {
                //Trying to start server here?? <-------
                //Thread thread = new Thread(new Server());
                //thread.start();

                sock = new Socket(SERVER, SOCKET_PORT_SAVE);
                cLoad.run();
                System.out.println("Connecting...");

                //Receiving file
                byte[] mybytearray = new byte[FILE_SIZE];
                InputStream is = sock.getInputStream();
                fos = new FileOutputStream(File_to_receive);
                bos = new BufferedOutputStream(fos);
                bytesRead = is.read(mybytearray, 0, mybytearray.length);
                current = bytesRead;

                do {
                    bytesRead = is.read(mybytearray, current, (mybytearray.length - current));
                    if (bytesRead >= 0) current += bytesRead;
                } while (bytesRead > -1);

                bos.write(mybytearray, 0, current);
                bos.flush();
                System.out.println("File" + File_to_receive + " downloaded (" + current + " bytes read)");
                currentFile = File_to_receive.toString();
                try {
                    load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                if (fos != null) fos.close();
                if (bos != null) bos.close();
                if (sock != null) sock.close();
                sock.close();
                cLoad.stop();
                update();


            } catch (IOException ex) {
                ex.printStackTrace();
            }
            cLoad.stop();
        }
    }

    private void update() throws FileNotFoundException {
        System.out.println("Updating...");

        menu.fightBox.monsterInfoBox.updateBar();
        menu.fightBox.monsterInfoBox.updateText();
    }

    //Save Function
    public void save() throws IOException {
        FileWriter fileWriter = new FileWriter("saves/monsterSave.txt");

        fileWriter.append(menu.fightBox.monster.toString());
        fileWriter.append('\n');
        fileWriter.append(menu.fightBox.target.toString());

        fileWriter.flush();
        fileWriter.close();

        //write to a file

        //close writer
    }

    //Load Function
    private void load() throws IOException {
        int counter = 0;
        if (currentFile == null) {
            return;
        }

        FileReader fileReader = new FileReader(currentFile);
        //read file get monster and target data
        String line = "";
        String data[] = {"nothing", "nothing", "nothing", "nothing", "nothing", "nothing", "nothing", "nothing", "nothing", "nothing", "nothing"};
        Monster m = null;
        Monster m2 = null;
        // "14" -> int 14
        try {
            BufferedReader br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {

                data = line.split(", ");

                if (counter == 0) {
                    //menu.fightBox.monster.HP = (int)data[0];

                    String skills[] = {data[0], data[1], data[2], data[3]};
                    List<String> list = Arrays.asList(skills);
                    int skillDamage[] = {Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]), Integer.parseInt(data[7])};
                    //m = new Monster(skills, skillDamage, Integer.parseInt((data[10])), data[9], data[8]);
                    //menu.fightBox.monster.skillsArrayList = list;
                    //menu.fightBox.monster.name = data[8];
                    //menu.fightBox.monster.type = data[9];
                    menu.fightBox.monster.HP = Integer.parseInt(data[10]);
                    update();

                } else if (counter == 1) {
                    String skills[] = {data[0], data[1], data[2], data[3]};
                    int skillDamage[] = {Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]), Integer.parseInt(data[7])};
                    //m2 = new Monster(skills, skillDamage, Integer.parseInt((data[10])), data[9], data[8]);
                    // menu.fightBox.target.name = data[8];
                    //menu.fightBox.target.type = data[9];
                    menu.fightBox.target.HP = Integer.parseInt(data[10]);
                    update();

                } else {
                    break;
                }
                counter++;
                //counter = counter + 1;


            }
            update();

            br.close();
            //System.out.println(m.toString());
            //System.out.println(m2.toString());
            fileReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //create new instance of fightbox
        //FightBox newFightBox = new FightBox(m, m2);
        //menu.setFightBox(newFightBox);

        //set IOMenu Fightbox -> new fightbox
        //need to update text and progress bar
    }
}
