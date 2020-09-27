package project.utils;

import lombok.Data;
import project.dto.OrderDTO;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class StatByDateHolder {
    private List<OrderDTO> orders = new ArrayList<>();
    private BigDecimal proceeds;
    private Date from;
    private Date to;
}
