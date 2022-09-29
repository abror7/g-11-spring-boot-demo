package uz.pdp.springbootdemo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.springbootdemo.dto.UserDto;
import uz.pdp.springbootdemo.entity.Brand;
import uz.pdp.springbootdemo.entity.Passport;
import uz.pdp.springbootdemo.entity.User;
import uz.pdp.springbootdemo.repository.BrandRepo;
import uz.pdp.springbootdemo.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final BrandRepo brandRepo;

    public List<User> getAllUsersFromDb(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<User> usersPage;

        usersPage = userRepo.findAll(pageable);
        int totalPages = usersPage.getTotalPages();
        long totalElements = usersPage.getTotalElements();
        List<User> userList = usersPage.getContent();
        return userList;
    }

    public User getUserById(Integer id){


        Optional<User> optionalUser = userRepo.findById(id);
        // TODO: 29/09/22
        User user = optionalUser.get();
        //.....
        //.....
        //.....
        //.....
        Passport passport = user.getPassport();


        return user;


    }


    public void saveUser(UserDto userDto) {

        User user = new ObjectMapper().convertValue(userDto, User.class);
        userRepo.save(user);
    }

    public void deleteUserById(int id) {
        userRepo.deleteById(id);
    }


}
