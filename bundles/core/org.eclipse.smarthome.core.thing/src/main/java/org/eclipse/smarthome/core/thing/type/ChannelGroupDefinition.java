/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.core.thing.type;

import org.eclipse.smarthome.core.thing.Thing;


/**
 * The {@link ChannelGroupDefinition} class defines a {@link ChannelGroupType}
 * of a {@link ThingType}.
 * <p>
 * A {@link ChannelGroupType} is part of a {@link Thing} that represents a set of channels
 * (functionalities) of it.
 * <p>
 * <b>Hint:</b> This class is immutable.
 * 
 * @author Michael Grammling - Initial Contribution
 */
public class ChannelGroupDefinition {

    private String id;
    private ChannelGroupType type;


    /**
     * Creates a new instance of this class with the specified parameters.
     * 
     * @param id the identifier of the channel group (must neither be null nor empty)
     * @param type the type of the channel group (must not be null)
     * 
     * @throws IllegalArgumentException if the ID is null or empty, or the type is null
     */
    public ChannelGroupDefinition(String id, ChannelGroupType type) throws IllegalArgumentException {
        if ((id == null) || (id.isEmpty())) {
          throw new IllegalArgumentException("The ID must neither be null nor empty!");  
        }

        if (type == null) {
            throw new IllegalArgumentException("The channel group type must not be null");  
        }

        this.id = id;
        this.type = type;
    }

    /**
     * Returns the identifier of the channel group.
     * 
     * @return the identifier of the channel group (neither null, nor empty) 
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the type of the channel group.
     * 
     * @return the type of the channel group (not null)
     */
    public ChannelGroupType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "ChannelGroupDefinition [id=" + id + ", type=" + type + "]";
    }

}
