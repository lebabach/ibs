/*
 * DynamicRoutingDataSourceResolver
 */
package com.ecard.core.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *
 * @author vinhla
 */
public class DynamicRoutingDataSourceResolver extends AbstractRoutingDataSource{
    @Override
    protected Object determineCurrentLookupKey() {
        return SchemaContextHolder.getSchemaType();
    }
}
