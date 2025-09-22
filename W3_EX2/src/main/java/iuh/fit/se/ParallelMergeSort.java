package iuh.fit.se;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ParallelMergeSort {

    // Our RecursiveAction implementation
    static class MergeSortAction extends RecursiveAction {
        private static final int SEQUENTIAL_THRESHOLD = 10_000;
        private final int[] arr;
        private final int[] temp;
        private final int start, end;

        public MergeSortAction(int[] arr, int[] temp, int start, int end) {
            this.arr = arr;
            this.temp = temp;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= SEQUENTIAL_THRESHOLD) {
                Arrays.sort(arr, start, end); // base case
                return;
            }

            int mid = (start + end) / 2;

            MergeSortAction left  = new MergeSortAction(arr, temp, start, mid);
            MergeSortAction right = new MergeSortAction(arr, temp, mid, end);

            invokeAll(left, right);
            merge(start, mid, end);
        }

        private void merge(int start, int mid, int end) {
            System.arraycopy(arr, start, temp, start, end - start);

            int i = start, j = mid, k = start;

            while (i < mid && j < end) {
                if (temp[i] <= temp[j]) {
                    arr[k++] = temp[i++];
                } else {
                    arr[k++] = temp[j++];
                }
            }
            while (i < mid) {
                arr[k++] = temp[i++];
            }
            while (j < end) {
                arr[k++] = temp[j++];
            }
        }
    }

    public static void main(String[] args) {
        // Generate random array
        int[] arr = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1, 1_000_000))
                .limit(20_000_000) // 20 million elements
                .toArray();

        int[] temp = new int[arr.length];

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        long startTime = System.currentTimeMillis();
        forkJoinPool.invoke(new MergeSortAction(arr, temp, 0, arr.length));
        long endTime = System.currentTimeMillis();

        forkJoinPool.shutdown();

        // Verify sorted
        System.out.println("Sorted correctly? " + isSorted(arr));
        System.out.println("Time taken: " + (endTime - startTime) + " ms");

        // Print sample values
        System.out.println("First 100: " + Arrays.toString(Arrays.copyOfRange(arr, 0, 100)));
        System.out.println("Last 100: " + Arrays.toString(Arrays.copyOfRange(arr, arr.length - 100, arr.length)));
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }
}
