package edu.EAM.admin.Admin.service;

import edu.EAM.admin.Admin.model.Address;
import edu.EAM.admin.Admin.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.EAM.admin.Admin.repository.AdminRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AdminService {
    private final AdminRepository repository;

    @Autowired
    public AdminService(AdminRepository repository) {
        this.repository = repository;
        initSampleData();
    }

    private void initSampleData() {
        Address addr1 = new Address("Main St", "123", "Downtown", "New York", "10001");
        save(new Admin(UUID.randomUUID().toString(), "Alice", "Female", "alice@example.com", "123456789", addr1));

        Address addr2 = new Address("Broadway", "456", "Midtown", "New York", "10002");
        save(new Admin(UUID.randomUUID().toString(), "Bob", "Male", "bob@example.com", "987654321", addr2));
    }

    public Admin save(Admin admin) {
        if (admin.getId() == null) {
            admin.setId(UUID.randomUUID().toString());
        }
        return repository.save(admin);
    }

    public Admin findById(String id) {
        return repository.findById(id);
    }

    public List<Admin> findAll() {
        return repository.findAll();
    }

    public List<Admin> findByName(String name) {
        return repository.findByNameContaining(name);
    }

    public Admin update(Admin admin) {
        return repository.update(admin);
    }

    public Admin patch(String id, Map<String, Object> updates) {
        Admin admin = repository.findById(id);
        if (admin != null) {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "name" -> {
                        String newName = (String) value;
                        if (newName != null && !newName.trim().isEmpty()) {
                            admin.setName(newName);
                        }
                    }
                    case "gender" -> {
                        String newGender = (String) value;
                        if (newGender != null && !newGender.trim().isEmpty()) {
                            admin.setGender(newGender);
                        }
                    }
                    case "email" -> {
                        String newEmail = (String) value;
                        if (newEmail != null && !newEmail.trim().isEmpty()) {
                            admin.setEmail(newEmail);
                        }
                    }
                    case "phoneNumber" -> {
                        String newPhone = (String) value;
                        if (newPhone != null && !newPhone.trim().isEmpty()) {
                            admin.setPhoneNumber(newPhone);
                        }
                    }
                    case "address" -> {
                        if (value instanceof Map<?, ?> addrMap) {
                            Address current = admin.getAddress();
                            String street = (String) addrMap.get("street");
                            if (street != null && !street.trim().isEmpty()) {
                                current.setStreet(street);
                            }
                            String number = (String) addrMap.get("number");
                            if (number != null && !number.trim().isEmpty()) {
                                current.setNumber(number);
                            }
                            String neighborhood = (String) addrMap.get("neighborhood");
                            if (neighborhood != null && !neighborhood.trim().isEmpty()) {
                                current.setNeighborhood(neighborhood);
                            }
                            String city = (String) addrMap.get("city");
                            if (city != null && !city.trim().isEmpty()) {
                                current.setCity(city);
                            }
                            String postalCode = (String) addrMap.get("postalCode");
                            if (postalCode != null && !postalCode.trim().isEmpty()) {
                                current.setPostalCode(postalCode);
                            }
                            admin.setAddress(current);
                        }
                    }
                }
            });
            return repository.update(admin);
        }
        return null;
    }


    public void deleteById(String id) {
        repository.deleteById(id);
    }
}