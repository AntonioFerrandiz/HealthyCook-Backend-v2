package com.afb.HealthyCook.controller;

import com.afb.HealthyCook.domain.model.User;
import com.afb.HealthyCook.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserRest {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "createUser", method = RequestMethod.POST, produces =
            {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> createUser(@RequestBody User response){
        User user = this.userService.save(response);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
