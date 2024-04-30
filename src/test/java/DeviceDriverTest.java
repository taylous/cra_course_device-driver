import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import core.DeviceDriver;
import devices.DataCenterSSD;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeviceDriverTest {

    @Mock
    DataCenterSSD DATA_CENTER_SSD;

    @Spy
    DataCenterSSD DATA_CENTER_SSD_SPY;

    @Test
    public void 주소에있는_데이터_읽어오기() {
        DeviceDriver driver = new DeviceDriver(this.DATA_CENTER_SSD);
        byte data = driver.read(0xFF);
        assertEquals(0, data);
    }

    @Test
    void 주소에_데이터_쓰기() {
        long address = 0xFF;
        byte data = 7;

        DeviceDriver driver = new DeviceDriver(this.DATA_CENTER_SSD_SPY);
        when(driver.read(address)).thenReturn(data);

        driver.write(address, data);

        byte expected = driver.read(0xFF);

        assertThat(7).isEqualTo(expected);
    }
}
