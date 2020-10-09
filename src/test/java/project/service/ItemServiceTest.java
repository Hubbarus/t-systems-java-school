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
import project.dao.ItemDao;
import project.dto.CartDTO;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.entity.Item;
import project.exception.NoSuchItemGroupException;
import project.service.utils.TestHelper;
import project.utils.CartListWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, DispatcherConfig.class})
@WebAppConfiguration
public class ItemServiceTest {

    @Autowired ItemService itemService;
    @MockBean ItemDao dao;
    @Autowired ModelMapper mapper;
    
    private ItemDTO defaultItem = new ItemDTO();

    @Before
    public void setUp() {
        defaultItem = TestHelper.getItem(1, "TestName", "TestGroup");
        when(dao.findAll()).thenReturn(TestHelper.getItems());
        doThrow(NoSuchItemGroupException.class).when(dao).update(any());
        when(dao.findById(any())).thenReturn(mapper.map(defaultItem, Item.class));
    }

    @Test
    public void findByGroup() {
        ItemDTO anotherItem = TestHelper.getItem(2, "TestName1", "AnotherGroup");

        List<ItemDTO> defaultTarget = itemService.findByGroup(defaultItem.getItemGroup());
        
        assertTrue(defaultTarget.contains(defaultItem));
        assertEquals(1, defaultTarget.size());

        List<ItemDTO> anotherTarget = itemService.findByGroup(anotherItem.getItemGroup());

        assertTrue(anotherTarget.contains(anotherItem));
        assertEquals(1, anotherTarget.size());
    }

    @Test(expected = NoSuchItemGroupException.class)
    public void findByGroupWithException() {
        itemService.findByGroup("No such group in list");
    }

    @Test
    public void getGroupNames() {
        ItemDTO anotherItem = TestHelper.getItem(2, "TestName1", "AnotherGroup");

        Set<String> groupNames = itemService.getGroupNames();

        assertTrue(groupNames.contains(defaultItem.getItemGroup()));
        assertTrue(groupNames.contains(anotherItem.getItemGroup()));
        assertEquals(2, groupNames.size());
    }

    @Test
    public void getItemInCategoryById() {
        ItemDTO anotherItem = TestHelper.getItem(2, "TestName1", "TestGroup");
        List<ItemDTO> groups = itemService.findByGroup("TestGroup");

        assertEquals(2, groups.size());

        CartDTO target = itemService.getItemInCategoryById(2L, "TestGroup");

        assertEquals(anotherItem, target.getItem());
    }

    @Test(expected = NoSuchItemGroupException.class)
    public void renameGroup() {
        itemService.renameGroup("TestGroup", "NewGroup");
    }

    @Test(expected = NoSuchItemGroupException.class)
    public void deleteGroup() {
        itemService.deleteGroup("TestGroup");
    }

    @Test
    public void addToCart() {
        CartDTO cart = TestHelper.getCart(1, defaultItem, 2, new OrderDTO());

        CartListWrapper wrapper = new CartListWrapper();

        List<CartDTO> carts = new ArrayList<>();
        carts.add(cart);

        wrapper.setList(carts);

        ItemDTO anotherItem = TestHelper.getItem(2, "TestName1", "TestGroup");
        CartDTO anotherCart = TestHelper.getCart(2, anotherItem, 1, new OrderDTO());

        itemService.addToCart(wrapper, anotherCart);

        assertEquals(2, wrapper.getList().size());
        assertTrue(wrapper.getList().contains(anotherCart));
    }

    @Test
    public void buyInOneClick() {
        CartListWrapper wrapper = new CartListWrapper();
        wrapper.setList(new ArrayList<>());

        itemService.buyInOneClick(wrapper, defaultItem.getId());

        List<CartDTO> list = wrapper.getList();

        assertEquals(defaultItem, list.get(0).getItem());
        assertEquals(1, list.get(0).getQuantity());
    }

    @Test
    public void removeItemFromCart() {
        CartListWrapper wrapper = new CartListWrapper();
        ArrayList<CartDTO> carts = new ArrayList<>();
        CartDTO cart = TestHelper.getCart(1, defaultItem, 2, new OrderDTO());
        carts.add(cart);

        wrapper.setList(carts);

        itemService.removeItemFromCart(wrapper, defaultItem.getId());

        assertEquals(0, wrapper.getList().size());
    }
}