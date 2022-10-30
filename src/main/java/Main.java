import javax.sound.midi.MidiEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, LinkedList<String>> transferMap = new HashMap<>();
        Map<String, Map<String,Integer>> transferMapWithData = new HashMap<>();
        Map<String, Integer> straightLineDistance = new HashMap<>();
        try {
            File file = new File("C:\\Users\\KuPuK\\IdeaProjects\\SIILab2\\src\\main\\java\\file\\file.txt");
            Scanner scannerFile = new Scanner(file);
            while (scannerFile.hasNextLine()) {
                String[] line = scannerFile.nextLine().trim().split(" ");
                if (!transferMap.containsKey(line[0])){
                    transferMap.put(line[0], new LinkedList<>());
                    transferMapWithData.put(line[0], new HashMap<>());
                }
                transferMap.get(line[0]).add(line[1]);
                transferMapWithData.get(line[0]).put(line[1],Integer.parseInt(line[2]));
                if (!transferMap.containsKey(line[1])){
                    transferMap.put(line[1], new LinkedList<>());
                    transferMapWithData.put(line[1], new HashMap<>());
                }
                transferMap.get(line[1]).add(line[0]);
                transferMapWithData.get(line[1]).put(line[0],Integer.parseInt(line[2]));
            }

            File file1 = new File("C:\\Users\\KuPuK\\IdeaProjects\\SIILab2\\src\\main\\java\\file\\file2");
            Scanner scannerFile1 =  new Scanner(file1);
            while (scannerFile1.hasNextLine()){
                String[] line1 = scannerFile1.nextLine().trim().split(" ");
                straightLineDistance.put(line1[0], Integer.parseInt(line1[1]));
            }

        }catch (FileNotFoundException fileNotFoundException){
            System.out.println("нет файла!");
        }




        BfsAndDfs bfsAndDsf = new BfsAndDfs(transferMap);
        bfsAndDsf.searchInWidth("Рига", "Уфа");
        bfsAndDsf.searchInDepth("Рига", "Уфа");
//
        Dlsa dlsa = new Dlsa(transferMap);

        dlsa.searchInDepthLimited("Рига", "Уфа",4);

        Idsa idsa = new Idsa(transferMap);
        idsa.search("Рига", "Уфа");
        Bs bs = new Bs(transferMap);
        bs.search("Рига", "Уфа");


        BestFirstSearchAlgorithm bestFirstSearchAlgorithm = new BestFirstSearchAlgorithm(transferMap, straightLineDistance);
        bestFirstSearchAlgorithm.search("Рига", "Уфа");

        ASearchAlgorithm aSearchAlgorithm = new ASearchAlgorithm(transferMapWithData, straightLineDistance,transferMap);
        aSearchAlgorithm.search("Рига", "Уфа");
    }

}
