import java.util.*;

public class ASearchAlgorithm {
    private Map<String, Map<String,Integer>> transferMapWithData;
    private Map<String, Integer> straightLineDistance;
    private Map<String, LinkedList<String>> transferMap;
    private static final List<String> path = new ArrayList<>();
    public ASearchAlgorithm(Map<String, Map<String,Integer>> transferMapWithData, Map<String, Integer> straightLineDistance,  Map<String, LinkedList<String>> transferMap){
        this.transferMapWithData = transferMapWithData;
        this.straightLineDistance = straightLineDistance;
        this.transferMap = transferMap;
    }

    public void search(String startCity, String finishCity){
        LinkedList<String> queue  = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        HashSet<String> visited = new HashSet<>();

        queue.add(finishCity);
        int sum = 0;
        while (!queue.isEmpty()){

            String city = queue.removeFirst();
            path.add(city);
            visited.add(city);

            if(city.equals(startCity)){
                System.out.println("");
                System.out.println("A*:");
                System.out.print("Путь: ");
                StringBuilder pathBuilder = new StringBuilder();
                path.forEach(c -> {
                    pathBuilder.append(c);
                    pathBuilder.append(" -> ");
                });
                pathBuilder.delete(pathBuilder.length() - 3, pathBuilder.length());
                System.out.println(pathBuilder);
                return;
            }else {
                LinkedList<String> allNeighbourhood = transferMap.get(city);

                Collections.sort(allNeighbourhood, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return (straightLineDistance.get(o2) + transferMapWithData.get(city).get(o2)) - (straightLineDistance.get(o1) + transferMapWithData.get(city).get(o1));
                    }
                });




                if ( map.containsKey(allNeighbourhood.getLast())){
                    if (map.get(allNeighbourhood.getLast()) < sum + transferMapWithData.get(city).get(allNeighbourhood.getLast()) && !visited.contains(allNeighbourhood.getLast())) {
                        System.out.println(city + ":");
                        for (String tmpCity : allNeighbourhood) {
                            System.out.println(tmpCity);
                            int t = straightLineDistance.get(tmpCity) + transferMapWithData.get(city).get(tmpCity);
                            System.out.println(tmpCity+t);
                        }
                        continue;
                    }
                }

                for (String tmpCity : allNeighbourhood) {
                    map.put(tmpCity, transferMapWithData.get(city).get(tmpCity) + sum);
                }
                sum = sum + transferMapWithData.get(city).get(allNeighbourhood.getLast());

                for (String tmpCity : allNeighbourhood) {
                    if (!visited.contains(tmpCity)) {
                        queue.addFirst(tmpCity);
                    }
                }





            }

        }
    }

}
