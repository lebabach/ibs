/*
 * SchemaContextHolder
 */
package com.ecard.core.datasource;

import com.ecard.core.datasource.type.SchemaType;
import org.springframework.util.Assert;

/**
 *
 * @author vinhla
 */
public class SchemaContextHolder {
    private static ThreadLocal<SchemaType> contextHolder = new ThreadLocal<SchemaType>();
    /**
     * 
     * @param type
     */
    public static void setSchemaType(SchemaType type) {
        Assert.notNull(type, "Schema type cannot be null.");
        contextHolder.set(type);
    }
    /**
     * 
     * @return 
     */
    public static SchemaType getSchemaType() {
        return contextHolder.get();
    }
    
    /**
     */
    public static void clear() {
        contextHolder.remove();
    }
}
