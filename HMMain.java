package T5;

import java.io.*;
import java.util.*;

public class HMMain {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HM.HashMap<String, String> map = new HM.HashMap();
        ArrayList<HM.HashMap<String, String>> al = new ArrayList<>();

        String str = br.readLine();
        while (str.equals("Terminate") == false) {
            try {
                if (str.startsWith("Set")) {
                    String[] parts = str.split(" ");
                    String key = parts[1];
                    String val = parts[2];
                    map.set(key, val);
                } else if (str.startsWith("Get")) {
                    String[] parts = str.split(" ");
                    String key = parts[1];
                    System.out.println(map.get(key));

                } else if (str.startsWith("Unset")) {
                    String[] parts = str.split(" ");
                    String key = parts[1];
                    System.out.println(map.unset(key));

                } else if (str.startsWith("Count")) {
                    String[] parts = str.split(" ");
                    String values = parts[1];
                    map.count(values);
                } else if (str.startsWith("Update")) {
                    String[] parts = str.split(" ");
                    String key = parts[1];
                    String value = parts[2];
                    map.update(key,value);
                }else if (str.startsWith("Begin")) {
                    al.add(map);
                    map = new HM.HashMap();
                    int i = al.size() - 1;
                    while (i >= 0) {
                        HM.HashMap<String, String> tempMap = al.get(i);
                        for (int index = 0; index < tempMap.size(); index++) {
                            map.set((String) tempMap.Keyvariables().toArray()[index],
                                    (String) tempMap.Values().toArray()[index]);
                        }
                        break;
                    }
                } else if (str.startsWith("Rollback")) {
                    int i = 0;
                    while (i < map.size()) {
                        map.unset((String) map.Keyvariables().toArray()[i]);
                    }
                    map = al.get(al.size() - 1);
                    al.remove(al.size() - 1);
                } else if (str.startsWith("Commit")) {
                    int i = al.size() - 1;
                    while (i >= 0) {
                        HM.HashMap<String, String> tempMap = al.get(i);
                        for (int index = 0; index < map.size(); index++) {
                            tempMap.set((String) map.Keyvariables().toArray()[index], (String) map.Values().toArray()[index]);
                        }
                        break;
                    }

                }else if (str.startsWith("Size")) {
                    System.out.println(map.size());
                } else if (str.startsWith("Keyset")) {
                    System.out.println(map.Keyvariables());
                } else if (str.startsWith("Display")) {
                    map.display();
                }else if (str.startsWith("Values")) {
                    System.out.println(map.Values());
                }else if (str.startsWith("Containskey")) {
                    String[] parts = str.split(" ");
                    String key = parts[1];
                    System.out.println(map.containsKey(key));
                }else {
                    System.out.println("Please provide a valid Command ");
                }
            } catch (Exception e){
                System.out.println("Please follow the correct Syntax");
            }
            str = br.readLine();
        }
    }
}