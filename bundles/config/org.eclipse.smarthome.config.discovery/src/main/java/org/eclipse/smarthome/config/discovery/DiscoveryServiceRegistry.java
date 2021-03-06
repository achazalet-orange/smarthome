/**
 * Copyright (c) 2014-2015 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.config.discovery;

import java.util.List;

import org.eclipse.smarthome.core.thing.ThingTypeUID;


/**
 * The {@link DiscoveryServiceRegistry} is a service interface which provides
 * the following features.
 * <ul>
 * <li>Monitoring of {@link DiscoveryService}s</li>
 * <li>Direct accessing monitored {@link DiscoveryService}s</li>
 * <li>Forwarding all events received from the monitored {@link DiscoveryService}s.</li>
 * </ul>
 *
 * @author Michael Grammling - Initial Contribution.
 *
 * @see DiscoveryService
 * @see DiscoveryListener
 */
public interface DiscoveryServiceRegistry {

    /**
     * Forces the associated {@link DiscoveryService}s to start a discovery.
     * <p>
     * Returns {@code true}, if at least one {@link DiscoveryService} could be
     * found and forced to start a discovery, otherwise {@code false}. If the
     * discovery process has already been started before, {@code true} is
     * returned.
     *
     * @param thingTypeUID
     *            the Thing type UID pointing to collection of discovery
     *            services to be forced to start a discovery
     * @param listener
     *            a callback to inform about errors or termination, can be null.
     *            If more than one discovery service is started, the
     *            {@link ScanListener#onFinished()} callback is called after all
     *            discovery services finished their scan. If one discovery
     *            service raises an error, the method
     *            {@link ScanListener#onErrorOccurred(Exception)} is called
     *            directly. All other finished or error callbacks will be
     *            ignored and not forwarded to the listener.
     * @return true if a t least one discovery service could be found and forced
     *         to start a discovery, otherwise false
     */
    boolean startScan(ThingTypeUID thingTypeUID, ScanListener listener);
    
    /**
     * Forces the associated {@link DiscoveryService}s to start a discovery for
     * all thing types of the given binding id.
     * <p>
     * Returns {@code true}, if a at least one {@link DiscoveryService} could be
     * found and forced to start a discovery, otherwise {@code false}.
     *
     * @param bindingId
     *            the binding id pointing to one or more discovery services to
     *            be forced to start a discovery
     * @param listener
     *            a callback to inform about errors or termination, can be null.
     *            If more than one discovery service is started, the
     *            {@link ScanListener#onFinished()} callback is called after all
     *            discovery services finished their scan. If one discovery
     *            service raises an error, the method
     *            {@link ScanListener#onErrorOccurred(Exception)} is called
     *            directly. All other finished or error callbacks will be ignored
     *            and not forwarded to the listener.
     *
     * @return true if a t least one discovery service could be found and forced
     *         to start a discovery, otherwise false
     */
    boolean startScan(String bindingId, ScanListener listener);
    
    /**
     * Aborts a started discovery on all {@link DiscoveryService}s for the given
     * thing type.
     * <p>
     * Returns {@code true}, if at least one {@link DiscoveryService} could be
     * found and all found discoveries could be aborted, otherwise {@code false}
     * . If the discovery process has not been started before, {@code true} is
     * returned.
     *
     * @param thingTypeUID
     *            the Thing type UID whose discovery scans should be aborted
     *
     * @return true if at least one discovery service could be found and all
     *         discoveries could be aborted, otherwise false
     */
    boolean abortScan(ThingTypeUID thingTypeUID);
    
    /**
     * Aborts a started discovery on all {@link DiscoveryService}s for the given
     * binding id.
     * <p>
     * Returns {@code true}, if at least one {@link DiscoveryService} could be
     * found and all found discoveries could be aborted, otherwise {@code false}
     * . If the discovery process has not been started before, {@code true} is
     * returned.
     *
     * @param bindingId
     *            the binding id whose discovery scans should be aborted
     *
     * @return true if at least one discovery service could be found and all
     *         discoveries could be aborted, otherwise false
     */
    boolean abortScan(String bindingId);
    
    /**
     * Returns true if the given thing type UID supports discovery, false
     * otherwise.
     * 
     * @param thingTypeUID
     *            thing type UID
     * @return true if the given thing type UID supports discovery, false
     *         otherwise
     */
    boolean supportsDiscovery(ThingTypeUID thingTypeUID);

    /**
     * Returns true if the given binding id supports discovery for at least one
     * thing type.
     * 
     * @param bindingId
     *            bindingId
     * @return true if the given binding id supports discovery, false otherwise
     */
    boolean supportsDiscovery(String bindingId);
    
    /**
     * Adds a {@link DiscoveryListener} to the listeners' registry.
     * <p>
     * When a {@link DiscoveryResult} is created by any of the monitored {@link DiscoveryService}s,
     * (e.g. by forcing the startup of the discovery process or while enabling the auto discovery
     * mode), the specified listener is notified.
     * <p>
     * This method returns silently if the specified listener is {@code null}
     * or has already been registered before.
     *
     * @param listener the listener to be added (could be null)
     */
    void addDiscoveryListener(DiscoveryListener listener);

    /**
     * Removes a {@link DiscoveryListener} from the listeners' registry.
     * <p>
     * When this method returns, the specified listener is no longer notified about
     * {@link DiscoveryResult}s created by any of the monitored {@link DiscoveryService}s
     * (e.g. by forcing the startup of the discovery process or while enabling the auto
     * discovery mode).
     * <p>
     * This method returns silently if the specified listener is {@code null}
     * or has not been registered before.
     *
     * @param listener the listener to be removed (could be null)
     */
    void removeDiscoveryListener(DiscoveryListener listener);
    
    /**
     * Returns a list of thing types, that support discovery.
     * 
     * @return list of thing types, that support discovery
     */
    List<ThingTypeUID> getSupportedThingTypes();

    /**
     * Returns a list of bindings, that support discovery.
     * 
     * @return list of bindings, that support discovery
     */
    List<String> getSupportedBindings();

}
