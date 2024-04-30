package devices;


public interface FlashMemoryDevice {

    byte read(long address);

    void write(long address, byte data);

}
