import java.util.*;

public class BestFirstSearchAlgorithm {
    private Map<String, LinkedList<String>> transferMap;
    private Map<String, Integer> straightLineDistance;

    public BestFirstSearchAlgorithm(Map<String, LinkedList<String>> transferMap, Map<String, Integer> straightLineDistance){
        this.transferMap = transferMap;
        this.straightLineDistance = straightLineDistance;
    }


    private void printPath(ArrayList<String> path){
        System.out.println(" ");
        System.out.println("Жадный поиск по первому наилучшему соответствию:");
        System.out.print("Путь: ");
        StringBuilder pathBuilder = new StringBuilder();
        path.forEach(city -> {
            pathBuilder.append(city);
//            pathBuilder.append("(" + straightLineDistance.get(city) + ")");
            pathBuilder.append(" -> ");
        });
        pathBuilder.delete(pathBuilder.length() - 3, pathBuilder.length());
        System.out.println(pathBuilder);

    }

    public void search(String startCity, String finishCity){
        ArrayList<String> path = new ArrayList<>();
        LinkedList<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();

        visited.add(finishCity);

        queue.push(finishCity);

        while(!queue.isEmpty()){

            String city = queue.removeFirst();
            path.add(city);
            visited.add(city);

            if(city.equals(startCity)){
                printPath(path);
                return;
            }else{
                LinkedList<String> allNeighbourhood = transferMap.get(city);


                Collections.sort(allNeighbourhood, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return straightLineDistance.get(o2) - straightLineDistance.get(o1);
                    }
                });

                for (String tmpCity : allNeighbourhood){
                    if (!visited.contains(tmpCity)){
                        queue.addFirst(tmpCity);

                    }

                }
            }
        }
    }
}
