package project.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import project.dto.CartDTO;
import project.dto.ItemDTO;
import project.exception.AppJsonParseException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JSONWrapperParser {

    public String serializeToJson(CartListWrapper wrapper) throws AppJsonParseException {
        List<CartDTO> list = wrapper.getList();
        List<ItemDTO> items = list.stream()
                .map(CartDTO::getItem)
                .collect(Collectors.toList());
        ObjectMapper mapper = new ObjectMapper();

        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, items);
        } catch (IOException e) {
            throw new AppJsonParseException("Exception while parsing object", e);
        }
        return writer.toString();
    }

}
