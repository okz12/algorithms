package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        Double cities[][];
        boolean [] visitedNodes;
        int num_cities;
        int[] visitedNodesOrder;
        int previousNode;
        Double min_dist = new Double(0);
        Double nn_dist;
        Double total_distance;
        int min_idx = 0;

        cities = readFile();

        num_cities = cities.length;

        //initialise visitedNodes
        visitedNodes = new boolean[num_cities]; // initialised to false by default
        visitedNodes[0] = true;

        visitedNodesOrder = new int[num_cities]; // initialised to 0 by default
        total_distance = 0.0;

        for (int i = 0; i < num_cities - 1 ; i++){

            min_dist = Double.MAX_VALUE;
            min_idx = -1;
            previousNode = visitedNodesOrder[i] ;

            for (int j = 1; j < num_cities; j++){
                if ((!visitedNodes[j]) && (previousNode != j)){
                    nn_dist = calcSQDistance(cities[previousNode][0], cities[j][0], cities[previousNode][1], cities[j][1]);

                    //System.out.println("p1 : " + cities[previousNode][0].toString() + "," + cities[previousNode][1].toString() + "p2: " + cities[j][1].toString() + "," + cities[j][0].toString() + " | nn_dist : " + nn_dist.toString() + " | min_dist: " + min_dist.toString() + " | nn_dist - min_dist : " + nn_dist.subtract(min_dist).toString());
                    if (nn_dist < min_dist){ // nn_dist < min_dist
                        min_dist = nn_dist;
                        min_idx = j;
                    }
                }
            }
            visitedNodesOrder[i + 1] = min_idx;
            visitedNodes[min_idx] = true;
            total_distance += Math.sqrt(min_dist);
            if (i % 100 == 0)
                System.out.println("Iteration: " + Integer.toString(i) + " | total_distance: " + total_distance.toString() + " | Previous Node: " + Integer.toString(previousNode + 1) + " | Node: " + Integer.toString(min_idx + 1));
        }

        total_distance += Math.sqrt(calcSQDistance(cities[0][0], cities[min_idx][0], cities[0][1], cities[min_idx][1]));

        for (int i = 0; i<num_cities; i++)
            visitedNodesOrder[i] += 1;
        System.out.println(total_distance.floatValue());
        System.out.println(Arrays.toString(visitedNodesOrder));

    }

    public static Double[][] readFile() throws IOException{
        String fileName = "C:\\Users\\osman\\Practice\\AlgoCourse\\Course 4\\nn.txt";
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        int num_points;
        Double cities[][];
        num_points = Integer.parseInt(br.readLine());
        cities = new Double[num_points][2];

        int idx = 0;
        while ((st = br.readLine()) != null && (idx < num_points)) {
            String[] splitted = st.split("\\s+");
            cities[idx][0] = new Double(splitted[1]);
            cities[idx][1] = new Double(splitted[2]);
            idx++;
        }
        return cities;

    }

    public static Double calcSQDistance(Double x1, Double x2, Double y1, Double y2){
        return Math.pow((x1 - x2),2) + Math.pow((y1 - y2), 2);
    }
}
//BigDecimal solution does not work as the original coursework result is calculated using double

/*
package com.company;

import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        BigDecimal cities[][];
        boolean [] visitedNodes;
        int num_cities;
        int[] visitedNodesOrder;
        int previousNode;
        BigDecimal min_dist = new BigDecimal(0);
        BigDecimal nn_dist;
        BigDecimal total_distance;
        int min_idx = 0;

        cities = readFile();

        num_cities = cities.length;

        //initialise visitedNodes
        visitedNodes = new boolean[num_cities];
        for (int i = 1; i < num_cities; i++){
            visitedNodes[i] = false;
        }
        visitedNodes[0] = true;

        visitedNodesOrder = new int[num_cities];
        visitedNodesOrder[0] = 0;
        total_distance = new BigDecimal(0);
        MathContext mc = new MathContext(100, RoundingMode.HALF_UP);

        for (int i = 0; i < num_cities - 1 ; i++){

            min_dist = BigDecimal.valueOf(Integer.MAX_VALUE);
            min_idx = -1;
            previousNode = visitedNodesOrder[i] ;

            for (int j = 1; j < num_cities; j++){
                if ((!visitedNodes[j]) && (previousNode != j)){
                    nn_dist = calcSQDistance(cities[previousNode][0], cities[j][0], cities[previousNode][1], cities[j][1]);

                    //System.out.println("p1 : " + cities[previousNode][0].toString() + "," + cities[previousNode][1].toString() + "p2: " + cities[j][1].toString() + "," + cities[j][0].toString() + " | nn_dist : " + nn_dist.toString() + " | min_dist: " + min_dist.toString() + " | nn_dist - min_dist : " + nn_dist.subtract(min_dist).toString());
                    if (nn_dist.subtract(min_dist).floatValue() < 0){ // nn_dist < min_dist
                        min_dist = nn_dist;
                        min_idx = j;
                    }
                }
            }
            visitedNodesOrder[i + 1] = min_idx;
            visitedNodes[min_idx] = true;
            total_distance = total_distance.add(min_dist.sqrt(mc));
            if (i % 100 == 0)
                System.out.println("Iteration: " + Integer.toString(i) + " | total_distance: " + total_distance.toString() + " | Previous Node: " + Integer.toString(previousNode + 1) + " | Node: " + Integer.toString(min_idx + 1));
        }

        total_distance = total_distance.add(calcSQDistance(cities[0][0], cities[min_idx][0], cities[0][1], cities[min_idx][1]).sqrt(mc));

        for (int i = 0; i<num_cities; i++)
            visitedNodesOrder[i] += 1;
        System.out.println(total_distance.floatValue());
        System.out.println(Arrays.toString(visitedNodesOrder));

    }

    public static BigDecimal[][] readFile() throws IOException{
        String fileName = "C:\\Users\\osman\\Practice\\AlgoCourse\\Course 4\\nn.txt";
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        int num_points;
        BigDecimal cities[][];
        num_points = Integer.parseInt(br.readLine());
        num_points = 5001;
        cities = new BigDecimal[num_points][2];

        int idx = 0;
        while ((st = br.readLine()) != null && (idx < num_points)) {
            String[] splitted = st.split("\\s+");
            cities[idx][0] = new BigDecimal(splitted[1]);
            cities[idx][1] = new BigDecimal(splitted[2]);
            idx++;
        }
        return cities;

    }

    public static BigDecimal calcSQDistance(BigDecimal x1, BigDecimal x2, BigDecimal y1, BigDecimal y2){
        BigDecimal x_dist = x1.subtract(x2);
        BigDecimal y_dist = y1.subtract(y2);
        BigDecimal sq_dist = x_dist.pow(2).add(y_dist.pow(2));
        return sq_dist;
    }
}
*/