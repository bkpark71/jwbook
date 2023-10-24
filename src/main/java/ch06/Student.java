package ch06;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class Student {
    private int id;
    private String name;
    private String univ;
    private Date birth;
    private String email;
}
