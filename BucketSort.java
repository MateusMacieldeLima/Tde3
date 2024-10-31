package TDE3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BucketSort {
    static int trocaCount = 0;
    static int iteracaoCount = 0;

    public static void bucketSort(int[] array, int bucketSize) {
        if (array.length == 0) return;

        int minValue = array[0];
        int maxValue = array[0];
        for (int value : array) {
            if (value < minValue) minValue = value;
            if (value > maxValue) maxValue = value;
        }

        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        ArrayList<int[]> buckets = new ArrayList<>(bucketCount);

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new int[0]);  
        }

        for (int value : array) {
            int bucketIndex = (value - minValue) / bucketSize;
            buckets.set(bucketIndex, addElement(buckets.get(bucketIndex), value));
            iteracaoCount++;
        }

        int index = 0;
        for (int[] bucket : buckets) {
            insertionSort(bucket);  
            for (int value : bucket) {
                array[index++] = value;
                trocaCount++;
            }
        }
    }

    private static int[] addElement(int[] array, int element) {
        int[] newArray = new int[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = element;
        return newArray;
    }

    private static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
                trocaCount++;
                iteracaoCount++;
            }
            array[j + 1] = key;
            iteracaoCount++;
        }
    }

    public static void main(String[] args) {
        int seed = 12345; 
        Random random = new Random(seed);

        int[] sizes = {1000, 10000, 100000, 500000, 1000000};
        int bucketSize = 1000;

        String arquivoCSV = "TDE3/relatorio_bucket_sort.csv";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoCSV))) {
            writer.write("Tamanho do Vetor,Tempo de Execução (ms),Número de Trocas,Número de Iterações\n");
            for (int size : sizes) {
                int[] array = new int[size];
                for (int i = 0; i < size; i++) {
                    array[i] = random.nextInt(10000); 
                }

                trocaCount = 0;
                iteracaoCount = 0;

                long startTime = System.nanoTime();
                bucketSort(array, bucketSize);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime) / 1000000;

                System.out.println("Tamanho do vetor: " + size);
                System.out.println("Tempo de execução (ms): " + duration);
                System.out.println("Número de trocas: " + trocaCount);
                System.out.println("Número de iterações: " + iteracaoCount);
                System.out.println();


                writer.write(size + "," + duration + "," + trocaCount + "," + iteracaoCount);
                writer.newLine();
            }

            System.out.println("Relatório gerado com sucesso: " + arquivoCSV);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
