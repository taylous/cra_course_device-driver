package devices;

import java.util.Arrays;

public abstract class Memory {

    public static final int ADDRESS_BOUNDARY = 255;

    private final byte[] data;

    public Memory(int memoryMaximumSize) {
        this.data = new byte[memoryMaximumSize + 1];
        Arrays.fill(this.data, (byte) 0xFF);
    }

    public Memory() {
        this(ADDRESS_BOUNDARY);
    }

    byte[] getData() {
        return this.data;
    }
}
