package com.soa.fooddelivery.user.repository;

import com.soa.fooddelivery.user.dto.UserDto;
import com.soa.fooddelivery.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    List<User> findAllById(@Param("id") Integer id);


    @Query("SELECT new com.soa.fooddelivery.user.dto.UserDto(u.id, u.firstName, u.lastName, u.category, u.activeStatus) FROM User u WHERE u.activeStatus = true and u.id=:id")
    UserDto findDtoById(@Param("id") Integer id);


}
