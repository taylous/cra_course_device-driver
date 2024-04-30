package devices;

public class DataCenterSSD extends Memory implements FlashMemoryDevice {

    public DataCenterSSD() {
        super();
    }

    @Override
    public byte read(long address) {
        System.out.println(address);
        try {
            return this.getData()[(int) address];
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("유효하지 않은 접근입니다.");
        }
    }

    @Override
    public void write(long address, byte data) {

    }
}
