package com.phone.directory.repository;

import com.phone.directory.models.Directory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DirectoryRepository extends CrudRepository <Directory, Long> {

    List<Directory> findByFirstNameOrSecondNameOrLastNameOrPhoneOrAdress(String firstName,
                                                                         String secondName, String lastName,
                                                                         String phone, String adress);

}


