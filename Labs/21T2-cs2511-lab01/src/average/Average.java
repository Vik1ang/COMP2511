package average;

public class Average {
    /**
     * Returns the average of an array of numbers
     * 
     * @param the array of integer numbers
     * @return the average of the numbers
     */
    public float computeAverage(int[] nums) {
        float result = 0;
        // Add your code
        for (int num : nums) {
            result += num;
        }
        return result / nums.length;
    }

    public static void main(String[] args) {
        // Add your code
        int[] nums = new int[]{1, 2, 3};
        System.out.println(new Average().computeAverage(nums));
    }
}
