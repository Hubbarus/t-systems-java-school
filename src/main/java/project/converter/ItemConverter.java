package project.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dto.ItemDTO;
import project.entity.Item;

@Service
public class ItemConverter {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CartConverter cartConverter;

    public Item convertToEntity(ItemDTO product) {
        Item entity = mapper.map(product, Item.class);
        return entity;
    }

    public ItemDTO convertToDTO(Item product) {
        ItemDTO dto = mapper.map(product, ItemDTO.class);
        return dto;
    }
}
