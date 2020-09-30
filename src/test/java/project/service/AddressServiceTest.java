package project.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import project.config.DispatcherConfig;
import project.config.HibernateConfig;
import project.config.SpringConfig;
import project.dto.AddressDTO;
import project.exception.NoSuchAddressException;
import project.exception.NotSupportedActionException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, HibernateConfig.class, DispatcherConfig.class})
@WebAppConfiguration
public class AddressServiceTest {

    @Autowired AddressService addressService;

    public static AddressDTO getAddress() {
        AddressDTO address = new AddressDTO();
        address.setId(0);
        address.setCountry("Russia");
        address.setPostcode("123456");
        address.setCity("Spb");
        address.setStreet("Lenina");
        address.setBuilding(5);
        address.setApart(10);

        return address;
    }

    @Test
    public void getFormFromActionWithAddActionString() {
        AddressDTO target = new AddressDTO();
        AddressDTO result = addressService.getFormFromAction(getAddress(), "add");

        assertNull("Not blank address object", result.getCountry());
        assertEquals("Blank object ids is not equals", target.getId(), result.getId());
        assertNotNull("Result object is null", result);
    }

    @Test(expected = NotSupportedActionException.class)
    public void getFormFromActionWithNotSupportedAction() {
        addressService.getFormFromAction(getAddress(), "delete");
    }

    @Test(expected = NoSuchAddressException.class)
    public void getFormFromActionWithEditActionString() {
       addressService.getFormFromAction(getAddress(), "edit");
    }
}