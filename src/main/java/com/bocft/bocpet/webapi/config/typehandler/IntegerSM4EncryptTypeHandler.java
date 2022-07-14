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

public class IntegerSM4EncryptTypeHandler extends BaseTypeHandler<Integer> {

    private static final SymmetricCrypto sm4 = SmUtil.sm4("B0cFT&CBA#Webap!".getBytes(StandardCharsets.UTF_8));

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Integer parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setBytes(i, sm4.encrypt(String.valueOf(parameter), StandardCharsets.UTF_8));
    }

    @Override
    public Integer getNullableResult(ResultSet rs, String columnName) throws SQLException {
        byte[] columnValue = rs.getBytes(columnName);
        return columnValue == null ? null : Integer.valueOf(sm4.decryptStr(columnValue, StandardCharsets.UTF_8));
    }

    @Override
    public Integer getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        byte[] columnValue = rs.getBytes(columnIndex);
        return columnValue == null ? null : Integer.valueOf(sm4.decryptStr(columnValue, StandardCharsets.UTF_8));
    }

    @Override
    public Integer getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        byte[] columnValue = cs.getBytes(columnIndex);
        return columnValue == null ? null : Integer.valueOf(sm4.decryptStr(columnValue, StandardCharsets.UTF_8));
    }
}
