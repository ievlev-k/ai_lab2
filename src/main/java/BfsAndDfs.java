import java.util.*;

public class BfsAndDfs {

    private static final ArrayList<String> pathBfs = new ArrayList<>();
    private static final ArrayList<String> pathDfs = new ArrayList<>();
    private Map<String, LinkedList<String>> transferMap;
    boolean check = false;
    int depth;
    public BfsAndDfs(Map<String, LinkedList<String>> transferMap ){
        this.transferMap = transferMap;

    }

    private void printPath(ArrayList<String> path){
        System.out.print("Путь: ");
        StringBuilder pathBuilder = new StringBuilder();
        path.forEach(city -> {
            pathBuilder.append(city);
            pathBuilder.append(" -> ");
        });
        pathBuilder.delete(pathBuilder.length() - 3, pathBuilder.length());
        System.out.println(pathBuilder);

    }

    public void searchInWidth(String startCity, String finishCity){
        System.out.println("Поиск в ширину:");
        LinkedList<String> queue = new LinkedList<>();
        Map<String, Boolean> checkMap = new HashMap<>();
        queue.push(startCity);
        checkMap.put(startCity, false);

        while (!queue.isEmpty()){

            String city = queue.removeFirst();

            if(checkMap.get(city)){
                continue;
            }


            pathBfs.add(city);
            if(city.equals(finishCity)){

                break;
            }

            checkMap.put(city, true);
            LinkedList<String> allNeighbors = transferMap.get(city);

            if (allNeighbors == null){
                continue;
            }

            for (String tmpCity : allNeighbors){

                if(!checkMap.containsKey(tmpCity)){
                    checkMap.put(tmpCity,false);
                }
                if (!checkMap.get(tmpCity)){
                    queue.add(tmpCity);
                }
            }

        }
        printPath(pathBfs);
        System.out.println(" ");
    }



    public void searchInDepth(String startCity, String finishCity){
        this.check = false;
        Map<String, Boolean> checkMap = new HashMap<>();
        searchInDepthAlgorithm(startCity, finishCity,checkMap);
        System.out.println("Поиск в глубину:");
        if (check){
            printPath(pathDfs);
            System.out.println(" ");
            return;}


    }

    public void searchInDepthAlgorithm(String startCity, String finishCity, Map<String, Boolean> checkMap){


        Map<String, Boolean> currentCheckMap = checkMap;

        pathDfs.add(startCity);



        if (startCity.equals(finishCity)){

            this.check = true;
        }



        currentCheckMap.put(startCity, true);

        LinkedList<String> allNeighbors = transferMap.get(startCity);
        if(allNeighbors == null) System.out.println("Error");

        for (String tmpCity : allNeighbors){


            if(!currentCheckMap.containsKey(tmpCity)){
                currentCheckMap.put(tmpCity,false);
            }
            if (!currentCheckMap.get(tmpCity)){
                searchInDepthAlgorithm(tmpCity ,finishCity,currentCheckMap);

            }
        }

    }



}
