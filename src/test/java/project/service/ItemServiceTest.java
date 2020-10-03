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
import project.service.utils.EntityFactory;

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
        when(dao.findAll()).thenReturn(EntityFactory.getAllItems());
    }

    @Test(expected = NoSuchItemGroupException.class)
    public void findByGroupEx() {
        itemService.findByGroup("no such group");
    }

    @Test
    public void findByGroup() {
        Item item = EntityFactory.getItem1();

        List<ItemDTO> targetList = itemService.findByGroup(item.getItemGroup());

        assertTrue(targetList.contains(mapper.map(item, ItemDTO.class)));
    }

    @Test
    public void getGroupNames() {
        List<Item> allItems = EntityFactory.getAllItems();
        Set<String> target = new HashSet<>();
        for (Item item : allItems) {
            target.add(item.getItemGroup());
        }

        assertEquals(target, itemService.getGroupNames());
    }

    @Test
    public void getItemInCategoryById() {
        Cart expected = new Cart();
        Item expectedItem = EntityFactory.getItem1();
        expected.setItem(expectedItem);

        CartDTO target = itemService.getItemInCategoryById(expectedItem.getId(), expectedItem.getItemGroup());
        Cart targetCart = mapper.map(target, Cart.class);

        assertEquals(expected, targetCart);
    }
}