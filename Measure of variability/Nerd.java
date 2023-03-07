import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
public class Nerd {
    final int MAX_VALUE = 100; // You can change this depending on the size of the data

    public Nerd(){
        try {
            // This is where all lines of code should be written
            ArrayList <Integer> myNum = new ArrayList<>();
            String[] parts = new String[MAX_VALUE];
            File inputFile = new File("input.txt"); // NOTE: You need to make a text file and input the datas there seperated by comas, make sure you had the right directory that share with this code and no empty spaces in your text file
            Scanner scanner = new Scanner(inputFile); // Getting input from the text file
            Scanner sc = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parts = line.split(","); 
            }
            

            for(String i : parts){
                myNum.add(Integer.parseInt(i)); // Migrate the values to the arraylist
            }
            Collections.sort(myNum);
            System.out.print("S for sample, N for population: ");
            char choice = sc.next().toUpperCase().charAt(0);
            int range = myNum.get(myNum.size()-1) - myNum.get(0); // Range


                /*SOlVING THE MAD */
                double MAD = 0;
                double x_mean = 0;
                for (int i : myNum) { // Solving mean
                    x_mean += i;
                }
                double mean = x_mean/myNum.size();
                
                    for (int x : myNum) {
                        MAD += Math.abs(x - mean); // solving the summation from the formula MAD = ∑|x - x_mean|
                    }
                    double mad_result = MAD/myNum.size(); // MAD result
    
    
            switch(choice){
                case 'S':
                
                /*SOLVING THE VARIANCE OF SAMPLE */
                double x_squared_sample = 0;
                for (int x : myNum) { // Summation of x2
                    int temp_sample = x*x;
                    x_squared_sample += temp_sample;
                    temp_sample = 0;
                }
                double x_sum_squared_sample = 0;
                for (int x : myNum) { // Summation of (x)2
                    x_sum_squared_sample += x;
                }
                double variance_sample = (x_squared_sample/(myNum.size()-1)) - ((x_sum_squared_sample*x_sum_squared_sample)/(myNum.size()*(myNum.size()-1))); // Computing the Variance
                
                System.out.println("MEASURE OF VARIABILITY(SAMPLE)");
                System.out.println("==========================");
                System.out.println("Range : "+ range);
                System.out.println("Length : "+myNum.size());
                System.out.printf("MAD : %.2f\n", mad_result);
                System.out.printf("Variance : %.2f\n", variance_sample);
                System.out.printf("Standard Deviation : %.2f\n", Math.sqrt(variance_sample));
                System.out.println("==========================");
                break;

                case 'N':
                /*SOLVING THE VARIANCE OF POPULATION*/
                double x_squared_population = 0;
                for(int x : myNum){ // Solving the summation of x2
                    int temp_population = x*x;
                    x_squared_population += temp_population;
                    temp_population = 0;
                }

                double x_sum_squared_population = 0;
                for(int x : myNum){ // Solving the summation of (x/N)2 
                    x_sum_squared_population+= x;
                }
                double variance_population = (x_squared_population/myNum.size()) - Math.pow((x_sum_squared_population/myNum.size()), 2);
                System.out.println("MEASURE OF VARIABILITY(POPULATION)");
                System.out.println("==========================");
                System.out.println("Range : "+ range);
                System.out.println("Length : "+myNum.size());
                System.out.printf("MAD : %.2f\n", mad_result);
                System.out.printf("Variance: %.2f\n", variance_population);
                System.out.printf("Standard Deviation: %.2f\n", Math.sqrt(variance_population));
                System.out.println("==========================");
                break;
                
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[]args) {
        new Nerd();
    }
}
