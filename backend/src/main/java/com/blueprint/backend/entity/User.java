package com.blueprint.backend.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;


@Entity
@Table(name = "users")
public class User {
        @Id
        @GeneratedValue(strategy =GenerationType.IDENTITY)
        private Long id;
        private String email;
        private String name;
        @JsonIgnore
        private String password;

        public User() {}
        public Long getId(){
                return id;
        }
        public String getEmail(){
                return email;
        }
        public String getName() {
                return name;
        }
          
        public String getPassword() {
                return password;
        }
        public void setEmail(String email){
                this.email = email;
        }
        public void setName(String name) {
                this.name = name;
        }
          
        public void setPassword(String password) {
                this.password = password;
        }
    
}
