package com.example.springsocial.service;

import com.example.springsocial.entity.database.DbUser;
import com.example.springsocial.entity.elasticsearch.EsUser;
import com.example.springsocial.model.request.UserRequestModel;
import com.example.springsocial.model.response.ApiResponse;
import com.example.springsocial.repository.database.UserRepository;
import com.example.springsocial.repository.elasticsearch.UserElasticsearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final UserElasticsearchRepository userElasticsearchRepository;

    public ApiResponse getOne(Long id) {
        EsUser esUser = userElasticsearchRepository.findById(String.valueOf(id)).orElseThrow();
        return new ApiResponse(null, esUser, true);
    }

    public ApiResponse getAll() {
        List<EsUser> esUsers = new ArrayList<>();
        userElasticsearchRepository.findAll().forEach(esUser -> esUsers.add(EsUser.builder().id(esUser.getId()).name(esUser.getName()).build()));
        return new ApiResponse(null, esUsers, true);
    }

    public ApiResponse getViaFuzzy(String name){
        List<EsUser> esUsers = userElasticsearchRepository.findByNameFuzzy(name);
        return new ApiResponse(null, esUsers, true);
    }

    public ApiResponse create(UserRequestModel userRequestModel) {
        if (!userRepository.existsByName(userRequestModel.getName())) {
            DbUser user = userRepository.save(
                    DbUser.builder()
                            .name(userRequestModel.getName())
                            .build()
            );
            EsUser esUser = userElasticsearchRepository.save(
                    EsUser.builder()
                            .id(String.valueOf(user.getId()))
                            .name(user.getName())
                            .build()
            );
            System.out.println(esUser);
            return new ApiResponse(null, user, true);
        } else {
            return new ApiResponse("User already exists", null, false);
        }
    }
}
