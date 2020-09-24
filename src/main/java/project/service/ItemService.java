package project.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.converter.ItemConverter;
import project.dao.ItemDao;
import project.dto.ItemDTO;
import project.entity.Item;
import project.exception.NoSuchItemGroupException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService {
    @Autowired
    private final ItemDao itemDao;
    @Autowired
    private final ItemConverter itemConverter;

    @Transactional
    public void save(ItemDTO itemDTO) {
        Item item = itemConverter.convertToEntity(itemDTO);
        itemDao.save(item);
    }

    @Transactional
    public void update(ItemDTO itemDTO) {
        Item item = itemConverter.convertToEntity(itemDTO);
        itemDao.update(item);
    }

    public ItemDTO findById(Long id) {
        Item item = itemDao.findById(id);
        return itemConverter.convertToDTO(item);
    }

    @Transactional
    public void delete(ItemDTO itemDTO) {
        Item item = itemConverter.convertToEntity(itemDTO);
        itemDao.delete(item);
    }

    @Transactional
    public void deleteAll() {
        itemDao.deleteAll();
    }

    public List<ItemDTO> findAll() {
        List<Item> item = itemDao.findAll();
        return item
                .stream()
                .map(itemConverter::convertToDTO)
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

    public List<ItemDTO> findByPriceGap(BigDecimal from, BigDecimal to) {
        List<ItemDTO> result = new ArrayList<>();
        List<ItemDTO> all = findAll();

        for (ItemDTO item : all) {
            BigDecimal currentPrice = item.getPrice();
            if (currentPrice.compareTo(from) >= 0 &&  currentPrice.compareTo(to) <= 0) {
                result.add(item);
            }
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
}
