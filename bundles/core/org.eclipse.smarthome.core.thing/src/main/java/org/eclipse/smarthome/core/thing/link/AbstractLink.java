/**
 * Copyright (c) 2014-2015 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.core.thing.link;

import org.eclipse.smarthome.core.thing.UID;

/**
 * {@link AbstractLink} is the abstract base class of all links.
 * 
 * @author Dennis Nobel - Initial contribution
 */
public abstract class AbstractLink {

    /**
     * Returns the link ID for a given item name and UID
     * 
     * @param itemName
     *            item name
     * @param uid
     *            UID
     * @return the item channel link ID
     */
    public static String getIDFor(String itemName, UID uid) {
        return itemName + " -> " + uid.toString();
    }

    private final String itemName;

    public AbstractLink(String itemName) {
        this.itemName = itemName;
    }

    AbstractLink() {
        this.itemName = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractLink) {
            AbstractLink link = (AbstractLink) obj;
            return this.getID().equals(link.getID());
        }
        return false;
    }

    /**
     * Returns the ID for the link.
     * 
     * @return id (can not be null)
     */
    public String getID() {
        return getIDFor(getItemName(), getUID());
    }

    /**
     * Returns the item that is linked to the object.
     * 
     * @return item name (can not be null)
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Returns the UID of the object, which is linked to the item.
     * 
     * @return UID (can not be null)
     */
    public abstract UID getUID();

    @Override
    public int hashCode() {
        return this.itemName.hashCode() * this.getUID().hashCode();
    }

    @Override
    public String toString() {
        return getID();
    }
}
