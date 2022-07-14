package com.bocft.bocpet.webapi.config.typehandler;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSM4EncryptTypeHandler extends BaseTypeHandler<LocalDate> {

    private static final SymmetricCrypto sm4 = SmUtil.sm4("B0cFT&CBA#Webap!".getBytes(StandardCharsets.UTF_8));
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDate parameter, JdbcType jdbcType)
            throws SQLException {
        String dateStr = parameter.format(dtf);
        ps.setBytes(i, sm4.encrypt(dateStr, StandardCharsets.UTF_8));
    }

    @Override
    public LocalDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
        byte[] columnValue = rs.getBytes(columnName);
        return columnValue == null ? null : LocalDate.parse(sm4.decryptStr(columnValue, StandardCharsets.UTF_8), dtf);
    }

    @Override
    public LocalDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        byte[] columnValue = rs.getBytes(columnIndex);
        return columnValue == null ? null : LocalDate.parse(sm4.decryptStr(columnValue, StandardCharsets.UTF_8), dtf);
    }

    @Override
    public LocalDate getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        byte[] columnValue = cs.getBytes(columnIndex);
        return columnValue == null ? null : LocalDate.parse(sm4.decryptStr(columnValue, StandardCharsets.UTF_8), dtf);
    }
}
