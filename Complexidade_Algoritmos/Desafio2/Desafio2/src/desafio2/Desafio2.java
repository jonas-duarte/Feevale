/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio2;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author jonas
 */
public class Desafio2 {

    private ArrayList<Integer> loadPrimalNumbers(int max) {
        ArrayList<Integer> primes = new ArrayList();
        primes.add(2);

        boolean isPrime;

        for (int i = 3; i <= max; i += 2) {

            isPrime = true;

            int sqrtI = (int) Math.round(Math.sqrt(i));

            for (int j = 0; j < primes.size() && j < sqrtI; j++) {
                if ((i % primes.get(j)) == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                primes.add(i);
            }
        }

        return primes;
    }

    private ArrayList<Integer> loadGoldbachSets(int min, int max, int qtd) {
        ArrayList<Integer> primes = loadPrimalNumbers(max);

        int arrLen = ((max - min) / 2) + 1;
        int[] goldbathCount = new int[arrLen];

        ArrayList<ArrayList<Integer[]>> aux = new ArrayList(arrLen);
        
        int val;

        for (int p1 = 1; p1 < primes.size(); p1++) {
            for (int p2 = p1; p2 < primes.size(); p2++) {
                val = primes.get(p1) + primes.get(p2);
                if (val >= min && val <= max ) {
                    goldbathCount[(val - min) / 2]++;
                }
            }
        }
        
        int index;
        int[] greaters = new int[qtd];
        
        for(int i = 0; i < goldbathCount.length;i++){
            index = 0;
            for (int e=1; e < greaters.length; e++) {
                if(greaters[e] < greaters[index]) index = e;
            }
            
            if(goldbathCount[i]>goldbathCount[greaters[index]]){
                greaters[index] = i;
            }
        }

        ArrayList<Integer> ret = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            ret.add(greaters[i] * 2 + min);
//            ret.add(goldbathCount[greaters[i]]);
        }
        return ret;
    }

    private void run() {
        long inicio = System.currentTimeMillis();

        System.out.println(loadGoldbachSets(1_000_000, 2_000_000, 3));

        long fim = System.currentTimeMillis();
        System.out.println(new SimpleDateFormat("ss.SSS").format(new Date(fim - inicio)));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Desafio2().run();
    }

}