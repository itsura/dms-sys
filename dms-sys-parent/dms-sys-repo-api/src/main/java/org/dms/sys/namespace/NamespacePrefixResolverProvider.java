/*
 * Copyright (C) 2005-2010 Alfresco Software Limited.
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

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * Provides support for serializable objects such as the QNameMap that require a
 * NamespacePrefixResolver to be available. Ensures that the objects can remain
 * serializable themselves and still maintain a valid NamespacePrefixResolver.
 * 
 * @author Kevin Roast
 */
public interface NamespacePrefixResolverProvider extends Serializable
{
    
    /**
     * Get an object that implements the NamespacePrefixResolver interface.
     *
     * @return NamespacePrefixResolver
     */
    NamespacePrefixResolver getNamespacePrefixResolver();
}