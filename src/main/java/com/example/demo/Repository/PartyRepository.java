package com.example.demo.Repository;

import com.example.demo.Model.PartyModel;
import com.example.demo.Model.ReformaModel;
import org.springframework.data.repository.CrudRepository;

public interface PartyRepository extends CrudRepository<PartyModel,Long> {

}
