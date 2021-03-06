package com.batch.example.demo.configs;

import com.batch.example.demo.models.BitcoinRecord;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class BtcFieldSetMapper implements FieldSetMapper<BitcoinRecord> {
    @Override
    public BitcoinRecord mapFieldSet(FieldSet fieldSet) throws BindException {
        return new BitcoinRecord(
                fieldSet.readString("unix_timestamp"),
                fieldSet.readString("date_time"),
                fieldSet.readString("open"),
                fieldSet.readString("high"),
                fieldSet.readString("low"),
                fieldSet.readString("close"),
                fieldSet.readString("volume_btc"),
                fieldSet.readString("volume_currency"),
                fieldSet.readString("weighted_price")
        );
    }
}
