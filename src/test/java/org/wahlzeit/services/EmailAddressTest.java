/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.services;

import junit.framework.TestCase;
import org.junit.Test;

import javax.mail.internet.AddressException;

/**
 * Test cases for the EmailAddress class.
 */
public class EmailAddressTest extends TestCase {

	/**
	 *
	 */
	public EmailAddressTest(String name) {
		super(name);
	}

    /**
     *
     */
    @Test
    public void testConstructorSetsCorrectValue() {
        String emailName = "bingo.bongo@web.de";
        EmailAddress email = new EmailAddress(emailName);

        assertTrue(emailName.equals(email.value));
    }

	/**
	 *
	 */
	@Test
	public void testGetEmailAddressReturnsCorrectEmail() {
		String emailName = "bingo.bongo@web.de";

		EmailAddress emailFirstRequest = EmailAddress.getFromString(emailName);
		EmailAddress emailSecondRequest = EmailAddress.getFromString(emailName);

		assertTrue(emailFirstRequest.asString().equals(emailName));
		assertTrue(emailSecondRequest.asString().equals(emailName));
	}

	/**
	 *
	 */
	@Test
	public void testGetEmailAddressFromString() {
		// invalid email addresses are allowed for local testing and online avoided by Google

		assertTrue(createEmailAddressIgnoreException("bingo@bongo"));
		assertTrue(createEmailAddressIgnoreException("bingo@bongo.com"));
		assertTrue(createEmailAddressIgnoreException("bingo.bongo@bongo.com"));
		assertTrue(createEmailAddressIgnoreException("bingo+bongo@bango"));
	}

    /**
     *
     */
    @Test
    public void testIsEqualOnSameObject() {
        EmailAddress email = EmailAddress.getFromString("bingo@bongo");

        assertTrue(email.isEqual(email));
    }

    /**
     *
     */
    @Test
    public void testIsEqualOnDifferentObject() {
        EmailAddress email = EmailAddress.getFromString("bingo@bongo");
        EmailAddress email2 = EmailAddress.getFromString("bingo@bongo2");

        assertFalse(email.isEqual(email2));
    }


    /**
	 *
	 */
	protected boolean createEmailAddressIgnoreException(String ea) {
		try {
			EmailAddress.getFromString(ea);
			return true;
		} catch (IllegalArgumentException ex) {
			// creation failed
			return false;
		}
	}

	/**
	 *
	 */
	public void testEmptyEmailAddress() {
		assertFalse(EmailAddress.EMPTY.isValid());
	}

}

