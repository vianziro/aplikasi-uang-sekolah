/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.school.tuition.dao;

import com.artivisi.school.tuition.domain.Kelas;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author user
 */
public interface KelasDao extends PagingAndSortingRepository<Kelas, String> {
    
}
