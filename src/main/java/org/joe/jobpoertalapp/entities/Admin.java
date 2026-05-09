package org.joe.jobpoertalapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "admin",schema = "dbo")
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User{

}
