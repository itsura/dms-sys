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


// TODO: Auto-generated Javadoc
/**
 * Provides pattern matching against {@link org.alfresco.service.namespace.QName qnames}.
 * <p>
 * Implementations will use different mechanisms to match against the
 * {@link org.alfresco.service.namespace.QName#getNamespaceURI() namespace} and
 * {@link org.alfresco.service.namespace.QName#getLocalName()() localname}.
 *
 * @author Derek Hulley
 * @see org.alfresco.service.namespace.QName
 */
public interface QNamePattern
{
    
    /**
     * Checks if the given qualified name matches the pattern represented
     * by this instance.
     *
     * @param qname the instance to check
     * @return Returns true if the qname matches this pattern
     */
    public boolean isMatch(QName qname);
}
