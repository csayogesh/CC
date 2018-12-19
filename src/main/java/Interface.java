import java.util.Scanner;

public class Interface {
    private static SlotAllocator currentSlotAllocator = null;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            handleRequest(line);
        }
    }

    private static void handleRequest(String line) {
        if (line == null || line.trim().length() == 0) {
            System.out.println("Please enter valid input");
            return;
        }
        String[] command = line.replaceAll("\\s+", " ").trim().toLowerCase().split(" ");
        switch (command[0]) {
            case "create_parking_lot":
                if (command.length != 2) {
                    System.out.println("Invalid input");
                    return;
                }
                int n;
                try {
                    n = Integer.parseInt(command[1]);
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    return;
                }
                currentSlotAllocator = new SlotAllocator(n);
                break;
            case "park":
                if (!validateAllocator())
                    return;
                if (command.length != 3) {
                    System.out.println("Invalid input");
                    return;
                }
                Car car = new Car(command[1], command[2]);
                currentSlotAllocator.park(car);
                break;
            case "leave":
                if (!validateAllocator())
                    return;
                if (command.length != 2) {
                    System.out.println("Invalid input");
                    return;
                }
                int id;
                try {
                    id = Integer.parseInt(command[1]);
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    return;
                }
                currentSlotAllocator.leaveSlot(id);
                break;
            case "status":
                if (!validateAllocator())
                    return;
                if (command.length != 2) {
                    System.out.println("Invalid input");
                    return;
                }
                switch (command[1]) {
                    case "allocated":
                        currentSlotAllocator.getAllocatedCarStatus();
                        break;
                    case "free":
                        currentSlotAllocator.getFreeSlotsStatus();
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
                break;
            default:
                System.out.println("Please enter valid input");
                break;
        }
    }

    private static boolean validateAllocator() {
        if (currentSlotAllocator == null) {
            System.out.println("Please create parking lot first");
            return false;
        }
        return true;
    }
}
