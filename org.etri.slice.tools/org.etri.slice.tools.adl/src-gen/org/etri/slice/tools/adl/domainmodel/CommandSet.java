/**
 * generated by Xtext
 */
package org.etri.slice.tools.adl.domainmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.common.types.JvmTypeReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Command Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etri.slice.tools.adl.domainmodel.CommandSet#getControl <em>Control</em>}</li>
 *   <li>{@link org.etri.slice.tools.adl.domainmodel.CommandSet#getCommands <em>Commands</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etri.slice.tools.adl.domainmodel.DomainmodelPackage#getCommandSet()
 * @model
 * @generated
 */
public interface CommandSet extends EObject
{
  /**
   * Returns the value of the '<em><b>Control</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Control</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Control</em>' containment reference.
   * @see #setControl(JvmTypeReference)
   * @see org.etri.slice.tools.adl.domainmodel.DomainmodelPackage#getCommandSet_Control()
   * @model containment="true"
   * @generated
   */
  JvmTypeReference getControl();

  /**
   * Sets the value of the '{@link org.etri.slice.tools.adl.domainmodel.CommandSet#getControl <em>Control</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Control</em>' containment reference.
   * @see #getControl()
   * @generated
   */
  void setControl(JvmTypeReference value);

  /**
   * Returns the value of the '<em><b>Commands</b></em>' containment reference list.
   * The list contents are of type {@link org.etri.slice.tools.adl.domainmodel.Command}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Commands</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Commands</em>' containment reference list.
   * @see org.etri.slice.tools.adl.domainmodel.DomainmodelPackage#getCommandSet_Commands()
   * @model containment="true"
   * @generated
   */
  EList<Command> getCommands();

} // CommandSet