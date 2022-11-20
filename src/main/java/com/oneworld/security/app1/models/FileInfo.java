package com.oneworld.security.app1.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Files")
@NoArgsConstructor
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotBlank
    @Size(max = 200)
    private String name;
    @NotBlank
    @Size(max = 500)
    private String url;

    public FileInfo(String name, String url) {
        this.name = name;
        this.url = url;
    }

    // get Employee
    @OneToOne(cascade = CascadeType.ALL,
              fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDateTime localDateTime;

}
