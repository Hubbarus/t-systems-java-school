package project.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.ItemDao;
import project.dto.CartDTO;
import project.dto.ItemDTO;
import project.entity.Item;
import project.exception.NoSuchItemGroupException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService {

    @Autowired private final ItemDao itemDao;
    @Autowired private final ModelMapper mapper;

    @Transactional
    public void save(ItemDTO itemDTO) {
        Item item = mapper.map(itemDTO, Item.class);
        itemDao.save(item);
    }

    @Transactional
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
            throw new NoSuchItemGroupException("Group " + group + " not found");
        }

        return result;
    }

    public Set<String> getGroupNames() {
        Set<String> result = new HashSet<>();
        List<ItemDTO> list = findAll();
        for (ItemDTO item : list) {
            result.add(item.getItemGroup());
        }
        return result;
    }

    public CartDTO getItemById(Long id, String category) {
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
            save(item);
        }
    }
}
