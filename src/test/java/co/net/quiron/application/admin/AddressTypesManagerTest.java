package co.net.quiron.application.admin;

import co.net.quiron.domain.admin.AddressType;
import co.net.quiron.test.util.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressTypesManagerTest {

    AddressTypesManager addressTypesManager;

    @BeforeEach
    void setUp() {
        addressTypesManager = new AddressTypesManager();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    @Test
    void testGetAllAddressTypes() {
        List<AddressType> addressTypeList = addressTypesManager.getAll();
        assertEquals(5, addressTypeList.size());
    }
}