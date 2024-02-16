package com.hp.hpreservasapi.service;

import com.hp.hpreservasapi.exception.DuplicateException;
import com.hp.hpreservasapi.exception.NotFoundException;
import com.hp.hpreservasapi.model.Hotel;
import com.hp.hpreservasapi.repository.IHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    IHotelRepository hotelRepository;
    
    public Hotel add(Hotel hotel) throws Exception{
        var u = hotelRepository.findAll().stream()
                .filter(h-> h.getNome().equalsIgnoreCase(h.getNome())).toList();
        if (u.isEmpty()) {
            hotelRepository.save(hotel);
        }else{
            System.out.println("Elemento ja cadastrado " + hotel.getNome());
            throw new DuplicateException();
        }
        return hotel;
    }
    public List<Hotel> todos(){
        return hotelRepository.findAll();
    }

//    public Hotel delete(Long id) throws NotFoundException {
//        Hotel hotel = this.get(id);
//        if(quartoService.quartosHotel(id).isEmpty()){
//            if(hotel!=null){
//                hotelRepository.deleteById(id);
//            }
//        }
//        return hotel;
//    }
    public Hotel edit(Hotel hotel,Long id) throws NotFoundException {
        return hotelRepository.findById(id).map(hotelBd->{
            hotelBd.setNome(hotel.getNome());
            return hotelRepository.save(hotelBd);
        }).orElseThrow(NotFoundException::new);
    }

    public Hotel get(Long id) throws NotFoundException {
        return hotelRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
