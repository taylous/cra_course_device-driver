package core;

import devices.FlashMemoryDevice;


public class DeviceDriver {

    private final FlashMemoryDevice flashMemoryDevice;

    public DeviceDriver(FlashMemoryDevice hardware) {
        this.flashMemoryDevice = hardware;
    }

    public byte read(long address) {
        return this.flashMemoryDevice.read(address);
    }

    public void write(long address, byte data) {
        // TODO: implement this method
    }
}
