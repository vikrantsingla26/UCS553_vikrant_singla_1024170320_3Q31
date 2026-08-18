package library.main;

import library.model.LibraryResource;
import library.model.Book;
import library.model.DigitalResource;
import library.model.Printable;
import library.service.FineService;
import library.util.InputValidator;

public class Main {

    public static void main(String[] args) {
        LibraryResource[] resources = new LibraryResource[5];
        int[] overdueDays = {3, 0, 7, 10, 0};

        resources[0] = new Book(1, "horimiya", "hehehe");
        resources[1] = new DigitalResource(2, "JJK", "jojo");
        resources[2] = new Book(3, "love is war", "kaguya");
        resources[3] = new DigitalResource(4, "ghost rider", "marvel");
        resources[4] = new Book(5, "spider-man", "me");

        for (int i = 0; i < resources.length; i++) {
            LibraryResource resource = resources[i];

            if (!InputValidator.validateResourceId(resource.getResourceId())
                    || !InputValidator.validateFineDays(overdueDays[i])) {
                System.out.println("Skipped invalid resource #" + resource.getResourceId());
                continue;
            }

            if (resource instanceof Printable) {
                ((Printable) resource).printDetails();
            }
        }

        double totalFine = FineService.calculateTotalFine(resources, overdueDays);
        System.out.println("Total fine for overdue resources: Rs. " + totalFine);

        LibraryResource.displayTotalResources();
    }
}
