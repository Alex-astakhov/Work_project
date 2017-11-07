package assertion;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.testng.asserts.SoftAssert;
/**
 * Created by Asta on 11.07.2017.
 */
public class CheckValid {
    public <T> void validate(Object object, Class<T> group) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> violation = validator.validate(object, group);
        SoftAssert soft = new SoftAssert();
        violation.forEach(v -> soft.assertTrue(v.getMessage().isEmpty(),
                v.getLeafBean() + " has error: " + v.getPropertyPath() + " " + v.getMessage()));
        soft.assertAll();
    }

    public void validate(Object object) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> violation = validator.validate(object);
        SoftAssert soft = new SoftAssert();
        violation.forEach(v -> soft.assertTrue(v.getMessage().isEmpty(),
                v.getRootBean() + " has error: " + v.getPropertyPath() + " " + v.getMessage()));
        soft.assertAll();
    }
}
