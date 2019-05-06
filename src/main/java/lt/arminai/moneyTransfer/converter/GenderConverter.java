package lt.arminai.moneyTransfer.converter;

import lt.arminai.moneyTransfer.dto.GenderDto;
import lt.arminai.moneyTransfer.model.Gender;

public final class GenderConverter {
    public static Gender fromDto(GenderDto dto) {
        if (dto == null) {
            return null;
        }

        return Gender.valueOf(dto.toString());
    }

    public static GenderDto toDto(Gender gender) {
        if (gender == null) {
            return null;
        }

        return GenderDto.valueOf(gender.toString());
    }
}
