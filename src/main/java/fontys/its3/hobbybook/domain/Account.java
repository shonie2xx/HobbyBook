package fontys.its3.hobbybook.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private int age;
    @NotNull
    private EGender gender;


        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "acc_hobbies",referencedColumnName = "id")
        private List<Hobby> hobbies = new ArrayList<>();

        @JsonIgnore
        @OneToMany(cascade = CascadeType.ALL,mappedBy = "fromUser")
        private List<Match> fromUsers = new ArrayList<>();

        @JsonIgnore
        @OneToMany(cascade = CascadeType.ALL,mappedBy = "toUser")
        private List<Match> toUsers = new ArrayList<>();

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id"),
                                        inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<Role> roles = new HashSet<>();

    public Account() {

    }

    public Account(String uname, String firstName, String lastName, String email, String password, int age, EGender gender) {
        this.username = uname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public EGender getGender() {
        return gender;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public List<Match> getFromUsers() {
        return fromUsers;
    }

    public void setFromUsers(List<Match> fromUsers) {
        this.fromUsers = fromUsers;
    }

    public List<Match> getToUsers() {
        return toUsers;
    }

    public void setToUsers(List<Match> toUsers) {
        this.toUsers = toUsers;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
