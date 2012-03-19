// **********************************************************************
//
// Copyright (c) 2003-2010 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.4.1

package Ice;

// <auto-generated>
//
// Generated from file `LocalException.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>


/**
 * This exception indicates an unsupported data encoding version.
 * 
 **/
public class UnsupportedEncodingException extends ProtocolException
{
    public UnsupportedEncodingException()
    {
        super();
    }

    public UnsupportedEncodingException(String reason, int badMajor, int badMinor, int major, int minor)
    {
        super(reason);
        this.badMajor = badMajor;
        this.badMinor = badMinor;
        this.major = major;
        this.minor = minor;
    }

    public String
    ice_name()
    {
        return "Ice::UnsupportedEncodingException";
    }

    /**
     * The major version number of the unsupported encoding.
     * 
     **/
    public int badMajor;

    /**
     * The minor version number of the unsupported encoding.
     * 
     **/
    public int badMinor;

    /**
     * The major version number of the encoding that is supported.
     * 
     **/
    public int major;

    /**
     * The highest minor version number of the encoding that can be supported.
     * 
     **/
    public int minor;
}