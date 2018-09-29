package co.net.quiron.application.admin;

import co.net.quiron.domain.admin.AddressType;
import co.net.quiron.persistence.shared.GenericDAO;
import java.util.List;

public class AddressTypesManager {

    GenericDAO<AddressType> addressTypeRepository;

    public List<AddressType> getAll(){
        addressTypeRepository = new GenericDAO(AddressType.class);
        return addressTypeRepository.getAll();
    }
}
