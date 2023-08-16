package com.example.apiroy.Service.Impl;

import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.Gerne;
import com.example.apiroy.Repository.GerneRepository;
import com.example.apiroy.Service.GerneService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class GerneServiceImpl implements GerneService {
    @Autowired
    private GerneRepository gerneRepository;

    @Override
    public List<Gerne> getAllGerne() {
        return gerneRepository.findAll();
    }

    @Override
    public Gerne getGerneById(Long id) throws Exception {
        Gerne gerne = gerneRepository.findById(id).orElseThrow(() -> new Exception("Thể loại này không tồn tại: " + id));
        return gerne;
    }

    @Override
    public Gerne createGerne(Gerne gerne) {
        return gerneRepository.save(gerne);
    }

    @Override
    public Gerne updateGerne(Long id, Gerne gerneDetails) throws Exception {
        Gerne gerne = gerneRepository.findById(id)
                .orElseThrow(() -> new Exception("Thể loại này không tồn tại: " + id));

        gerne.setId(gerneDetails.getId());
        gerne.setNameOfGerne(gerneDetails.getNameOfGerne());

        return gerneRepository.save(gerne);
    }

    @Override
    public Map<String, Boolean> deleteGerne(Long id) throws Exception {

        Gerne gerne = gerneRepository.findById(id)
                .orElseThrow(() -> new Exception("Thể loại này không tồn tại: " + id));

        gerneRepository.delete(gerne);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @Override
    public List<Book> getBookByGerne(Long id) {
        return gerneRepository.getBookByGerne(id);
    }
}
