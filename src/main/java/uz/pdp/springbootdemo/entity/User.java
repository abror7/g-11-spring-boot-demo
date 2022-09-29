package uz.pdp.springbootdemo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id // primary key bo'lishi uchun
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sequence yaratib berishi uchun
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;


    @OneToOne(mappedBy = "owner", fetch = FetchType.LAZY)
    @JsonIgnore
    private Passport passport;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Address> addressList;

//    @OneToOne
//    private Address address;



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
