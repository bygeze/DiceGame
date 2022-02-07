package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Picture;

public interface IPicturesRepository extends CrudRepository<Picture, Long>, IPicturesRepositoryCustom {
}
