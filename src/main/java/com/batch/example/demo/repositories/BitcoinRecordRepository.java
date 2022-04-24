package com.batch.example.demo.repositories;

import com.batch.example.demo.models.BitcoinRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BitcoinRecordRepository extends JpaRepository<BitcoinRecord, Long> {

}
