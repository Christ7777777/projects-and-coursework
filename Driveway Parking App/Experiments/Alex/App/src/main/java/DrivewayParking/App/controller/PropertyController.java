package DrivewayParking.App.controller;

import DrivewayParking.App.entity.Property;
import DrivewayParking.App.entity.User;
import DrivewayParking.App.service.PropertyService;
import DrivewayParking.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService service;

    @PostMapping("/addProperty")
    public Property addProprty(@RequestBody Property property){
        return service.saveProperty(property);
    }

    @GetMapping("/propertyById/{id}")
    public Property getPropertyById(@PathVariable int id){
        return service.getPropertyById(id);
    }

    @GetMapping("/getAllProperties")
    public List<Property> getAllProperties(){
        return service.getAllProperties();
    }

    @DeleteMapping("/deleteProperty/{id}")
    public void deleteProperty(@PathVariable int id){
        service.deleteProperty(id);
    }

    @PutMapping("/updateProperty")
    public Property updateProperty(@RequestBody Property property){
        return service.updateProperty(property);
    }
}
