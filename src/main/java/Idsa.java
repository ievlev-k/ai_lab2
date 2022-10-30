import com.oracle.xmlns.internal.webservices.jaxws_databinding.SoapBindingParameterStyle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Idsa {
    private Map<String, LinkedList<String>> transferMap;

    public Idsa(Map<String, LinkedList<String>> transferMap){
        this.transferMap = transferMap;
    }

    public void search(String startCity, String finishCity){
        Dlsa dlsa = new Dlsa(transferMap);
        System.out.println("");
        System.out.println("Поиск с итеративным углублением:");

        for (int i = 1; i < Integer.MAX_VALUE; i++){
            if (dlsa.forSearchWithIterativeDeepening(startCity,finishCity, i)){
                System.out.println("Найдено с глубиной " + i);
                return;
            }
        }
    }

}
