/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.school.tuition.dao;

import com.artivisi.school.tuition.domain.Pembayaran;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Presario CQ43
 */
public interface PembayaranDao extends PagingAndSortingRepository<Pembayaran, String> {
    
}
