/**
 * generated by Xtext
 */
package org.etri.slice.tools.adl.domainmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etri.slice.tools.adl.domainmodel.Event#getTopic <em>Topic</em>}</li>
 *   <li>{@link org.etri.slice.tools.adl.domainmodel.Event#getSuperType <em>Super Type</em>}</li>
 *   <li>{@link org.etri.slice.tools.adl.domainmodel.Event#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etri.slice.tools.adl.domainmodel.DomainmodelPackage#getEvent()
 * @model
 * @generated
 */
public interface Event extends AbstractElement, DataType
{
  /**
   * Returns the value of the '<em><b>Topic</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Topic</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Topic</em>' containment reference.
   * @see #setTopic(Topic)
   * @see org.etri.slice.tools.adl.domainmodel.DomainmodelPackage#getEvent_Topic()
   * @model containment="true"
   * @generated
   */
  Topic getTopic();

  /**
   * Sets the value of the '{@link org.etri.slice.tools.adl.domainmodel.Event#getTopic <em>Topic</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Topic</em>' containment reference.
   * @see #getTopic()
   * @generated
   */
  void setTopic(Topic value);

  /**
   * Returns the value of the '<em><b>Super Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Super Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Super Type</em>' containment reference.
   * @see #setSuperType(JvmParameterizedTypeReference)
   * @see org.etri.slice.tools.adl.domainmodel.DomainmodelPackage#getEvent_SuperType()
   * @model containment="true"
   * @generated
   */
  JvmParameterizedTypeReference getSuperType();

  /**
   * Sets the value of the '{@link org.etri.slice.tools.adl.domainmodel.Event#getSuperType <em>Super Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Super Type</em>' containment reference.
   * @see #getSuperType()
   * @generated
   */
  void setSuperType(JvmParameterizedTypeReference value);

  /**
   * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
   * The list contents are of type {@link org.etri.slice.tools.adl.domainmodel.Property}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Properties</em>' containment reference list.
   * @see org.etri.slice.tools.adl.domainmodel.DomainmodelPackage#getEvent_Properties()
   * @model containment="true"
   * @generated
   */
  EList<Property> getProperties();

} // Event
