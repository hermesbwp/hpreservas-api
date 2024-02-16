package com.hp.hpreservasapi.service;

import com.hp.hpreservasapi.exception.DuplicateException;
import com.hp.hpreservasapi.exception.NotFoundException;
import com.hp.hpreservasapi.model.Quarto;
import com.hp.hpreservasapi.repository.IQuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuartoService {
    @Autowired
    IQuartoRepository quartoRepository;
    @Autowired
    HotelService hotelService;
    public Quarto add(Quarto quarto) throws Exception{
        var hotel = hotelService.get(quarto.getHotel().getId());

        if(hotel!=null){
            var u = quartoRepository.findAll().stream()
                    .filter(qtr-> qtr.getNome().equalsIgnoreCase(quarto.getNome())
                            && qtr.getDiaria().equals(quarto.getDiaria())
                            && qtr.getHotel().equals(hotel)).toList();
            if (u.isEmpty()) {
                quartoRepository.save(quarto);
            }else{
                System.out.println("Elemento cadastrado " + quarto.getNome());
                throw new DuplicateException();
            }
        }

        return quarto;
    }
    public List<Quarto> todos(){
        return quartoRepository.findAll();
    }
    public List<Quarto> quartosHotel(Long id){
        return quartoRepository.findAll().stream()
                .filter(qtr->qtr.getHotel().getId().equals(id)).toList();
    }
    public Quarto delete(Long id) throws NotFoundException {
        Quarto qtr = this.get(id);
        if(qtr!=null){
            quartoRepository.deleteById(id);
        }
        return qtr;
    }
    public Quarto edit(Quarto quarto,Long id) throws NotFoundException {
        return quartoRepository.findById(id).map(qtrBd->{
            qtrBd.setNome(quarto.getNome());
            qtrBd.setDiaria(quarto.getDiaria());
            qtrBd.setCapacidade(quarto.getCapacidade());
            return quartoRepository.save(qtrBd);
        }).orElseThrow(NotFoundException::new);
    }

    public Quarto get(Long id) throws NotFoundException {
        return quartoRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
