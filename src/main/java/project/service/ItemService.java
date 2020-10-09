package project.service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.ItemDao;
import project.dto.CartDTO;
import project.dto.ItemDTO;
import project.entity.Item;
import project.exception.NoSuchItemGroupException;
import project.utils.CartListWrapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log
public class ItemService {

    @Autowired private final ItemDao itemDao;
    @Autowired private final ModelMapper mapper;

    public void update(ItemDTO itemDTO) {
        Item item = mapper.map(itemDTO, Item.class);
        itemDao.update(item);
    }

    public ItemDTO findById(Long id) {
        Item item = itemDao.findById(id);
        return mapper.map(item, ItemDTO.class);
    }

    public List<ItemDTO> findAll() {
        List<Item> items = itemDao.findAll();
        return items
                .stream()
                .map(it -> mapper.map(it, ItemDTO.class))
                .collect(Collectors.toList());
    }

    public List<ItemDTO> findByGroup(String group) throws NoSuchItemGroupException {
        List<ItemDTO> result = new ArrayList<>();
        List<ItemDTO> all = findAll();

        for (ItemDTO item : all) {
            if (item.getItemGroup().equalsIgnoreCase(group)) {
                result.add(item);
            }
        }

        if (result.size() == 0) {
            log.log(Level.WARNING, String.format("Group %s not found", group));
            throw new NoSuchItemGroupException("Group " + group + " not found");
        }

        return result;
    }

    public List<ItemDTO> getItems(int from, int quantity) {
        List<Item> items = itemDao.getItems(from, quantity);
        return items
                .stream()
                .map(it -> mapper.map(it, ItemDTO.class))
                .collect(Collectors.toList());
    }

    public Set<String> getGroupNames() {
        Set<String> result = new HashSet<>();
        List<ItemDTO> list = findAll();
        for (ItemDTO item : list) {
            result.add(item.getItemGroup());
        }
        return result;
    }

    public CartDTO getItemInCategoryById(Long id, String category) {
        List<ItemDTO> itemByGroup = findByGroup(category);
        CartDTO cart = new CartDTO();
        for (ItemDTO item : itemByGroup) {
            if (item.getId() == id) {
                cart.setItem(item);
                break;
            }
        }
        return cart;
    }

    public void saveOrUpdate(ItemDTO item) {
        try {
            findById(item.getId());
            update(item);
        } catch (Exception e) {
            item.setPathToIMG("/img/" + item.getPathToIMG());
            Item itemToSave = mapper.map(item, Item.class);
            itemDao.save(itemToSave);
        }
    }

    public void renameGroup(String oldName, String newName) {
        List<ItemDTO> all = findAll();
        for (ItemDTO item : all) {
            if (item.getItemGroup().equals(oldName)) {
                item.setItemGroup(newName);
                update(item);
            }
        }
        log.log(Level.INFO, String.format("Category with name %s renamed to %s", oldName, newName));
    }

    public void deleteGroup(String cat) {
        List<ItemDTO> all = findAll();
        for (ItemDTO item : all) {
            if (item.getItemGroup().equalsIgnoreCase(cat)) {
                item.setItemGroup("DEFAULT");
                update(item);
            }
        }
        log.log(Level.INFO, String.format("Category with name %s deleted. All items moved to DEFAULT.", cat));
    }

    public void addToCart(CartListWrapper wrapper, CartDTO cart) {
        List<CartDTO> cartDTOList = wrapper.getList();
        cartDTOList.add(cart);
        wrapper.setList(cartDTOList);
        log.log(Level.INFO, String.format("Item %s added to cart", cart.getItem().getItemName()));
    }

    public void buyInOneClick(CartListWrapper wrapper, Long itemId) {
        List<CartDTO> cartDTOList = wrapper.getList();
        ItemDTO item = findById(itemId);
        CartDTO cart = new CartDTO();
        cart.setItem(item);
        cart.setQuantity(1);
        cartDTOList.add(cart);
        wrapper.setList(cartDTOList);
        log.log(Level.INFO, String.format("Item %s added to cart", cart.getItem().getItemName()));
    }

    public void removeItemFromCart(CartListWrapper wrapper, long itemId) {
        List<CartDTO> items = wrapper.getList();
        for (CartDTO cartDTO : items) {
            if (cartDTO.getItem().getId() == itemId) {
                items.remove(cartDTO);
                log.log(Level.INFO, String.format("Item %s removed from cart", cartDTO.getItem().getItemName()));
                break;
            }
        }
        wrapper.setList(items);
    }
}
