package project.utils;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import project.dto.OrderDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class StatByDateHolder {
    private List<OrderDTO> orders = new ArrayList<>();
    private BigDecimal proceeds;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate from;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate to;
}
