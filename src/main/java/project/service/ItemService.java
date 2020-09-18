package project.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.converter.ItemConverter;
import project.dao.ItemDao;
import project.dto.ItemDTO;
import project.entity.Item;

import java.util.List;
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
}
