import core.DeviceDriver;
import devices.DataCenterSSD;
import exceptions.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeviceDriverTest {

    public static final int READ_TEST_ITERATION = 5;

    public static final long ADDRESS = 0x77;

    @Mock
    DeviceDriver DEVICE_DRIVER;

    @Spy
    DataCenterSSD DATA_CENTER_SSD_SPY;

    @Test
    public void 주소에있는_데이터_읽어오기() {
        DeviceDriver driver = DEVICE_DRIVER;
        when(driver.read(ADDRESS)).thenReturn((byte) 0);

        byte expected = 0;
        byte readData = 0, prevReadData = 0;

        int iteration = READ_TEST_ITERATION;

        try {
            while (iteration-- > 0) {
                Thread.sleep(200);
                readData = driver.read(ADDRESS);

                if (prevReadData != readData) {
                    expected = -1;
                    break;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        verify(driver, times(5)).read(ADDRESS);
        assertThat(readData).isEqualTo(expected);
    }

    @Test
    void 주소에_데이터_쓰기() {
        DeviceDriver driver = new DeviceDriver(this.DATA_CENTER_SSD_SPY);
        byte actual = 7;

        when(driver.read(ADDRESS)).thenReturn(actual);

        driver.write(ADDRESS, actual);

        byte expected = driver.read(ADDRESS);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 쓰여진_주소에_데이터_쓰기() {

        try {
            DeviceDriver driver = new DeviceDriver(new DataCenterSSD());
            byte actual = 7;

            driver.write(ADDRESS, actual);
            driver.write(ADDRESS, actual);
        } catch (CustomException e) {
            assertThat(e.getMessage()).isEqualTo("이미 값이 적혀 있습니다.");
        }
    }
}
