import java.util.*;

public class Bs {
    private Map<String, LinkedList<String>> transferMap;

    public Bs(Map<String, LinkedList<String>> transferMap){
        this.transferMap = transferMap;
    }

    public String intersecting(HashSet<String> visitStart, HashSet<String> visitFinish){
        for (Map.Entry<String, LinkedList<String>> entry: transferMap.entrySet()){
            if(visitStart.contains(entry.getKey()) && visitFinish.contains(entry.getKey())){

                return entry.getKey();
            }
        }
        return "not";
    }

    public void bfs( HashSet<String> visit, LinkedList<String> queue, List<String> path){
        String city = queue.removeFirst();
        for (String tmpCity : transferMap.get(city)){
            if(!visit.contains(tmpCity)){
                visit.add(tmpCity);
                path.add(tmpCity);
                queue.add(tmpCity);
            }
        }
    }

    public void printPath(List<String> pathStart, List<String> pathFinish, String intersecting){
        System.out.println("");
        System.out.println("Двунаправленный поиск:");
        System.out.println("Путь: ");
        StringBuilder pathBuilderStart = new StringBuilder();
        for(String tmpCity1 : pathStart){
            pathBuilderStart.append(tmpCity1);
            pathBuilderStart.append(" -> ");

            if (tmpCity1.equals(intersecting)) break;
        }
        pathBuilderStart.delete(pathBuilderStart.length() - 3, pathBuilderStart.length());
        System.out.println(pathBuilderStart);


        StringBuilder pathBuilderFinish = new StringBuilder();
        for(String tmpCity2 : pathFinish){
            pathBuilderFinish.append(tmpCity2);
            pathBuilderFinish.append(" -> ");

//            if (tmpCity2.equals(intersecting)) break;
        }
        pathBuilderFinish.delete(pathBuilderFinish.length() - 3, pathBuilderFinish.length());
        System.out.println(pathBuilderFinish);
    }

    public void search(String startCity, String finishCity){
        LinkedList<String> queueStart = new LinkedList<>();
        LinkedList<String> queueFinish = new LinkedList<>();



        HashSet<String> visitStart = new HashSet<>();
        HashSet<String> visitFinish = new HashSet<>();

//        Map<String, Boolean> visitStart = new HashMap<>();
//        Map<String, Boolean> visitFinish = new HashMap<>();

        queueStart.push(startCity);
        queueFinish.push(finishCity);


        visitStart.add(startCity);
        visitFinish.add(finishCity);

        List<String> pathStart = new ArrayList<>();
        List<String> pathFinish = new ArrayList<>();

        pathFinish.add(finishCity);
        pathStart.add(startCity);

        while (!queueFinish.isEmpty() && !queueStart.isEmpty()){
            bfs(visitStart,queueStart, pathStart);
            bfs(visitFinish,queueFinish, pathFinish);

            String intersectingCity = intersecting(visitStart,visitFinish);

            if (!intersectingCity.equals("not")){

                printPath(pathStart,pathFinish, intersectingCity);
                return;
            }
        }

    }


}
