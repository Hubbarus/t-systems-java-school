package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.exception.AppJsonParseException;
import project.service.ItemService;
import project.utils.CartListWrapper;
import project.utils.JSONWrapperParser;


@RestController("/")
public class StandController {

    @Autowired private ItemService itemService;
    @Autowired private JSONWrapperParser parser;

    @GetMapping("/stand")
    public String getTop10ForStandAppInJSONString() throws AppJsonParseException {
        CartListWrapper wrapper = new CartListWrapper();
        wrapper.setList(itemService.getTopTenItems());
        return parser.serializeToJson(wrapper);
    }
    
}
