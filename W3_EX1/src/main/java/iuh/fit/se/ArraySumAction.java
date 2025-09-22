package iuh.fit.se;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicLong;

public class ArraySumAction extends RecursiveAction {
    private static final int THRESHOLD = 5_000_000; // nguong chia
    private final int[] arr;
    private final int start;
    private final int end;
    private final AtomicLong result;

    public ArraySumAction(int[] arr, int start, int end, AtomicLong result) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.result = result;
    }

    @Override
    protected void compute() {
        if ((long)(end - start) <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            result.addAndGet(sum);
        } else {
            int mid = (start + end) / 2;
            ArraySumAction left = new ArraySumAction(arr, start, mid, result);
            ArraySumAction right = new ArraySumAction(arr, mid, end, result);

            invokeAll(left, right);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[1_000_000]; // demo voi 1 trieu phan tu
        for (int i = 0; i < arr.length; i++) arr[i] = i + 1;

        AtomicLong result = new AtomicLong(0);
        ForkJoinPool pool = new ForkJoinPool();

        pool.invoke(new ArraySumAction(arr, 0, arr.length, result));
        System.out.println("Tong = " + result.get());
    }
}
