import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SlotAllocator {
    private TreeMap<Integer, ParkingSlot> availableSlots = new TreeMap<>();
    private Map<Integer, ParkingSlot> allSlots = new HashMap<>();

    public SlotAllocator(int n) {
        for (int i = 1; i <= n; i++) {
            ParkingSlot slot = new ParkingSlot(i);
            availableSlots.put(i, slot);
            allSlots.put(i, slot);
        }
        System.out.println("Created a parking lot with " + n + " slots");
    }

    public void park(Car car) {
        if (availableSlots.size() > 0) {
            Map.Entry<Integer, ParkingSlot> first = availableSlots.pollFirstEntry();
            ParkingSlot slot = first.getValue();
            slot.setParkedCar(car);
            System.out.println("Allocated slot number: " + slot.getId());
        } else {
            System.out.println("Sorry, parking lot is full");
        }
    }

    public void getAllocatedCarStatus() {
        System.out.printf("%-50s%-50s%-50s\n", "Slot No", "Registration", "Color");
        for (int i = 1; i <= allSlots.size(); i++) {
            ParkingSlot slot = allSlots.get(i);
            Car car = slot.getParkedCar();
            if (car != null) {
                System.out.printf("%-50d%-50s%-50s\n", i, car.getRegistrationNumber(), car.getColor());
            }
        }
    }

    public void getFreeSlotsStatus() {
        System.out.println("Slots");
        for (Map.Entry<Integer, ParkingSlot> entry : availableSlots.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    public void leaveSlot(int id) {
        ParkingSlot slot = allSlots.get(id);
        slot.setParkedCar(null);
        availableSlots.put(id, slot);
        System.out.println("Slot number " + id + " is freed");
    }
}
