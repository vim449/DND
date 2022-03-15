package DND;

import org.json.*;
import java.io.*;
import java.nio.file.Files;

public class Storage {
    // fields
    private static String userHomeDir;
    private static String systemOS;
    private static String storageDir;
    private static JSONObject dirStore;
    private static JSONArray classArray;
    private static JSONArray raceArray;
    private static JSONArray backgroundArray;
    private static String s; // system-specific seperator

    // methods
    private static boolean checkValueArray(String string, JSONArray array) {
        boolean returnValue = false;
        for (int i = 0; i < array.length(); i++) {
            if (array.get(i).equals(string)) {
                returnValue = true;
            }
        }
        return returnValue;
    }

    private static void makeIfAbsent(File file) {
        if (!(file.exists())) {
            file.mkdir();
        }

    }

    // This has to be called when the program begins running each time, just in case
    // the storage directory is missing.
    public static void initializeFileSystem() throws Exception {

        userHomeDir = System.getProperty("user.home");
        systemOS = System.getProperty("os.name");

        if (systemOS.equals("Windows 10")) {
            storageDir = userHomeDir + "\\AppData\\Local\\DND\\";
            s = "\\";
        } else if (systemOS == "nix") {
            storageDir = userHomeDir + "cache/DND/";
            s = "s";
        }

        File dnd = new File(storageDir);
        File classes = new File(storageDir + "Classes");
        File races = new File(storageDir + "Races");
        File backgrounds = new File(storageDir + "Backgrounds");
        File dirStorePath = new File(storageDir + "dirStorage.json");

        makeIfAbsent(dnd);
        makeIfAbsent(classes);
        makeIfAbsent(races);
        makeIfAbsent(backgrounds);
        if (!(dirStorePath.exists())) {
            dirStorePath.createNewFile();
            FileOutputStream fileWriter = new FileOutputStream(dirStorePath);
            fileWriter.write(("{\"classes\":[],\"races\":[],\"backgrounds\":[]}").getBytes());
            fileWriter.close();
        }

        dirStore = new JSONObject(Files.readString(dirStorePath.toPath()));

        if (dirStore.getJSONArray("classes") == null) {
            dirStore.put("classes", "[]");
        }
        if (dirStore.getJSONArray("races") == null) {
            dirStore.put("races", "[]");
        }
        if (dirStore.getJSONArray("backgrounds") == null) {
            dirStore.put("backgrounds", "[]");
        }

        classArray = dirStore.getJSONArray("classes");
        raceArray = dirStore.getJSONArray("races");
        backgroundArray = dirStore.getJSONArray("backgrounds");

    }

    public static boolean storeClass(DNDClass clas, boolean bool) {
        File file = new File(storageDir + "classes" + s + clas.getName() + ".ser");

        try {
            if (bool || !(file.exists())) {
                FileOutputStream fileOut = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(clas);
                out.close();
                fileOut.close();
                if (!checkValueArray(clas.getName(), classArray)) {
                    classArray.put(clas.getName());
                    dirStore.remove("classes");
                    dirStore.put("classes", classArray);
                    File json = new File(storageDir + "dirStorage.json");
                    FileOutputStream jsonWriter = new FileOutputStream(json);
                    jsonWriter.write(dirStore.toString().getBytes());
                    jsonWriter.close();
                }
            } else if (file.exists() && !bool) {
                return false;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean storeClass(DNDClass clas) {
        return storeClass(clas, false);
    }

    public static DNDClass retrieveClass(String className) throws Exception {
        DNDClass returnClass;
        File file = new File(storageDir + "Classes" + s + className + ".ser");
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        returnClass = (DNDClass) objIn.readObject();
        fileIn.close();
        objIn.close();
        return returnClass;
    }
}
