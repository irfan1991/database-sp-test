package com.irfan.training.testing.database.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity @Data
public class Pelanggan {
    
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @NotEmpty
    private String nama;
    
    @NotEmpty @Email
    private String email;
    
    @Past @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate tanggalLahir;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private JenisKelamin jenisKelamin;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private PendidikanTerakhir pendidikanTerakhir;
    
    
    
    
    
}
