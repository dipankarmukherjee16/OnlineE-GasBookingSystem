package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entity.Invoice;

@Repository
public interface IInvoiceDao extends JpaRepository<Invoice, Integer>{
	@Query("from Invoice i inner join fetch i.booking where i.invoiceStatus=:istatus")
	public List<Invoice> findByInvoiceStatus(@Param("istatus") String status);

}
