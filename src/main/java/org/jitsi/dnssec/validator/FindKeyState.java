/*
 * dnssecjava - a DNSSEC validating stub resolver for Java
 * Copyright (c) 2013-2015 Ingo Bauersachs
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * This file is based on work under the following copyright and permission
 * notice:
 * 
 *     Copyright (c) 2005 VeriSign. All rights reserved.
 * 
 *     Redistribution and use in source and binary forms, with or without
 *     modification, are permitted provided that the following conditions are
 *     met:
 * 
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. The name of the author may not be used to endorse or promote
 *        products derived from this software without specific prior written
 *        permission.
 * 
 *     THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *     IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *     WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 *     ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT,
 *     INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *     (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *     SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 *     HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 *     STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 *     IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *     POSSIBILITY OF SUCH DAMAGE.
 */

package org.jitsi.dnssec.validator;

import org.jitsi.dnssec.SRRset;
import org.xbill.DNS.Name;

/**
 * State-object for the key-finding phase.
 */
class FindKeyState {
    /**
     * The (initial) DS RRset for the following DNSKEY search and validate
     * phase.
     */
    SRRset dsRRset;

    /**
     * Iteratively holds the key during the search phase.
     */
    KeyEntry keyEntry;

    /**
     * The name of the key to search. This is taken from the RRSIG's signer name
     * or the query name if no signer name is available.
     */
    Name signerName;

    /**
     * The query class if the key to find.
     */
    int qclass;

    /**
     * Sets the key name being searched for when a DS response is provably not a
     * delegation point.
     */
    Name emptyDSName;

    /**
     * The initial key name when the key search is started from a trust anchor.
     */
    Name currentDSKeyName;
    
    /**
     * True if we have searched all the way down to signerName in the DNS tree.
     */
    boolean isExhausted;

    /**
     * Set to false if parent can prove no DS. Fail-safe default is true.
     */
    boolean hasChainOfTrust = true;
}
