import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;
import java.math.*;;
public class Regex {
    public static void main(String[] args) {
       
        HashMap <Integer, Integer> ClassPair = new HashMap<>();
        String input = "12,12,11,11,11,64,59,13,12,11,11,11,63,11,16,14,16,11,12,61,12,17,18,64,11,11,13,12,21,19,11,11,62,65,30,11,11,12,29,61,11,25"; // Insert here seperated by space and comma
        String [] myNums = input.split("[,\\s]+"); // Separate the string by space and comma only
        
        int [] myArr = new int[myNums.length];
        int x = myArr.length;
      for(int i = 0; i < myNums.length; i++){
        myArr[i] = Integer.parseInt(myNums[i]); // Converting the String into int
      }
      Arrays.sort(myArr);
      System.out.println(Arrays.toString(myArr));
      System.out.println("Length = "+myNums.length);
      int Range = (myArr[x - 1] - myArr[0]);
      System.out.println("Range = "+ Range);
      double Sturges = 1 + (3.322 * (Math.log10(x))) ;
      System.out.println("Sturges = " + Sturges);
      int Interval = (int) Math.round(Range/Sturges);
      System.out.println("Interval = " + Interval);
     
      // Class Interval
      int start = myArr[0];
      System.out.println();
      
      while(start < myArr[myArr.length-1]){ // Getting the lowest interval and highest and putting it into a map
        int temp = start + Interval;
        ClassPair.put(start, temp -1 );
        start = temp;
      }
      // Sorting the map because im lazy asf 
      List<Map.Entry<Integer, Integer>> list = new ArrayList<>(ClassPair.entrySet());
        
      Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
          @Override
          public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
              return o1.getKey().compareTo(o2.getKey());
          }
      });
      
      LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap<>();
      
      for (Map.Entry<Integer, Integer> entry : list) {
          sortedMap.put(entry.getKey(), entry.getValue());
      }
      
     
      // Class Frequency
      int Frequency = 0;
      int tempFrequency = 0; // recording the cf
      int LCF = x;
      double rf;
      for(Map.Entry<Integer, Integer> entry: sortedMap.entrySet()){
        Integer key = entry.getKey();
        Integer value = entry.getValue();
      
        for(int i = 0; i < myArr.length; i ++){
          if(myArr[i] >= key && myArr[i] <= value){
            if(myArr[i] < value || myArr[i] == value){
              Frequency++;
            }
          }
        }
      
        tempFrequency += Frequency; 
        rf = ((double) Frequency) / ((double) x) * 100;
        System.out.println("Class Interval = " + key + " - " + value);
        System.out.println("Frequency = " + Frequency);
        System.out.println("Class Mark = " + ((key + value )/ 2.00));
        System.out.println("Class Boundary = " + (key - 0.50) + " - "  + (value + .50));
        System.out.println("<CF = " + tempFrequency);
        System.out.println(">CF  = " + LCF );
        System.out.println("%rf = " + Math.round(rf));
        System.out.println();
        LCF -= Frequency;
        Frequency = 0;
      }
    }
}
