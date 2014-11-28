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

/**
 * Provides matching between {@link org.alfresco.service.namespace.QName qnames} using
 * regular expression matching.
 * <p>
 * A simple {@link #MATCH_ALL convenience} pattern matcher is also provided that
 * will match any qname.
 *
 * @author Derek Hulley
 * @see java.lang.String#matches(java.lang.String)
 */
public class RegexQNamePattern implements QNamePattern
{
    /**  A helper pattern matcher that will match <i>all</i> qnames. */
    public static final QNamePattern MATCH_ALL = new QNamePattern()
        {
            public boolean isMatch(QName qname)
            {
                return true;
            }

            @Override
            public boolean equals(Object obj)
            {
                // this is equal if the object's class is the same as this instances
                if (obj == null)
                {
                    return false;
                }
                else if (obj.getClass().equals(this.getClass()))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            
        };
    
    /** The namespace uri pattern. */
    private String namespaceUriPattern;
    
    /** The local name pattern. */
    private String localNamePattern;
    
    /** The combined pattern. */
    private String combinedPattern;
    
    /**
     * Instantiates a new regex q name pattern.
     *
     * @param namespaceUriPattern a regex pattern that will be applied to the namespace URI
     * @param localNamePattern a regex pattern that will be applied to the local name
     */
    public RegexQNamePattern(String namespaceUriPattern, String localNamePattern)
    {
        this.namespaceUriPattern = namespaceUriPattern;
        this.localNamePattern = localNamePattern;
        this.combinedPattern = null;
    }
    
    /**
     * Instantiates a new regex q name pattern.
     *
     * @param combinedPattern a regex pattern that will be applied to the full qname
     *      string representation
     * @see QName#toString()
     */
    public RegexQNamePattern(String combinedPattern)
    {
        this.combinedPattern = combinedPattern;
        this.namespaceUriPattern = null;
        this.localNamePattern = null;
    }
    
    /**
     * To string.
     *
     * @return the string
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder(56);
        sb.append("RegexQNamePattern[");
        if (combinedPattern != null)
        {
            sb.append(" pattern=").append(combinedPattern);
        }
        else
        {
            sb.append(" uri=").append(namespaceUriPattern);
            sb.append(", localname=").append(localNamePattern);
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * Checks if is match.
     *
     * @param qname the value to check against this pattern
     * @return Returns true if the regex pattern provided match thos of the provided qname
     */
    public boolean isMatch(QName qname)
    {
        boolean match = false;
        if (combinedPattern != null)
        {
            String qnameStr = qname.toString();
            match = qnameStr.matches(combinedPattern);
        }
        else
        {
            match = (qname.getNamespaceURI().matches(namespaceUriPattern) &&
                     qname.getLocalName().matches(localNamePattern));
        }
        return match;
    }
}
