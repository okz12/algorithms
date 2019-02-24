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
        BigDecimal distances[][];

        cities = readFile();
        distances = calcDistanceMatrix(cities);
        //System.out.println(Arrays.deepToString(distances));
    }

    public List<Integer> calcRoute (BigDecimal [][] distances){
        List<Integer> resultSequence = new ArrayList<>();
        List<StateKey> resultTemp;
        StateKey min;

        // Combinations
        int[] a;
        int bits;
        int size = distances.length;
        Map<StateKey, StateKey> dict = new HashMap<>(size-1 * Math.pow(2, size - 2), (float)1.0);
        for (int i = 1; i < size; i++){
            dict.put(new StateKey(1 << i, i), new DoubleIntPair(distances[0][i].doubleValue(), 0);
        }

        for (int subsetSize = 2; subsetSize < size; subsetSize++){

        }


        return resultSequence;
    }

    public static BigDecimal[][] readFile() throws IOException{
        String fileName = "C:\\Users\\osman\\Practice\\AlgoCourse\\Course 4\\tsp.txt";
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        int num_points;
        BigDecimal cities[][];
        num_points = Integer.parseInt(br.readLine());
        cities = new BigDecimal[num_points][2];

        int idx = 0;
        while ((st = br.readLine()) != null) {
            String[] splitted = st.split("\\s+");
            cities[idx][0] = new BigDecimal(splitted[0]);
            cities[idx][1] = new BigDecimal(splitted[1]);
            idx++;
        }
        return cities;

    }

    public static BigDecimal calcDistance(BigDecimal x1, BigDecimal x2, BigDecimal y1, BigDecimal y2){
        BigDecimal x_dist = x1.subtract(x2);
        BigDecimal y_dist = y1.subtract(y2);
        BigDecimal sq_dist = x_dist.pow(2).add(y_dist.pow(2));
        BigDecimal sq_dist_sqrt = sq_dist.sqrt(new MathContext(200, RoundingMode.UP));
        return sq_dist_sqrt;
    }

    public static BigDecimal[][] calcDistanceMatrix(BigDecimal[][] cities){
        BigDecimal[][] distances;
        int dim = cities.length;
        distances = new BigDecimal[dim][dim];
        for (int x = 0; x < dim; x++){
            for (int y = 0; y < dim; y++){
                distances[x][y] = calcDistance(cities[x][0], cities[y][0], cities[x][1], cities[y][1]); // math.sqrt here or not?
            }
        }
        return distances;
    }
}
