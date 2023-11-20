package com.udea.drivers.controller;

import com.udea.drivers.exception.InvalidRating;
import com.udea.drivers.exception.ModelNotFoundException;
import com.udea.drivers.model.Driver;
import com.udea.drivers.model.StateDriverEnum;
import com.udea.drivers.service.DriverService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/driver")
@CrossOrigin("*")
@Api(value = "Driver Managment System", description = "Operation to Drivers")
public class DriverController {
    private static final Logger LOGGER = Logger.getLogger(DriverController.class.getName());
    @Autowired
    private DriverService driverService;

    @ApiOperation(value = "Add Driver")
    @PostMapping("/save")
    public long save(
            @ApiParam(value = "Driver Object Store in DB table", required = true)
            @RequestBody Driver driver
    ) throws InvalidRating {
        if(driver.getRating() > 5)
            throw new InvalidRating("Id should be less than o equal 5");
        LOGGER.info("driver.getState().toString()");
        driverService.save(driver);
        return driver.getId();
    }

    @ApiOperation(value = "View a list of available drivers", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successfully retried list"),
            @ApiResponse(code = 401, message = "You are not authorized to view th resource"),
            @ApiResponse(code = 403, message = "Accessing resource tha you are trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Driver not found")
    })
    @GetMapping("/listAll")
    public Iterable<Driver> listAllDrivers(){ return driverService.list();}

    @ApiOperation(value = "Get Driver by ID")
    @GetMapping("/list/{id}")
    public Driver listDriverById(@ApiParam(value = "Driver ID from Driver Object will retried", required = true)
                                     @PathVariable("id") int id){
        Optional<Driver> driver = driverService.listId(id);
        if(driver.isPresent()){
            return driver.get();
        }
        throw new ModelNotFoundException("ID de driver invalido");
    }

    @ApiOperation(value = "Get Top Driver")
    @GetMapping("/topDrivers")
    public ResponseEntity<List<Driver>> viewBestDrivers(){
        List<Driver> list = driverService.viewBestDriver();
        return new ResponseEntity<List<Driver>>(list, HttpStatus.ACCEPTED);
    }

    @PutMapping
    public Driver updateService(@RequestBody Driver driver){
        return driverService.update(driver);
    }

    @PutMapping("/{id}/state")
    public Driver updateDriverStatus(@PathVariable Long id, @RequestBody UpdateDriverStatusRequest request) {
        try {
            String newState = request.getNewState();
            StateDriverEnum stateEnum = StateDriverEnum.valueOf(newState);
            System.out.println("El string coincide con un ítem del enum.");
            return driverService.updateDriverStatus(id, stateEnum);
        } catch (IllegalArgumentException e) {
            System.out.println("error: " + e.toString());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "State " + request.getNewState() + " is not valid."
            );
        }
    }

    @DeleteMapping("/{id}")
    public String deleteDriver(@PathVariable long id){
        return driverService.delete(id);
    }
}

