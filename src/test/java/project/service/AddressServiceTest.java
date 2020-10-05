package project.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import project.config.DispatcherConfig;
import project.config.SpringConfig;
import project.dao.AddressDao;
import project.dto.AddressDTO;
import project.exception.NotSupportedActionException;
import project.service.utils.TestHelper;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, DispatcherConfig.class})
@WebAppConfiguration
public class AddressServiceTest {

    @Autowired private AddressService addressService;
    @MockBean private AddressDao dao;
    @Autowired private ModelMapper mapper;

    @Before
    public void setUp() throws Exception {
        TestHelper.initAddresses();
        TestHelper.initItems();
        TestHelper.initClients();
        TestHelper.initCarts();
        TestHelper.initOrders();

        TestHelper.setAddress1ID(1);
        when(dao.findById(anyLong())).thenReturn(TestHelper.address1);
    }

    @Test(expected = NotSupportedActionException.class)
    public void getFormFromActionEx() {
        AddressDTO address = mapper.map(TestHelper.address1, AddressDTO.class);
        addressService.getFormFromAction(address, "not supported action");
    }

    @Test
    public void getFormFromAction() {
        AddressDTO address = mapper.map(TestHelper.address1, AddressDTO.class);
        AddressDTO add = addressService.getFormFromAction(address, "add");

        assertEquals(add.getId(), 0);

        AddressDTO edit = addressService.getFormFromAction(address, "edit");

        assertEquals(edit.getId(), address.getId());
    }

}