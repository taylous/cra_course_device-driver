package devices;

public abstract class Memory {

    public static final int ADDRESS_BOUNDARY = 255;

    private byte[] data;

    public Memory(int memoryMaximumSize) {
        this.data = new byte[memoryMaximumSize + 1];
    }

    public Memory() {
        this(ADDRESS_BOUNDARY);
    }

    byte[] getData() {
        return this.data;
    }
}
