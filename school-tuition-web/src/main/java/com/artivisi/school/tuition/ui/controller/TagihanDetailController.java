/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.school.tuition.ui.controller;

import com.artivisi.school.tuition.service.BelajarRestfulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.artivisi.school.tuition.domain.TagihanDetail;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

/**
 *
 * @author axioo
 */
public class TagihanDetailController {
    
     @Autowired
    private BelajarRestfulService belajarRestfulService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/TagihanDetail/{id}")
    @ResponseBody
     public TagihanDetail findByIdTahunAjaran(@PathVariable String id) {
       TagihanDetail x = belajarRestfulService.findTagihanDetailById(id);
        if (x == null) {
            throw new IllegalStateException();
        }
       return null;
        
    }
    @RequestMapping(value = "/tagihandetail", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid TagihanDetail x, HttpServletRequest request, HttpServletResponse response) {
        belajarRestfulService.save(x);
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, x.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
      @RequestMapping(method = RequestMethod.PUT, value = "/tagihandetail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody @Valid TagihanDetail x) {
        TagihanDetail a = belajarRestfulService.findTagihanDetailById(id);
        if (a == null) {
            logger.warn("Tagihan detail dengan id [{}] tidak ditemukan", id);
            throw new IllegalStateException();
        }
        x.setId(a.getId());
        belajarRestfulService.save(x);
    }
        @RequestMapping(method = RequestMethod.DELETE, value = "/tagihandetail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        TagihanDetail a = belajarRestfulService.findTagihanDetailById(id);
        if (a == null) {
            logger.warn("TahunAjaran dengan id [{}] tidak ditemukan", id);
            throw new IllegalStateException();
        }
        belajarRestfulService.delete(a);
    }
       @RequestMapping(value = "/tahunAjaran", method = RequestMethod.GET)
    @ResponseBody
    public List<TagihanDetail> findAll(
            Pageable pageable,
            HttpServletResponse response) {
        List<TagihanDetail> hasil = belajarRestfulService.findAllTagihanDetail(pageable).getContent();

      

        return hasil;
    }
        @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({IllegalStateException.class})
    public void handle() {
        logger.debug("Resource dengan URI tersebut tidak ditemukan");
    }
}