package co.net.quiron.application.admin;

import co.net.quiron.domain.admin.AddressType;
import co.net.quiron.persistence.shared.GenericDAO;
import java.util.List;

public class AddressTypesManager {

    GenericDAO<AddressType> addressTypeRepository;

    public List<AddressType> getAll(){
        addressTypeRepository = new GenericDAO<>(AddressType.class);
        return addressTypeRepository.getAll();
    }

    public AddressType getById(int id) {
        addressTypeRepository = new GenericDAO<>(AddressType.class);
        return addressTypeRepository.getById(id);
    }

    public int insert (AddressType addressType) {
        addressTypeRepository = new GenericDAO<>(AddressType.class);
        int i = addressTypeRepository.insert(addressType);

        return i;
    }

    public void update (AddressType addressType) {
        addressTypeRepository = new GenericDAO<>(AddressType.class);
        addressTypeRepository.saveOrUpdate(addressType);
    }

    public void delete (AddressType addressType) {
        addressTypeRepository = new GenericDAO<>(AddressType.class);
        addressTypeRepository.delete(addressType);
    }


}
