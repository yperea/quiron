package co.net.quiron.application.admin;

import co.net.quiron.domain.admin.AddressType;
import co.net.quiron.persistence.shared.*;
import java.util.List;

/**
 * Holds the business logic to handle Address Types operations.
 */
public class AddressTypesManager {

    //private GenericDAO<AddressType> addressTypeRepository;
    private IAppRepository<AddressType> addressTypeRepository;

    /**
     * Instantiates a new Address types manager.
     */
    public AddressTypesManager() {
        addressTypeRepository = new AppRepository<>(AddressType.class);
    }

    /**
     * Gets the list of all of the Address Types.
     *
     * @return the list of address types
     */
    public List<AddressType> getAll(){
        return addressTypeRepository.getAll();
    }

    /**
     * Gets a specific address type by its id.
     *
     * @param id the id
     * @return the address type
     */
    public AddressType get(int id) {
        //return addressTypeRepository.getById(id);
        return addressTypeRepository.get(id);
    }

    /**
     * Creates a new Address Type.
     *
     * @param addressType the address type to be created
     * @return the int
     */
    public int create (AddressType addressType) {
        //int i = addressTypeRepository.insert(addressType);
        int i = addressTypeRepository.create(addressType);
        return i;
    }

    /**
     * Updates a specific Address Type.
     *
     * @param addressType the address type to be updated
     */
    public void update (AddressType addressType) {
        //addressTypeRepository.saveOrUpdate(addressType);
        addressTypeRepository.update(addressType);
    }

    /**
     * Deletes a specific Address Type.
     *
     * @param addressType the address type to be deleted
     */
    public void delete (AddressType addressType) {
        //addressTypeRepository = new GenericDAO<>(AddressType.class);
        addressTypeRepository.delete(addressType);
    }


}
