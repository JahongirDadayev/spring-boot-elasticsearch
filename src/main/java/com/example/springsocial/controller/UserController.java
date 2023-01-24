package com.example.springsocial.controller;

import com.example.springsocial.model.request.UserRequestModel;
import com.example.springsocial.model.response.ApiResponse;
import com.example.springsocial.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(path = "/get-one/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Long id){
        ApiResponse apiResponse = userService.getOne(id);
        return (apiResponse.getSuccess())?ResponseEntity.status(HttpStatus.OK).body(apiResponse):ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<ApiResponse> getAll(){
        ApiResponse apiResponse = userService.getAll();
        return (apiResponse.getSuccess())?ResponseEntity.status(HttpStatus.OK).body(apiResponse):ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @GetMapping(path = "/get-via-fuzzy")
    public ResponseEntity<ApiResponse> getViaFuzzy(@RequestParam String name){
        ApiResponse apiResponse = userService.getViaFuzzy(name);
        return (apiResponse.getSuccess())?ResponseEntity.status(HttpStatus.OK).body(apiResponse):ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ApiResponse> create(@RequestBody UserRequestModel userRequestModel){
        ApiResponse apiResponse = userService.create(userRequestModel);
        return (apiResponse.getSuccess())?ResponseEntity.status(HttpStatus.CREATED).body(apiResponse):ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
