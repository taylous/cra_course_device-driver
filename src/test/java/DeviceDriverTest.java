import core.DeviceDriver;
import devices.DataCenterSSD;
import exceptions.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeviceDriverTest {

    public static final int READ_TEST_ITERATION = 5;

    @Mock
    DeviceDriver DEVICE_DRIVER;

    @Spy
    DataCenterSSD DATA_CENTER_SSD_SPY;

    @Test
    public void 주소에있는_데이터_읽어오기() {
        DeviceDriver driver = DEVICE_DRIVER;
        long address = 0xFF;

        byte expected = 0;
        byte readData = 0, prevReadData = driver.read(address);

        int iteration = READ_TEST_ITERATION - 1;

        try {
            while (iteration-- > 0) {
                Thread.sleep(200);
                readData = driver.read(address);

                if (prevReadData != readData) {
                    expected = -1;
                    break;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        verify(driver, times(5)).read(address);
        assertThat(readData).isEqualTo(expected);
    }

    @Test
    void 주소에_데이터_쓰기() {
        long address = 0xFF;
        byte actual = 7;

        DeviceDriver driver = new DeviceDriver(this.DATA_CENTER_SSD_SPY);
        when(driver.read(address)).thenReturn(actual);

        driver.write(address, actual);

        byte expected = driver.read(0xFF);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 쓰여진_주소에_데이터_쓰기() {
        DeviceDriver driver = new DeviceDriver(new DataCenterSSD());
        long address = 0xFF;
        byte actual = 7;

        assertThrows(CustomException.class, () -> {
            driver.write(address, actual);
            driver.write(address, actual);
        });
    }
}
