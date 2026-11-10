public class Q1ArraySum {
    public static <T extends Number> double sum(T[] values) {
        double total = 0.0;
        for (T value : values) {
            total += value.doubleValue();
        }
        return total;
    }

    public static void main(String[] args) {
        Integer[] integers = {10, 20, 30};
        Float[] floats = {1.5f, 2.5f, 3.0f};
        Double[] doubles = {2.25, 4.5, 6.75};

        System.out.println("Integer sum: " + sum(integers));
        System.out.println("Float sum: " + sum(floats));
        System.out.println("Double sum: " + sum(doubles));
    }
}
