package com.hp.hpreservasapi.service;

import com.hp.hpreservasapi.exception.DuplicateException;
import com.hp.hpreservasapi.exception.NotFoundException;
import com.hp.hpreservasapi.model.Reserva;
import com.hp.hpreservasapi.model.Usuario;
import com.hp.hpreservasapi.repository.IReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {
    @Autowired
    IReservaRepository reservaRepository;
    public Reserva add(Reserva reserva) throws Exception{
        //todo pensar como adicionar a lista de quartos na reserva
        var u = reservaRepository.findAll().stream()
                .filter(rsv-> rsv.getResponsavel().equals(reserva.getResponsavel())
                        && rsv.getInicio().equals(reserva.getInicio())
                        && rsv.getFim().equals(reserva.getFim())).toList();

        if (u.isEmpty()) {
            reservaRepository.save(reserva);
        }else{
            System.out.println("Elemento ja cadastrado " + reserva.getResponsavel());
            throw new DuplicateException();
        }
        return reserva;
    }
    public List<Reserva> todos(){
        return reservaRepository.findAll();
    }
    public Reserva delete(Long id) throws NotFoundException {
        Reserva reserva = this.get(id);
        if(reserva!=null){
        reservaRepository.deleteById(id);
        }
        return reserva;
    }
    public Reserva edit(Reserva reserva,Long id) throws NotFoundException {
        //todo pensar no caso da mudanca de quantidade de quartos da reserva
        return reservaRepository.findById(id).map(reservaBd->{
            reservaBd.setResponsavel(reserva.getResponsavel());
            reservaBd.setInicio(reserva.getInicio());
            reservaBd.setFim(reserva.getFim());
            reservaBd.setValorTotal(reserva.getValorTotal());
            return reservaRepository.save(reservaBd);
        }).orElseThrow(NotFoundException::new);
    }

    public Reserva get(Long id) throws NotFoundException {
        return reservaRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
