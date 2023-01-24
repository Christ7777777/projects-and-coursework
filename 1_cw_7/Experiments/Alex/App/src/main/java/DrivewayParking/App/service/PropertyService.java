package DrivewayParking.App.service;

import DrivewayParking.App.entity.Property;
import DrivewayParking.App.entity.User;
import DrivewayParking.App.repository.PropertyRepository;
import DrivewayParking.App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository repository;

    public Property saveProperty(Property property){
        return repository.save(property);
    }

    public List<Property> getAllProperties(){
        return repository.findAll();
    }

    public Property getPropertyById(int id){
        return repository.findById(id).orElse(null);
    }

    public void deleteProperty(int id){
        repository.deleteById(id);
    }

    public Property updateProperty(Property property){
        Property existingProperty = repository.findById(property.getId()).orElse(null);

        if (existingProperty != null) {
            existingProperty.setAddress(property.getAddress());
        }

        return repository.save(existingProperty);
    }
}
