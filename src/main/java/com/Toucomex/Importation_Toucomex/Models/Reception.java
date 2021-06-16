package com.Toucomex.Importation_Toucomex.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reception")
public class Reception {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_rcp;

    @NonNull
    private String num_rcp;

    @NonNull
    private String libelle_rcp;


    @NonNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate Date_rcp;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name ="Id_s_i")
    private SuiviImp receptionsuivi;



}

