package pl.woelke.item.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private String saleIndex;
    private String name;
    private String category;
    private String description;
    private String unit;
    private BigDecimal weight;
    private BigDecimal quantity;
    private BigDecimal netPrice;
    private BigDecimal grossPrice;
    private BigDecimal vat;
    private String currency;
    private BigDecimal availableStock;
    private BigDecimal minStockLevel;
    private BigDecimal maxStockLevel;
}
