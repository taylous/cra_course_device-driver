import static org.junit.jupiter.api.Assertions.*;

import core.DeviceDriver;
import devices.DataCenterSSD;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeviceDriverTest {

    @Mock
    DataCenterSSD dataCenterSSD;

    @Test
    public void 주소에있는_데이터_읽어오기() {
        DeviceDriver driver = new DeviceDriver(this.dataCenterSSD);
        byte data = driver.read(0xFF);
        assertEquals(0, data);
    }
}
