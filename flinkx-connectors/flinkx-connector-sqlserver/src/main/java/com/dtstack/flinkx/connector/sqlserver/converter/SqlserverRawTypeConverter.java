/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dtstack.flinkx.connector.sqlserver.converter;

import com.dtstack.flinkx.throwable.UnsupportedTypeException;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.types.DataType;

import java.util.Locale;

/**
 * Company：www.dtstack.com
 *
 * @author shitou
 * @date 2021/5/19 14:26
 */
public class SqlserverRawTypeConverter {

    /**
     * Reference from https://www.sqlservertutorial.net/sql-server-basics/sql-server-data-types/
     *
     * @param type
     *
     * @return
     *
     * @throws UnsupportedTypeException
     */
    public static DataType apply(String type) throws UnsupportedTypeException {
        switch (type.toUpperCase(Locale.ENGLISH)) {
            case "BIT":
                return DataTypes.BOOLEAN();
            case "BIGINT":
                return DataTypes.BIGINT();
            case "TINYINT":
                return DataTypes.TINYINT();
            case "INT":
            case "SMALLINT":
            case "INT IDENTITY":
                return DataTypes.INT();
            case "REAL":
                return DataTypes.FLOAT();
            case "FLOAT":
                return DataTypes.DOUBLE();
            case "DECIMAL":
            case "NUMERIC":
                return DataTypes.DECIMAL(1, 0);
            case "CHAR":
            case "VARCHAR":
            case "VARCHAR(MAX)":
            case "TEXT":
            case "XML":
                return DataTypes.STRING();
            case "NCHAR":
            case "NVARCHAR":
            case "NVARCHAR(MAX)":
            case "NTEXT":
                return DataTypes.STRING();
// For net.sourceforge.jtds.jdbc.JtdsResultSet ,datetime2 type is String
            case "DATETIME2":
                return DataTypes.STRING();
            case "UNIQUEIDENTIFIER":
                return DataTypes.STRING();
            case "DATE":
                return DataTypes.DATE();
// For net.sourceforge.jtds.jdbc.JtdsResultSet ,time type is String
            case "TIME":
                return DataTypes.STRING();
            case "DATETIME":
            case "SMALLDATETIME":
//            case "DATETIMEOFFSET": is unsupported
                return DataTypes.TIMESTAMP();
            case "BINARY":
            case "VARBINARY":
            case "IMAGE":
            case "TIMESTAMP":
                // BYTES bottom call is the maximum length of VARBINARY
                return DataTypes.BYTES();
            case "MONEY":
            case "SMALLMONEY":
                return DataTypes.DECIMAL(1, 0);
            default:
                throw new UnsupportedTypeException(type);
        }
    }
}
