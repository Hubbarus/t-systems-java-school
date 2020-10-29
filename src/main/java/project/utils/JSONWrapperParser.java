package project.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import project.dto.CartDTO;
import project.dto.ItemDTO;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JSONWrapperParser {

    public String serializeToJson(CartListWrapper wrapper) throws IOException {
        List<CartDTO> list = wrapper.getList();
        List<ItemDTO> items = list.stream()
                .map(CartDTO::getItem)
                .collect(Collectors.toList());
        ObjectMapper mapper = new ObjectMapper();

        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, items);
        return writer.toString();
    }

}
