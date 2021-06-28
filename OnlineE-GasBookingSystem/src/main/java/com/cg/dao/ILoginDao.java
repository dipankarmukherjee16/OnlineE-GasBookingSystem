package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Login;



/*
 * Created By Titas Sarkar
 */
@Repository
public interface ILoginDao extends JpaRepository<Login, Integer> {

}
