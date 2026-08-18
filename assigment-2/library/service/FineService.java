package library.service;

import library.model.LibraryResource;

public class FineService {

    public static double calculateTotalFine(LibraryResource[] resources, int[] overdueDays) {
        double totalFine = 0.0;
        for (int i = 0; i < resources.length; i++) {
            if (overdueDays[i] > 0) {
                totalFine += resources[i].calculateFine(overdueDays[i]);
            }
        }
        return totalFine;
    }
}
