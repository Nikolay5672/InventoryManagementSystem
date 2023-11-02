package items;

import java.io.*;

public class AutoIncrementManager {
    private String filename;
    private int lastID;

    public AutoIncrementManager(String filename, int initialValue) {
        this.filename = filename;
        this.lastID = readLastID();
        if (this.lastID == 0) {
            this.lastID = initialValue;
        }
    }

    public int getNextID() {
        int newID = lastID + 1;
        lastID = newID;
        updateLastID(newID);
        return newID;
    }

    private int readLastID() {
        int lastID = 0;
        try {
            File file = new File(filename);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                lastID = Integer.parseInt(reader.readLine());
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastID;
    }

    private void updateLastID(int newID) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(Integer.toString(newID));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

