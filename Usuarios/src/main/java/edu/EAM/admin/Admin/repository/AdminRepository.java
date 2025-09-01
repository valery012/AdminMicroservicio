package edu.EAM.admin.Admin.repository;


import edu.EAM.admin.Admin.model.Admin;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AdminRepository {

    //base de datos iamginaria
    private final Map<String, Admin> database = new HashMap<>();

    // guardar
    public Admin save(Admin user) {
        database.put(user.getId(), user);
        return user;
    }

    // buscar(por id)
    public Admin findById(String id) {
        return database.get(id);
    }

    // darme todos
    public List<Admin> findAll() {
        return new ArrayList<>(database.values());
    }

    // buscar por nombre me da toods los que  tengan ese nombre
    public List<Admin> findByNameContaining(String name) {
        return database.values().stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // eliminar
    public void deleteById(String id) {
        database.remove(id);
    }

    // actualizar
    public Admin update(Admin user) {
        if (database.containsKey(user.getId())) {
            database.put(user.getId(), user);
            return user;
        }
        return null;
    }
}
