package com.akucheruk.bank_app;

import com.akucheruk.bank_app.domain.entity.Address;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.akucheruk.bank_app.domain.entity.AddressState.MINNESOTA;
import static com.akucheruk.bank_app.domain.entity.AddressState.OTHER;

@UtilityClass
public class DataFactory {
    public static JsonMapper mapper = new JsonMapper();

    public static List<Address> addresses() {
        var address1 = new Address();
        address1.setAddressId(UUID.fromString("57f284d3-313a-4529-9d8b-e681e44b457c"));
        address1.setHouseNumber("354");
        address1.setStreet("Cloud Dr");
        address1.setPostCode(553230);
        address1.setState(MINNESOTA);

        var address2 = new Address();
        address2.setAddressId(UUID.fromString("06e74b8d-7b0a-4564-b180-82cde54df3fb"));
        address2.setHouseNumber("8232");
        address2.setStreet("Highway Rd");
        address2.setPostCode(553230);
        address2.setState(OTHER);

        return List.of(address1, address2);
    }

    public static class LoadData {
        public static String loadFromResource(String fileName) throws IOException {
            return new String(LoadData.class.getClassLoader().getResourceAsStream(fileName).readAllBytes());
        }
    }
}
