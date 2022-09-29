package uz.pdp.springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.springbootdemo.entity.Continent;


@RepositoryRestResource(path = "qitalar" )
public interface ContinentRepo extends JpaRepository<Continent, Integer> {



}
