package com.Toucomex.Importation_Toucomex.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "suiviimport")
public class SuiviImp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_s_i;

    @NonNull
    private String num_declaration;

    @NonNull
    private String observation;

    @NonNull
    private String shipment;

    @NonNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate Date_arrive_f_p;

    @NonNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate Date_declaration;

    @NonNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate Date_arrive_stock;


    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name ="Id_facture")
    private Facture fac;

    @OneToOne(mappedBy = "receptionsuivi")
    private Reception ReceptionS;



}
