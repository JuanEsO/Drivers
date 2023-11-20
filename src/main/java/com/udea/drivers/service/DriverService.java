package com.udea.drivers.service;

import com.udea.drivers.dao.IDriverDAO;
import com.udea.drivers.exception.DriverNotFoundException;
import com.udea.drivers.model.Driver;
import com.udea.drivers.model.StateDriverEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private IDriverDAO dao;

    public Driver save(Driver t) {return dao.save(t);}

    public String delete(long id) {dao.deleteById(id); return "Driver Removed";}

    public Iterable<Driver> list() {return dao.findAll();}

    public Optional<Driver> listId(long id){ return dao.findById(id);}

    public Driver update(Driver t){
        Driver existingDriver = dao.findById(t.getId()).orElse(null);
        existingDriver.setNames(t.getNames());
        existingDriver.setSurnames(t.getSurnames());
        existingDriver.setService_number(t.getService_number());
        existingDriver.setEmail(t.getEmail());
        existingDriver.setAge(t.getAge());
        existingDriver.setPhone(t.getPhone());
        existingDriver.setDocument_type(t.getDocument_type());
        existingDriver.setDocument(t.getDocument());
        existingDriver.setAdmissionDate(t.getAdmissionDate());
        existingDriver.setState(t.getState());
        existingDriver.setRating(t.getRating());

        return dao.save(existingDriver);
    }

    public Driver updateDriverStatus(Long id, StateDriverEnum newState){
        Driver existingDriver = dao.findById(id).orElse(null);
        existingDriver.setState(newState);
        return dao.save(existingDriver);
    }

    public List<Driver> viewBestDriver() throws DriverNotFoundException {
        List<Driver> drivers = dao.viewBestDriver();
        if(drivers.size()>0)
            return drivers;
        else throw new DriverNotFoundException("No driver found with rating >=4");
    }
}
