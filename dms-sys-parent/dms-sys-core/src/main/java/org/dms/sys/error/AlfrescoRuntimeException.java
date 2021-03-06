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
package org.dms.sys.error;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.extensions.surf.util.I18NUtil;

/**
 * I18n'ed runtime exception thrown by Alfresco code.
 * 
 * @author gavinc
 */
public class AlfrescoRuntimeException extends RuntimeException
{
    
    /** Serial version UUID. */
    private static final long serialVersionUID = 3787143176461219632L;

    /** The msg id. */
    private String msgId;
    
    /** The msg params. */
    private transient Object[] msgParams = null;
    
    /**
     * Helper factory method making use of variable argument numbers.
     *
     * @param msgId the msg id
     * @param objects the objects
     * @return the alfresco runtime exception
     */
    public static AlfrescoRuntimeException create(String msgId, Object ...objects)
    {
        return new AlfrescoRuntimeException(msgId, objects);
    }

    /**
     * Helper factory method making use of variable argument numbers.
     *
     * @param cause the cause
     * @param msgId the msg id
     * @param objects the objects
     * @return the alfresco runtime exception
     */
    public static AlfrescoRuntimeException create(Throwable cause, String msgId, Object ...objects)
    {
        return new AlfrescoRuntimeException(msgId, objects, cause);
    }
    
    /**
     * Utility to convert a general Throwable to a RuntimeException.  No conversion is done if the
     * throwable is already a <tt>RuntimeException</tt>.
     *
     * @param e the e
     * @param msgId the msg id
     * @param objects the objects
     * @return the runtime exception
     * @see #create(Throwable, String, Object...)
     */
    public static RuntimeException makeRuntimeException(Throwable e, String msgId, Object ...objects)
    {
        if (e instanceof RuntimeException)
        {
            return (RuntimeException) e;
        }
        // Convert it
        return AlfrescoRuntimeException.create(e, msgId, objects);
    }
    
    /**
     * Constructor.
     *
     * @param msgId     the message id
     */
    public AlfrescoRuntimeException(String msgId)
    {
        super(resolveMessage(msgId, null));
        this.msgId = msgId;
    }
    
    /**
     * Constructor.
     *
     * @param msgId         the message id
     * @param msgParams     the message parameters
     */
    public AlfrescoRuntimeException(String msgId, Object[] msgParams)
    {
        super(resolveMessage(msgId, msgParams));
        this.msgId = msgId;
        this.msgParams = msgParams;
    }

    /**
     * Constructor.
     *
     * @param msgId     the message id
     * @param cause     the exception cause
     */
    public AlfrescoRuntimeException(String msgId, Throwable cause)
    {
        super(resolveMessage(msgId, null), cause);
        this.msgId = msgId;
    }
    
    /**
     * Constructor.
     *
     * @param msgId         the message id
     * @param msgParams     the message parameters
     * @param cause         the exception cause
     */
    public AlfrescoRuntimeException(String msgId, Object[] msgParams, Throwable cause)
    {
        super(resolveMessage(msgId, msgParams), cause);
        this.msgId = msgId;
        this.msgParams = msgParams;
    }
    
    /**
     * Gets the msg id.
     *
     * @return the msgId
     */
    public String getMsgId()
    {
        return msgId;
    }

    /**
     * Gets the msg params.
     *
     * @return the msgParams
     */
    public Object[] getMsgParams()
    {
        return msgParams;
    }

    /**
     * Resolves the message id to the localised string.
     * <p>
     * If a localised message can not be found then the message Id is
     * returned.
     * 
     * @param messageId     the message Id
     * @param params        message parameters
     * @return              the localised message (or the message id if none found)
     */
    private static String resolveMessage(String messageId, Object[] params)
    {
        String message = I18NUtil.getMessage(messageId, params);
        if (message == null)
        {
            // If a localised string cannot be found then return the messageId
            message = messageId;
        }
        return buildErrorLogNumber(message);
    }
    
    /**
     * Generate an error log number - based on MMDDXXXX - where M is month,
     * D is day and X is an atomic integer count.
     * 
     * @param message       Message to prepend the error log number to 
     * 
     * @return message with error log number prefix
     */
    private static String buildErrorLogNumber(String message)
    {
        // ensure message is not null
        if (message == null)
        {
            message= "";
        }
        
        Date today = new Date();
        StringBuilder buf = new StringBuilder(message.length() + 10);
        padInt(buf, today.getMonth(), 2);
        padInt(buf, today.getDate(), 2);
        padInt(buf, errorCounter.getAndIncrement(), 4);
        buf.append(' ');
        buf.append(message);
        return buf.toString();
    }
    
    /**
     * Helper to zero pad a number to specified length.
     *
     * @param buffer the buffer
     * @param value the value
     * @param length the length
     */
    private static void padInt(StringBuilder buffer, int value, int length)
    {
        String strValue = Integer.toString(value);
        for (int i = length - strValue.length(); i > 0; i--)
        {
            buffer.append('0');
        }
        buffer.append(strValue);
    }
    
    /** The error counter. */
    private static AtomicInteger errorCounter = new AtomicInteger();
    
    /**
     * Get the root cause.
     *
     * @return the root cause
     */
    public Throwable getRootCause()
    {
        Throwable cause = this;
        for (Throwable tmp = this; tmp != null ; tmp = cause.getCause())
        {
            cause = tmp;
        }
        return cause;
    }
}
