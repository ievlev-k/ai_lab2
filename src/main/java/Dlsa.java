import java.util.*;

public class Dlsa {
    private Map<String, LinkedList<String>> transferMap;

    private static final List<String> path = new ArrayList<>();
    boolean find = false;
    public Dlsa(Map<String, LinkedList<String>> transferMap){
        this.transferMap = transferMap;
    }



    public void searchInDepthLimited(String startCity, String finishCity, int limit){
        ArrayList<String> visited = new ArrayList<>();
        HashSet<String> visitedCity = new HashSet<>();
        search(startCity, finishCity, visited, limit);
        if (find){
            System.out.println("Поиск с ограничением глубины:");

            System.out.print("Путь: ");
            StringBuilder pathBuilder = new StringBuilder();
            path.forEach(city -> {
                pathBuilder.append(city);
                pathBuilder.append(" -> ");
            });
            pathBuilder.delete(pathBuilder.length() - 3, pathBuilder.length());
            System.out.println(pathBuilder);
        }else {
//            System.out.println("Путь не наден!");
        }


        path.clear();



    }

    public boolean forSearchWithIterativeDeepening(String startCity, String finishCity, int limit){
        searchInDepthLimited(startCity, finishCity,limit);
        path.clear();
        return find;
    }

    public void search(String startCity, String finishCity,ArrayList<String> visited , int limited ){


        visited.add(startCity);
        ArrayList<String> currentVisitedCity = (ArrayList<String>) visited.clone();
        path.add(startCity);

        if(startCity.equals(finishCity) && ! find){
            currentVisitedCity.clear();
            find = true;

            return;
        }else if (limited != 0){
            for (String tmpCity : transferMap.get(startCity)){
                if (!currentVisitedCity.contains(tmpCity) ){

                    search(tmpCity, finishCity, currentVisitedCity, limited - 1 );
//                    currentVisitedCity.clear();
                    currentVisitedCity =  (ArrayList<String>) visited.clone();
                    if (find){
                        return;
                    }


                }
            }
        }

    }


}
