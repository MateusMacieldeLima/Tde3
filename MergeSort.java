package TDE3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MergeSort {
    static int trocaCount = 0;
    static int iteracaoCount = 0;

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) leftArray[i] = array[left + i];
        for (int j = 0; j < n2; j++) rightArray[j] = array[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            iteracaoCount++;
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
                trocaCount++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
            iteracaoCount++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
            iteracaoCount++;
        }
    }

    public static void main(String[] args) {
        int seed = 12345; // Seed para replicabilidade
        Random random = new Random(seed);

        int[] sizes = {1000, 10000, 100000, 500000, 1000000};

        try (FileWriter writer = new FileWriter("TDE3/relatorio_merge_sort.csv")) {
            writer.write("Tamanho do Vetor,Tempo de Execução (ms),Número de Trocas,Número de Iterações\n");

            for (int size : sizes) {
                int[] array = new int[size];
                for (int i = 0; i < size; i++) {
                    array[i] = random.nextInt(10000);
                }

                trocaCount = 0;
                iteracaoCount = 0;

                long startTime = System.nanoTime();
                mergeSort(array, 0, array.length - 1);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime) / 1000000; // Tempo em milissegundos

                writer.write(size + "," + duration + "," + trocaCount + "," + iteracaoCount + "\n");

                System.out.println("Tamanho do vetor: " + size);
                System.out.println("Tempo de execução (ms): " + duration);
                System.out.println("Número de trocas: " + trocaCount);
                System.out.println("Número de iterações: " + iteracaoCount);
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
