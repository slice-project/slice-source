/**
 * generated by Xtext
 */
package org.etri.slice.tools.adl.domainmodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Behavior</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etri.slice.tools.adl.domainmodel.Behavior#getName <em>Name</em>}</li>
 *   <li>{@link org.etri.slice.tools.adl.domainmodel.Behavior#getSituation <em>Situation</em>}</li>
 *   <li>{@link org.etri.slice.tools.adl.domainmodel.Behavior#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etri.slice.tools.adl.domainmodel.DomainmodelPackage#getBehavior()
 * @model
 * @generated
 */
public interface Behavior extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.etri.slice.tools.adl.domainmodel.DomainmodelPackage#getBehavior_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.etri.slice.tools.adl.domainmodel.Behavior#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Situation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Situation</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Situation</em>' containment reference.
   * @see #setSituation(Situation)
   * @see org.etri.slice.tools.adl.domainmodel.DomainmodelPackage#getBehavior_Situation()
   * @model containment="true"
   * @generated
   */
  Situation getSituation();

  /**
   * Sets the value of the '{@link org.etri.slice.tools.adl.domainmodel.Behavior#getSituation <em>Situation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Situation</em>' containment reference.
   * @see #getSituation()
   * @generated
   */
  void setSituation(Situation value);

  /**
   * Returns the value of the '<em><b>Action</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Action</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Action</em>' containment reference.
   * @see #setAction(Action)
   * @see org.etri.slice.tools.adl.domainmodel.DomainmodelPackage#getBehavior_Action()
   * @model containment="true"
   * @generated
   */
  Action getAction();

  /**
   * Sets the value of the '{@link org.etri.slice.tools.adl.domainmodel.Behavior#getAction <em>Action</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Action</em>' containment reference.
   * @see #getAction()
   * @generated
   */
  void setAction(Action value);

} // Behavior