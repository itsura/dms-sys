/*
 * Copyright (C) 2005-2011 Alfresco Software Limited.
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
package org.dms.sys.service.cmr.security;

import java.util.Set;

/**
 * The public API for a permission service The implementation may be changed in the application configuration.
 *
 * @author Andy Hind
 */
public interface PermissionService
{
    /**
     * Prefixes used for authorities of type role. This is intended for external roles, e.g. those set by ACEGI
     * implementations It is only used for admin at the moment - which is done outside the usual permission assignments
     * at the moment. It could be a dynamic authority.
     */
    public static final String ROLE_PREFIX = "ROLE_";

    /**
     * Prefix used for authorities of type group.
     */
    public static final String GROUP_PREFIX = "GROUP_";

    /**
     * The group that contains everyone except guest.
     */
    public static final String ALL_AUTHORITIES = "GROUP_EVERYONE";

    /** The dynamic authority used for ownership. */
    public static final String OWNER_AUTHORITY = "ROLE_OWNER";

    /**
     * The authority that all owners of WCM stores have.
     */
    public static final String WCM_STORE_OWNER_AUTHORITY = "ROLE_WCM_STORE_OWNER";
    
    /**
     * The dynamic authority used for the ownership of locks.
     */
    public static final String LOCK_OWNER_AUTHORITY = "ROLE_LOCK_OWNER";

    /**
     * The admin authority - currently a role.
     */
    public static final String ADMINISTRATOR_AUTHORITY = "ROLE_ADMINISTRATOR";

    /** The guest authority. */
    public static final String GUEST_AUTHORITY = "ROLE_GUEST";

    /**
     * The permission for all - not defined in the model. Repsected in the code.
     */
    public static final String ALL_PERMISSIONS = "All";

    // Constants for permissions/permission groups defined in the standard permission model.

    /** The Constant FULL_CONTROL. */
    public static final String FULL_CONTROL = "FullControl";

    /** The Constant READ. */
    public static final String READ = "Read";

    /** The Constant WRITE. */
    public static final String WRITE = "Write";

    /** The Constant DELETE. */
    public static final String DELETE = "Delete";

    /** The Constant ADD_CHILDREN. */
    public static final String ADD_CHILDREN = "AddChildren";

    /** The Constant READ_PROPERTIES. */
    public static final String READ_PROPERTIES = "ReadProperties";

    /** The Constant READ_CHILDREN. */
    public static final String READ_CHILDREN = "ReadChildren";

    /** The Constant WRITE_PROPERTIES. */
    public static final String WRITE_PROPERTIES = "WriteProperties";

    /** The Constant DELETE_NODE. */
    public static final String DELETE_NODE = "DeleteNode";

    /** The Constant DELETE_CHILDREN. */
    public static final String DELETE_CHILDREN = "DeleteChildren";

    /** The Constant CREATE_CHILDREN. */
    public static final String CREATE_CHILDREN = "CreateChildren";

    /** The Constant LINK_CHILDREN. */
    public static final String LINK_CHILDREN = "LinkChildren";

    /** The Constant DELETE_ASSOCIATIONS. */
    public static final String DELETE_ASSOCIATIONS = "DeleteAssociations";

    /** The Constant READ_ASSOCIATIONS. */
    public static final String READ_ASSOCIATIONS = "ReadAssociations";

    /** The Constant CREATE_ASSOCIATIONS. */
    public static final String CREATE_ASSOCIATIONS = "CreateAssociations";

    /** The Constant READ_PERMISSIONS. */
    public static final String READ_PERMISSIONS = "ReadPermissions";

    /** The Constant CHANGE_PERMISSIONS. */
    public static final String CHANGE_PERMISSIONS = "ChangePermissions";

    /** The Constant EXECUTE. */
    public static final String EXECUTE = "Execute";

    /** The Constant READ_CONTENT. */
    public static final String READ_CONTENT = "ReadContent";

    /** The Constant WRITE_CONTENT. */
    public static final String WRITE_CONTENT = "WriteContent";

    /** The Constant EXECUTE_CONTENT. */
    public static final String EXECUTE_CONTENT = "ExecuteContent";

