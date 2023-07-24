package com.example.apiroy.Repository;

import com.example.apiroy.Model.Truyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TruyenRepos extends JpaRepository<Truyen, Long> {

}
