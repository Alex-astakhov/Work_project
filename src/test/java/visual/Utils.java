package visual;

import ashotLib.MyAshot;
import core.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;


/**
 * Created by Alex Astakhov on 17.12.2016.
 */
@Features("Ashot utils")
public class Utils {
    private MyAshot ashot = new MyAshot(Constants.SCREENSHOT_PATH);

    @Test
    public void clearExpected(){
        boolean success = ashot.clearAllExpectedImages();
        Assert.assertTrue(success);
    }

    @Test
    public void clearActual(){
        boolean success = ashot.clearAllActualImages();
        Assert.assertTrue(success);
    }

}
