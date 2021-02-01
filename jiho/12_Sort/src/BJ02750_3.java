import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <h4>수 정렬하기</h4> Quick Sort 활용
 */
public class BJ02750_3 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    public static int partition(int arr[], int left, int right) {

        int pivot = arr[(left + right) / 2];
    
        while (left < right) {
            while ((arr[left] < pivot) && (left < right))
                left++;
            while ((arr[right] > pivot) && (left < right))
                right--;
    
            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
    
        return left;
    }

    public static void quickSort(int arr[], int left, int right) {

        if (left < right) {
            int npivot = partition(arr, left, right);
    
            quickSort(arr, left, npivot - 1);
            quickSort(arr, npivot + 1, right);
        }
    
    }

    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
        
        quickSort(arr, 0, arr.length - 1);
    
        for (int i : arr) sb.append(i).append('\n');

        System.out.println(sb);

    }
    
}