    /** The Constant TAKE_OWNERSHIP. */
    public static final String TAKE_OWNERSHIP = "TakeOwnership";

    /** The Constant SET_OWNER. */
    public static final String SET_OWNER = "SetOwner";

    /** The Constant COORDINATOR. */
    public static final String COORDINATOR = "Coordinator";

    /** The Constant CONTRIBUTOR. */
    public static final String CONTRIBUTOR = "Contributor";

    /** The Constant EDITOR. */
    public static final String EDITOR = "Editor";

    /** The Constant CONSUMER. */
    public static final String CONSUMER = "Consumer";

    /** The Constant LOCK. */
    public static final String LOCK = "Lock";

    /** The Constant UNLOCK. */
    public static final String UNLOCK = "Unlock";

    /** The Constant CHECK_OUT. */
    public static final String CHECK_OUT = "CheckOut";

    /** The Constant CHECK_IN. */
    public static final String CHECK_IN = "CheckIn";

    /** The Constant CANCEL_CHECK_OUT. */
    public static final String CANCEL_CHECK_OUT = "CancelCheckOut";

    /** The Constant ASPECTS. */
    public static final String ASPECTS = "Aspects";

    /** The Constant PROPERTIES. */
    public static final String PROPERTIES = "Properties";

    /** The Constant WCM_CONTENT_MANAGER. */
    public static final String WCM_CONTENT_MANAGER = "ContentManager";

    /** The Constant WCM_CONTENT_PUBLISHER. */
    public static final String WCM_CONTENT_PUBLISHER = "ContentPublisher";

    /** The Constant WCM_CONTENT_CONTRIBUTOR. */
    public static final String WCM_CONTENT_CONTRIBUTOR = "ContentContributor";

    /** The Constant WCM_CONTENT_REVIEWER. */
    public static final String WCM_CONTENT_REVIEWER = "ContentReviewer";

    /** The Constant FLATTEN. */
    public static final String FLATTEN = "Flatten";

    /**
     * Get the Owner Authority.
     *
     * @return the owner authority
     */
    public String getOwnerAuthority();
    
    /**
     * Get the All Authorities.
     *
     * @return the All authorities
     */
    public String getAllAuthorities();

    /**
     * Get the All Permission.
     *
     * @return the All permission
     */
    public String getAllPermission();

    /**
     * Get all the AccessPermissions that are granted/denied to the current authentication for the given node.
     *
     * @param aclId the acl id
     * @return the set of allowed permissions
     */
//    public Set<AccessPermission> getPermissions(NodeRef nodeRef);

    /**
     * Get all the AccessPermissions that are set for anyone for the given node.
     *
     * @param nodeRef -
     * the reference to the node
     * @return the set of allowed permissions
     */
//    public Set<AccessPermission> getAllSetPermissions(NodeRef nodeRef);

    /**
     * Get the permissions that can be set for a given node.
     *
     * @param nodeRef the node ref
     * @return the settable permissions
     */
//    public Set<String> getSettablePermissions(NodeRef nodeRef);

    /**
     * Get the permissions that can be set for a given type.
     *
     * @param type the type
     * @return - set of permissions
     */
//    public Set<String> getSettablePermissions(QName type);

    /**
     * Check that the given authentication has a particular permission for the given node. (The default behaviour is to
     * inherit permissions)
     *
     * @param nodeRef the node ref
     * @param permission the permission
     * @return - access status
     */
//    public AccessStatus hasPermission(NodeRef nodeRef, String permission);

    /**
     * Check if read permission is allowed on an acl (optimised)
     * 
     * caveats:
     * doesn't take into account dynamic authorities/groups
     * doesn't take into account node types/aspects for permissions.
     *
     * @param nodeRef -
     * the reference to the node
     * @return access status
     */
//    public AccessStatus hasReadPermission(NodeRef nodeRef);

    /**
     * Get the readers associated with a given ACL.
     *
     * @param aclId                 the low-level ACL ID
     * @return                      set of authorities with read permission on the ACL
     */
    public Set<String> getReaders(Long aclId);
    
