package com.akucheruk.bank_app.controller;

import com.akucheruk.bank_app.DataFactory;
import com.akucheruk.bank_app.domain.dto.in.AddressDto;
import com.akucheruk.bank_app.domain.entity.Address;
import com.akucheruk.bank_app.exception.DataAlreadyExistException;
import com.akucheruk.bank_app.exception.DataNotFoundException;
import com.akucheruk.bank_app.service.AddressService;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static com.akucheruk.bank_app.DataFactory.LoadData.loadFromResource;
import static com.akucheruk.bank_app.domain.entity.AddressState.MINNESOTA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AddressService service;

    private final JsonMapper mapper = new JsonMapper();

    @Nested
    class GetAddresses {
        @Test
        void givenNoArguments_shouldReturnAllAddresses() throws Exception {
            var expectedJson = loadFromResource("assets/address_list.json");
            when(service.getAddresses(null, null, null)).thenReturn(DataFactory.addresses());
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .get("/address")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json(expectedJson));
            verify(service).getAddresses(null, null, null);
        }

        @Test
        void givenAllArguments_shouldReturnFilteredAddresses() throws Exception {
            var expectedJson = loadFromResource("assets/address_list.json");
            when(service.getAddresses(anyString(), anyInt(), anyString())).thenReturn(DataFactory.addresses());
            mockMvc.perform(
                            MockMvcRequestBuilders
                                    .get("/address")
                                    .queryParam("state", MINNESOTA.name())
                                    .queryParam("postCode", "123456")
                                    .queryParam("street", "some_street")
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json(expectedJson));
            verify(service).getAddresses(MINNESOTA.name(), 123456, "some_street");
        }

        @Test
        void givenNotValidPostCode_shouldReturnBadRequest() throws Exception {
            mockMvc.perform(
                            MockMvcRequestBuilders
                                    .get("/address")
                                    .queryParam("postCode", "123")
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
            verify(service, never()).getAddresses(anyString(), anyInt(), anyString());
        }
    }

    @Nested
    class CreateAddress {

        @Test
        void givenValidAddress_shouldCreateNew() throws Exception {
            var newAddress = loadFromResource("assets/address_new.json");
            var expectedAddress = DataFactory.addresses().get(1);
            when(service.createAddress(any(AddressDto.class))).thenReturn(expectedAddress);
            mockMvc.perform(
                            MockMvcRequestBuilders
                                    .post("/address")
                                    .content(newAddress)
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(expectedAddress)));

            verify(service).createAddress(mapper.readValue(newAddress, AddressDto.class));
        }

        @Test
        void givenNotValidPostCode_shouldReturnBadRequest() throws Exception {
            var newAddress = loadFromResource("assets/address_not_valid_postcode.json");
            mockMvc.perform(
                            MockMvcRequestBuilders
                                    .post("/address")
                                    .content(newAddress)
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());

            verify(service, never()).createAddress(mapper.readValue(newAddress, AddressDto.class));
        }

        @Test
        void givenExistAddress_shouldReturnBadRequest() throws Exception {
            var newAddress = loadFromResource("assets/address_new.json");
            when(service.createAddress(any(AddressDto.class)))
                    .thenThrow(new DataAlreadyExistException("Address is exist"));
            mockMvc.perform(
                            MockMvcRequestBuilders
                                    .post("/address")
                                    .content(newAddress)
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());

            verify(service).createAddress(mapper.readValue(newAddress, AddressDto.class));
        }
    }

    @Nested
    class UpdateAddress {
        @Test
        void givenValidData_shouldUpdateAddress() throws Exception {
            var newAddress = loadFromResource("assets/address_new.json");
            var newId = UUID.randomUUID();
            var existAddress = DataFactory.addresses().get(1);

            when(service.updateAddress(any(), any(AddressDto.class))).thenReturn(existAddress);
            mockMvc.perform(
                            MockMvcRequestBuilders
                                    .put("/address/" + newId)
                                    .content(newAddress)
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isAccepted())
                    .andExpect(content().json(newAddress));

            verify(service).updateAddress(newId, mapper.readValue(newAddress, AddressDto.class));
        }

        @Test
        void givenNotExistAddress_shouldReturnNotFound() throws Exception {
            var newAddress = loadFromResource("assets/address_new.json");
            var newId = UUID.randomUUID();

            when(service.updateAddress(any(), any(AddressDto.class)))
                    .thenThrow(new DataNotFoundException("Address is exist", Address.class));
            mockMvc.perform(
                            MockMvcRequestBuilders
                                    .put("/address/" + newId)
                                    .content(newAddress)
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

            verify(service).updateAddress(newId, mapper.readValue(newAddress, AddressDto.class));
        }
    }

    @Nested
    class DeleteAddress {
        @Test
        void givenExistAddress_shouldReturnOk() throws Exception {
            var newId = UUID.randomUUID();
            mockMvc.perform(
                            MockMvcRequestBuilders
                                    .delete("/address/" + newId)
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            verify(service).deleteAddress(newId);
        }

        @Test
        void givenNotExistAddress_shouldReturnNotFound() throws Exception {
            var newId = UUID.randomUUID();
            doThrow(new DataNotFoundException("Address is not exist", Address.class))
                    .when(service).deleteAddress(any());
            mockMvc.perform(
                            MockMvcRequestBuilders
                                    .delete("/address/" + newId)
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
            verify(service).deleteAddress(newId);
        }
    }
}
