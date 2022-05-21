package application.infrastructure.repository.convert;

import application.model.constant.HistoryType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author violet.
 */
@Converter(autoApply = true)
public class ConvertHistoryType implements AttributeConverter<HistoryType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(HistoryType attribute) {
        return switch (attribute) {
            case 安全事件 -> 1;
            default -> 0;
        };
    }

    @Override
    public HistoryType convertToEntityAttribute(Integer dbData) {
        return switch (dbData) {
            case 0 -> HistoryType.安全管理;
            default -> HistoryType.安全事件;
        };
    }
}
