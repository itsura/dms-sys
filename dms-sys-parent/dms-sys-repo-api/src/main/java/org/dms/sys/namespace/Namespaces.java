/*
 * Copyright (C) 2005-2012 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dms.sys.namespace;


// TODO: Auto-generated Javadoc
/**
 * Namespace Service.
 * 
 * The Namespace Service provides access to and definition of namespace
 * URIs and Prefixes. 
 * 
 * @author David Caruana
 */
public interface Namespaces
{
    
    /**  Default Namespace URI. */
    static final String DEFAULT_URI = "";
    
    /**  Default Namespace Prefix. */
    static final String DEFAULT_PREFIX = "";

    /**  Default Alfresco URI. */
    static final String DMS_SYS_URI = "http://www.dms-sys.org";
    
    /**  Default Alfresco Prefix. */
    static final String ALFRESCO_PREFIX = "alf";
    
    /**  Dictionary Model URI. */
    static final String DICTIONARY_MODEL_1_0_URI = DMS_SYS_URI + "/model/dictionary/1.0";
    
    /**  Dictionary Model Prefix. */
    static final String DICTIONARY_MODEL_PREFIX = "d";

    /**  System Model URI. */
    static final String SYSTEM_MODEL_1_0_URI = DMS_SYS_URI + "/model/system/1.0";

    /**  System Model Prefix. */
    static final String SYSTEM_MODEL_PREFIX = "sys";

    /**  Content Model URI. */
    static final String CONTENT_MODEL_1_0_URI =  DMS_SYS_URI + "/model/content/1.0";

    /**  Content Model Prefix. */
    static final String CONTENT_MODEL_PREFIX = "cm";

    /**  Business Process Model URI. */
    static final String BPM_MODEL_1_0_URI = DMS_SYS_URI + "/model/bpm/1.0";

    /**  Business Process Model Prefix. */
    static final String BPM_MODEL_PREFIX = "bpm";

    /**  Workflow Model URI. */
    static final String WORKFLOW_MODEL_1_0_URI = DMS_SYS_URI + "/model/workflow/1.0";

    /**  Workflow Model Prefix. */
    static final String WORKFLOW_MODEL_PREFIX = "wf";
    
}
