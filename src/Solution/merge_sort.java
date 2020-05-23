package Solution;

public class merge_sort {
    public static void merge_sort(int[] nums,int left,int right){
        if (left < right){
            int mid = (left + right)/2;
            merge_sort(nums,left,mid);
            merge_sort(nums,mid + 1,right);
            merge(nums,left,mid,right);
        }
    }

    public static void merge(int[] arr,int left,int mid,int right){
        int[] temp = new int[right - left + 1];

        int i = left;
        int j = mid + 1;
        int tem = 0;

        while(i <= mid && j <= right){
            if (arr[i] <= arr[j]){
                temp[tem++] = arr[i++];
            }else{
                temp[tem++] = arr[j++];
            }
        }

        while(i <= mid){
            temp[tem++] = arr[i++];
        }
        while(j <= right){
            temp[tem++] = arr[j++];
        }

        for (int k = 0; k < temp.length; k++) {
            arr[k + left] = temp[k];
        }
    }

    public static void main(String[] args) {
        int[] arr = {7,8,9,1,5,4,3};
        merge_sort(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
