package project.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import project.dao.ItemDao;
import project.dto.CartDTO;
import project.dto.ItemDTO;
import project.entity.Cart;
import project.entity.Item;
import project.exception.NoSuchItemGroupException;
import project.service.utils.TestHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @InjectMocks private ItemService itemService;
    @Mock private ItemDao dao;
    @Spy private ModelMapper mapper;

    @Before
    public void setUp() throws Exception {
        when(dao.findAll()).thenReturn(TestHelper.getAllItems());
    }

    @Test(expected = NoSuchItemGroupException.class)
    public void findByGroupEx() {
        itemService.findByGroup("no such group");
    }

    @Test
    public void findByGroup() {
        Item item = TestHelper.getItem1();
        ItemDTO target = mapper.map(item, ItemDTO.class);

        List<ItemDTO> targetList = itemService.findByGroup(item.getItemGroup());

        assertTrue(targetList.contains(target));
    }

    @Test
    public void getGroupNames() {
        List<Item> allItems = TestHelper.getAllItems();
        Set<String> expected = new HashSet<>();
        for (Item item : allItems) {
            expected.add(item.getItemGroup());
        }

        Set<String> actual = itemService.getGroupNames();
        assertEquals(expected, actual);
    }

    @Test
    public void getItemInCategoryById() {
        Cart expected = new Cart();
        Item expectedItem = TestHelper.getItem1();
        expected.setItem(expectedItem);

        CartDTO target = itemService.getItemInCategoryById(expectedItem.getId(), expectedItem.getItemGroup());
        Cart targetCart = mapper.map(target, Cart.class);

        assertEquals(expected, targetCart);
    }
}