    /**
     * Check if a permission is allowed on an acl.
     *
     * @return the access status
     */
//    public AccessStatus hasPermission(Long aclID, PermissionContext context, String permission);

    /**
     * Delete all the permission assigned to the node.
     *
     * @param nodeRef the node ref
     */
//    public void deletePermissions(NodeRef nodeRef);

    /**
     * Delete all permission for the given authority.
     *
     * @param nodeRef the node ref
     * @param authority (if null then this will match all authorities)
     */
//    public void clearPermission(NodeRef nodeRef, String authority);

    /**
     * Find and delete a access control entry by node, authentication and permission. It is possible to delete
     * <ol>
     * <li> a specific permission;
     * <li> all permissions for an authority (if the permission is null);
     * <li> entries for all authorities that have a specific permission (if the authority is null); and
     * <li> all permissions set for the node (if both the permission and authority are null).
     * </ol>
     *
     * @param nodeRef
     *            the node that the entry applies to
     * @param authority
     *            the authority recipient (if null then this will match all authorities)
     * @param permission
     *            the entry permission (if null then this will match all permissions)
     */
//    public void deletePermission(NodeRef nodeRef, String authority, String permission);
    
    /**
     * RM Specific - find and delete a access control entry by nodes, authentication and permission. It is possible to delete
     * <ol>
     * <li> a specific permission;
     * <li> all permissions for an authority (if the permission is null);
     * <li> entries for all authorities that have a specific permission (if the authority is null); and
     * <li> all permissions set for the node (if both the permission and authority are null).
     * </ol>
     *
     * @param nodeRefs the node refs
     * @param authority the authority recipient (if null then this will match all authorities)
     * @param permission the entry permission (if null then this will match all permissions)
     */
//    public void deletePermission(List<NodeRef> nodeRefs, String authority, String permission);

    /**
     * Set a specific permission on a node.
     *
     * @param nodeRef the node ref
     * @param authority the authority
     * @param permission the permission
     * @param allow the allow
     */
//    public void setPermission(NodeRef nodeRef, String authority, String permission, boolean allow);

    /**
     * RM Specific - set a specific permission on nodes.
     *
     * @param nodeRefs the node refs
     * @param authority the authority
     * @param permission the permission
     * @param allow the allow
     */
//    public void setPermissions(List<NodeRef> nodeRefs, String authority, String permission, boolean allow);
    
    /**
     * Set the global inheritance behaviour for permissions on a node.
     *
     * @param nodeRef the node ref
     * @param inheritParentPermissions the inherit parent permissions
     */
//    public void setInheritParentPermissions(NodeRef nodeRef, boolean inheritParentPermissions);

    /**
     * Return the global inheritance behaviour for permissions on a node.
     *
     * @param nodeRef the node ref
     * @return the inherit parent permissions
     */
//    public boolean getInheritParentPermissions(NodeRef nodeRef);

   
    /**
     * Add a permission mask to a store.
     *
     * @param storeRef the store ref
     * @param authority the authority
     * @param permission the permission
     * @param allow the allow
     */
//    public void setPermission(StoreRef storeRef, String authority, String permission, boolean allow);
    
    /**
     * Remove part of a permission mask on a store.
     *
     * @param storeRef the store ref
     * @param authority the authority
     * @param permission the permission
     */
//    public void deletePermission(StoreRef storeRef, String authority, String permission);
    
    /**
     * Clear all permission masks for an authority on a store.
     *
     * @param storeRef the store ref
     * @param authority the authority
     */
//    public void clearPermission(StoreRef storeRef, String authority);
    
    /**
     * Remove all permission mask on a store.
     *
     * @param storeRef the store ref
     */
//    public void deletePermissions(StoreRef storeRef);
    
    
    /**
     * Get all the AccessPermissions that are set for anyone for the given node.
     *
     * @param storeRef -
     * the reference to the store
     * @return the set of allowed permissions
     */
//    public Set<AccessPermission> getAllSetPermissions(StoreRef storeRef);
    
    /**
     * Get the set of authorities for currently authenticated user.
     *
     * @return              a set of authorities applying to the currently-authenticated user
     */
    public Set<String> getAuthorisations();
}