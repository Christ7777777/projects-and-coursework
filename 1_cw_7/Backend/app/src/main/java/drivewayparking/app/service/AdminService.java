package drivewayparking.app.service;

import drivewayparking.app.dto.AdminRequest;
import drivewayparking.app.entity.Admin;
import drivewayparking.app.enums.Status;
import drivewayparking.app.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository repository;

    public Admin getAdminByEmail(String email){
        return repository.findByEmail(email);
    }

    public Admin getAdminById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Integer updateAdmin(AdminRequest request) {

        Admin existingAdmin = repository.findById(request.getId()).orElse(null);
        if (existingAdmin != null) {

            if (request.getPassword() != null) existingAdmin.setPassword(request.getPassword());
            if (request.getPhoneNumber() != null) existingAdmin.setPhoneNumber(request.getPhoneNumber());
            if (request.getFirstName() != null) existingAdmin.setFirstName(request.getFirstName());
            if (request.getLastName() != null) existingAdmin.setLastName(request.getLastName());

            repository.save(existingAdmin);
            return Status.OK.getValue();
        }
        return Status.Not_Found.getValue();
    }

    public Admin getAdminByLowestNumberDisputesAssigned() {
        return repository.findByLowestNumberDisputesAssigned();
    }
}
