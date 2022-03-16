package DND;

import org.json.*;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Vector;

public class Storage {
    // fields
    private static String userHomeDir;
    private static String systemOS;
    private static String storageDir;
    private static JSONObject dirStore;
    private static JSONArray classArray;
    private static JSONArray raceArray;
    private static JSONArray backgroundArray;
    private static JSONArray characterArray;
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
        File characters = new File(storageDir + "Characters");
        File dirStorePath = new File(storageDir + "dirStorage.json");

        makeIfAbsent(dnd);
        makeIfAbsent(classes);
        makeIfAbsent(races);
        makeIfAbsent(backgrounds);
        makeIfAbsent(characters);
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
        if (dirStore.getJSONArray("characters") == null) {
            dirStore.put("characters", "[]");
        }
        FileOutputStream writer = new FileOutputStream(storageDir);
        writer.write(dirStore.toString().getBytes());
        writer.close();

        classArray = dirStore.getJSONArray("classes");
        raceArray = dirStore.getJSONArray("races");
        backgroundArray = dirStore.getJSONArray("backgrounds");
        characterArray = dirStore.getJSONArray("characters");

    }

    public static boolean store(Storeable clas, boolean bool) {
        String storeSpot;
        JSONArray arrayToStore;
        switch (clas.storeType) {
            case DNDCLASS:
                storeSpot = "Classes";
                arrayToStore = classArray;
                break;
            case RACE:
                storeSpot = "Races";
                arrayToStore = raceArray;
                break;
            case BACKGROUND:
                storeSpot = "Backgrounds";
                arrayToStore = backgroundArray;
                break;
            case CHARACTER:
                storeSpot = "Characters";
                arrayToStore = characterArray;
            default:
                storeSpot = null;
                arrayToStore = null;
                break;
        }
        File file = new File(storageDir + storeSpot + s + clas.name + ".ser");

        try {
            if (bool || !(file.exists())) {
                FileOutputStream fileOut = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(clas);
                out.close();
                fileOut.close();
                if (!checkValueArray(clas.name, arrayToStore)) {
                    arrayToStore.put(clas.name);
                    dirStore.remove(storeSpot);
                    dirStore.put(storeSpot, arrayToStore);
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

    public static boolean store(DNDClass clas) {
        return store(clas, false);
    }

    public static Storeable retrieve(String className, Storeable.StoreableType type) throws Exception {
        String retrieveLocation;
        switch (type) {
            case DNDCLASS:
                retrieveLocation = "Classes";
                break;

            default:
                retrieveLocation = null;
                break;
        }
        Storeable returnClass;
        File file = new File(storageDir + retrieveLocation + s + className + ".ser");
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        returnClass = (Storeable) objIn.readObject();
        fileIn.close();
        objIn.close();
        return returnClass;
    }

    public static String[] getList(String target) {
        JSONArray resultJSON = dirStore.getJSONArray(target);
        int length = resultJSON.length();
        Vector<String> vector = new Vector<String>();
        String[] result;
        for (int i = 0; i < length; i++) {
            vector.add(resultJSON.getString(i));
        }
        result = Arrays.copyOf((String[]) vector.toArray(), length);
        return result;
    }
}
