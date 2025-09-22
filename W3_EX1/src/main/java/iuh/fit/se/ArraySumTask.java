package iuh.fit.se;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ArraySumTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 5_000_000; // nguong chia
    private final int[] arr;
    private final int start;
    private final int end;

    public ArraySumTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if ((long)(end - start) <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            ArraySumTask left = new ArraySumTask(arr, start, mid);
            ArraySumTask right = new ArraySumTask(arr, mid, end);

            left.fork();
            long rightResult = right.compute();
            long leftResult = left.join();

            return leftResult + rightResult;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[1_000_000]; // demo voi 1 trieu phan tu
        for (int i = 0; i < arr.length; i++) arr[i] = i + 1;

        ForkJoinPool pool = new ForkJoinPool();
        long sum = pool.invoke(new ArraySumTask(arr, 0, arr.length));
        System.out.println("Tong = " + sum);
    }
}
