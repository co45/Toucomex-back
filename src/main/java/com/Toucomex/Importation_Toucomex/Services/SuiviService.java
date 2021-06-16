package com.Toucomex.Importation_Toucomex.Services;

import com.Toucomex.Importation_Toucomex.Models.Reception;
import com.Toucomex.Importation_Toucomex.Models.SuiviImp;
import com.Toucomex.Importation_Toucomex.Repositories.SuiviImpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SuiviService {
    @Autowired
    private SuiviImpRepository suimprepos ;

    public SuiviService (SuiviImpRepository suimprepos){this.suimprepos = suimprepos;}
    public Collection<SuiviImp> getALlsuiviImp()
    {return suimprepos.findAll(); }

}
